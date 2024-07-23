package baekjoon;

import java.util.*;
import java.io.*;

/**
 * 소요시간 : 한시간 반
 * 		1차 : 시간초과
 * 			=> 단계별로 하루하루 BFS로 토미토 익혔음. 시간초과
 * 		2차 : 틀림
 * 			=> 익어야하는/익은 토마토를 만나면 queue에 넣고 BFS
 * 			=> 문제점 : 순서대로 익는게 아니어서 2일 걸려서 익어야하는데 4일 걸려서 익는다고 먼저 체크되어버리는 경우 발생 (visit 사용이 문제)
 * 			=> 개선점 : visit 없애야함. 순서대로 익혀야함
 * 		3차 : 정답
 * 			=> 처음 입력받을 때 익은 토미토를 queue에 저장하고 이걸로 BFS 돌림
 * 			=> 무조건 순서대로 토마토가 익게 된다
 *
 * 문제 풀이 핵심 => (BFS 최단거리 문제 방식) 순서대로 익히기 / 3차원 시간, 공간 복잡도 고려하기
 *
 * 메모리 : 121,124 KB
 * 시간 : 732 ms
 *
 * 시간복잡도 : O(H * R * C)
 * 		입력 O(H * R * C)
 * 		BFS O(H * R * C)
 * 		계산 O(H * R * C)
 */
public class 토마토_7569 {

	/* 입력 */
	private static int R, C, H;
	private static int[][][] box;

	// 안익은 토마토 개수 : 안익은 토마토 없으면 ealry return 하기 위함
	private static int notDeliciousTomato = 0;

	/* BFS 용 변수 */

	// 익은 토마토 저장하는 queue
	private static Queue<Pair> deliciousQ = new LinkedList<>();

	// 상 하 좌 우 위 아래
	private static int[] dr = {1, -1, 0, 0, 0, 0};
	private static int[] dc = {0, 0, -1, 1, 0, 0};
	private static int[] dh = {0, 0, 0, 0, 1, -1};

	/* 3차원 좌표 : 그냥....pair 라고 함...*/
	private static class Pair {
		int r, c, h;
		Pair(int h, int r, int c) {
			this.h = h;
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// step 1 - 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		box = new int[H][R][C];

		for (int h = 0; h < H; ++h) {
			for (int r = 0; r < R; ++r) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < C; ++c) {
					box[h][r][c] = Integer.parseInt(st.nextToken());

					// 익은 토미토는 queue에 넣음. (순서대로 BFS 돌리기 위함)
					if (box[h][r][c] == 1) {
						deliciousQ.add(new Pair(h, r, c));
					}
					// 안익은 토마토 개수 카운트
					if (box[h][r][c] == 0) notDeliciousTomato++;
				}
			}
		}

		// 익은 토마토 없으면 early return (-1)
		if (deliciousQ.size() == 0) {
			System.out.println(-1);
			return ;
		}

		// 익힐 토마토가 없으면 ealry return (0)
		if (deliciousQ.size() > 0 && notDeliciousTomato == 0) {
			System.out.println(0);
			return ;
		}

		// step 2 - 토마토 익히기
		checkDelicious();

		// step 3 - 최소 날짜 계산해서 출력
		System.out.println(howLongDelicious());
	}

	/**
	 * 토마토 익히기 함수
	 * BFS 최단거리 계산 방식
	 */
	private static void checkDelicious() {
		while (!deliciousQ.isEmpty()) {
			Pair now = deliciousQ.poll();

			for (int i = 0; i < 6; ++i) {
				int nh = now.h + dh[i];
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (!isValid(nh, nr, nc) || box[nh][nr][nc] == -1) continue;

				if (box[nh][nr][nc] == 0) {
					box[nh][nr][nc] = box[now.h][now.r][now.c] + 1;
					deliciousQ.add(new Pair(nh, nr, nc));
				}
			}
		}
	}

	/**
	 * 토마토가 다 익기까지 걸린 시간 계산하는 함수
	 * @return 토마토가 다 익기까지 걸린 최소 시간
	 */
	private static int howLongDelicious() {
		int day = 0;
		for (int h = 0; h < H; ++h) {
			for (int r = 0; r < R; ++r) {
				for (int c = 0; c < C; ++c) {
					// 안익은 토마토 있으면 예외처리
					if (box[h][r][c] == 0) return -1;

					day = Math.max(day, box[h][r][c]);
				}
			}
		}
		return day-1;
	}

	private static boolean isValid(int h, int r, int c) {
		return 0 <= h && h < H && 0 <= r && r < R && 0 <= c && c < C;
	}
}
