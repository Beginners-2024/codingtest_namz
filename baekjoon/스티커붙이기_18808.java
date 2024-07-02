package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스티커붙이기_18808 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] laptop = new int[N][M];
		Sticker[] stickers = new Sticker[K];

		for (int i = 0; i < K; ++i) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			stickers[i] = new Sticker(R, C);

			for (int r = 0; r < R; ++r) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < C; ++c) {
					stickers[i].shape[r][c] = Integer.parseInt(st.nextToken());
				}
			}
		}

		// 스티커 순서대로 붙이기
		for (int i = 0; i < K; ++i) {
			int tmp[][] = decorate(stickers[i], laptop, N, M, 0);

			for (int r = 0; r < N; ++r) {
				for (int c = 0; c < M; ++c) {
					laptop[r][c] = tmp[r][c];
				}
			}
		}

		// 스티커 개수 세기
		int count = 0;
		for (int i = 0; i < N; ++i){
			for (int j = 0; j < M; ++j) {
				if (laptop[i][j] == 1) count++;
			}
		}
		System.out.println(count);
	}

	public static class Sticker {
		int R;
		int C;
		int[][] shape;

		Sticker(int r, int c) { R = r; C = c; shape = new int[R][C]; }

		// 시계방향으로 90도 돌린 모양 재설정
		void reSetShape() {
			int[][] tmp = new int[C][R];
			for (int i = 0; i < R; ++i) {
				int nc = R - 1 - i;
				for (int j = 0; j < C; ++j) {
					int nr = j;
					tmp[nr][nc] = shape[i][j];
				}
			}
			int tc = C;
			C = R;
			R = tc;

			shape = new int[R][C];
			for (int i = 0; i < R; ++i) {
				for (int j = 0; j < C; ++j) {
					shape[i][j] = tmp[i][j];
				}
			}
		}
	}

	/**
	 * 스티커 위치를 왼쪽 위 -> 오른쪽 아래 로 옮겨가면서 붙일 수 있는지 확인
	 * 붙일 수 있는 위치가 없다면 시계방향으로 90도 회전해서 다시 확인
	 *
	 * @param sticker 붙일 스티커
	 * @param laptop 스티커가 붙을 노트북
	 * @param N 노트북 세로
	 * @param M 노트북 가로
	 * @param dir 회전 횟수 (1 : 90도 , 2 : 180도, 3 : 270도)
	 * @return 스티커가 붙은 laptop 이차원 배열
	 */
	public static int[][] decorate(Sticker sticker, int[][] laptop, int N, int M, int dir) {

		boolean done = false; // 스티커를 붙인 여부륿 판단하는 flag (true : 붙임 , false : 안붙임)

		// 첫번째 loop : 노트북을 왼쪽 위 -> 오른쪽 아래로 가면서 스티커 붙일 위치 설정. (r, c)가 검사할 범위의 가장 왼쪽 위 지점이 된다.
		for (int r = 0; r < N - sticker.R + 1; ++r) {
			for (int c = 0; c < M - sticker.C + 1; ++c) {

				boolean possible = true; // 해당 위치에 스티커를 붙일 수 있는지 여부를 판단하는 flag (true : 붙일 수 있음 , false : 붙일 수 없음)

				// laptop 복사본 생성
				int[][] copy_laptop = new int[N][M];
				for (int i = 0; i < N; ++i) {
					for (int j = 0; j < M; ++j) {
						copy_laptop[i][j] = laptop[i][j];
					}
				}

				// 두번째 loop : 첫번째 loop에서 지정한 laptop 위치에 스티커를 붙일 수 있는지 여부 확인
				for (int i = 0; i < sticker.R; ++i) {
					for (int j = 0; j < sticker.C; ++j) {
						if (sticker.shape[i][j] == 1 && laptop[r+i][c+j] == 1) {
							possible = false;
							break;
						}
						if (sticker.shape[i][j] == 1 && laptop[r+i][c+j] == 0) {
							copy_laptop[r + i][c + j] = 1;
						}
					}
					if (!possible) break; // 붙일 수 없다면 다음 위치를 찾아햐 함.
				}

				// 해당 위치에 스티커를 붙일 수 있다면 붙이고 loop 벗어남
				if (possible) {
					for (int i = 0; i < N; ++i) {
						for (int j = 0; j < M; ++j) {
							laptop[i][j] = copy_laptop[i][j];
						}
					}
					done = true;
					break;
				} else possible = true;
			}

			if (done) break; // 스티커 붙였으면 loop 끝
		}

		// 스티커를 붙이지 못했다면 회전
		if (!done) {
			if (dir == 3) return laptop; // 270도까지 회전했으면 스티커를 붙일 수 없다

			sticker.reSetShape(); // 스티커 회전한 모양 재설정

			laptop = decorate(sticker, laptop, N, M, dir+1);
		}
		return laptop;
	}
}
