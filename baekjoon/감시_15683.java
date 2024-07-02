package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

public class 감시_15683 {

	// 사각지대 개수
	public static int blind_spot = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] office = new int[N][M];
		Cctv[] cctv = new Cctv[8];

		int C = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());

				if (num == 6) num = -1; // 벽은 -1로 설정함
				if (num > 0) cctv[C++] = new Cctv(num, i, j);

				office[i][j] = num;
			}
		}

		// 사각지대 계산하는 메서드 호출
		view(N, M, C, office, cctv, 0);
		System.out.println(blind_spot);
	}

	public static class Cctv {
		int number; // cctv 번호
		int row;
		int col;

		Vector<Integer> dir = new Vector<>();

		Cctv(int n, int r, int c) { number = n; row = r; col = c; initDir();}

		// 초기 방향 설정
		void initDir() {
			if (number == 1) {
				dir.add(1);
			} else if (number == 2) {
				dir.add(1);
				dir.add(3);
			} else if (number == 3) {
				dir.add(0);
				dir.add(1);
			} else if (number == 4) {
				dir.add(0);
				dir.add(1);
				dir.add(3);
			} else {
				dir.add(0);
				dir.add(1);
				dir.add(2);
				dir.add(3);
			}
		}
	}

	// 상 우 하 좌
	public static int[] dir_r = {-1, 0, 1, 0};
	public static int[] dir_c = {0, 1, 0, -1};

	/**
	 * cctv 배열 loop
	 * 현재 cctv의 방향에서 보이는 부분을 모두 체크 후, 다음 cctv를 체크
	 * 마지막 cctv까지 모두 체크했으면 사각지대의 개수를 계산
	 *
	 * @param N office의 세로(row) 사이즈
	 * @param M office의 가로(column) 사이즈
	 * @param C CCTV 대수
	 * @param office N*M 이차원 배열 (office)
	 * @param cctv 전체 cctv 배열
	 * @param index cctv 현재 인덱스
	 */
	public static void view(int N, int M, int C, int[][] office, Cctv[] cctv, int index) {

		// 마지막 CCTV까지 조회완료한 경우
		if (index == C) {
			int count = 0;
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < M; ++j) {
					if (office[i][j] == 0) count++;
				}
			}

			// 사각지대 최소값 검증
			if (count < blind_spot) blind_spot = count;
			return;
		}

		// CCTV 번호마다 loop 도는 횟수 설정
		int range = 4;
		if (cctv[index].number == 2) range = 2;
		else if (cctv[index].number == 5) range = 1;

		// CCTV 90도씩 돌려가면서 체크하는 loop
		for (int i = 0; i < range; ++i) {

			// CCTV 시야 체크용 copy
			int[][] copy_office = new int[N][M];
			for (int r = 0; r < N; ++r) {
				for (int c = 0; c < M; ++c) {
					copy_office[r][c] = office[r][c];
				}
			}

			// CCTV가 보는 방향 설정
			for (int j = 0; j < cctv[index].dir.size(); ++j) {
				int new_dir = (cctv[index].dir.get(j) + i) % 4;

				int nr = cctv[index].row + dir_r[new_dir];
				int nc = cctv[index].col + dir_c[new_dir];

				// 해당 방향에 있는 모든 칸 체크
				while (isValid(nr, nc, N, M) && copy_office[nr][nc] != -1) {
					if (copy_office[nr][nc] == 0) copy_office[nr][nc] = -2;

					nr += dir_r[new_dir];
					nc += dir_c[new_dir];
				}
			}

			// 다음 CCTV 체크
			view(N, M, C, copy_office, cctv, index+1);
		}
	}

	//

	/**
	 * (r,c) 좌표가 N*M office 이차원 배열을 벗어나지 않는지 유효성 검사 메서드
	 *
	 * @param r : row
	 * @param c : column
	 * @param N : office의 세로(row) 사이즈
	 * @param M : office의 가로(column) 사이즈
	 * @return True : 유효한 좌표 / False : 유효하지 않은 좌표
	 */
	public static boolean isValid(int r, int c, int N, int M) {
		if (r < 0 || N <= r || c < 0 || M <= c) return false;
		return true;
	}
}
