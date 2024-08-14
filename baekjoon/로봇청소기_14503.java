package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 풀이시간 : 40분
 * 메모리 : 14372 KB
 * 시간 : 108 ms
 * 시간복잡도 : O(N * M)
 */
public class 로봇청소기_14503 {

	private static int N, M;
	private static int[][] room;
	private static int[] dr = {-1, 0, 1, 0};
	private static int[] dc = {0, 1, 0, -1};
	private static Robot robotCleaner;
	private static int countOfClean = 0;
	private static int countOfDirty = 0;

	private static class Robot {
		int r, c, d;

		Robot(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

		void setPos(int r, int c) {
			this.r = r;
			this.c = c;
		}

		void setPosAndDir(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// step 1 - 입력
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		robotCleaner = new Robot(Integer.parseInt(st.nextToken()),
								Integer.parseInt(st.nextToken()),
								Integer.parseInt(st.nextToken()));

		room = new int[N][M];
		for (int r = 0; r < N; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; ++c) {
				room[r][c] = Integer.parseInt(st.nextToken());

				// 청소해야할 칸의 개수를 센다
				if (room[r][c] == 0) countOfDirty++;
			}
		}

		// step 2 - 청소가 가능하면 계속해서 청소해라
		while(clean()){
			// 다 청소했으면 끝냄
			// 종결조건 사용한 이유 : 문제에서 제시한 종결조건이 1개밖에 없어서 걍 추가해봄..
			// 근데 생각해보니 문제 조건 자체가 청소할 공간이 더이상 없으면 종결인거라서 굳이...할 필요 없었다고 생각되네..
			if (countOfDirty == 0) break;
		}

		// step 3 - 출력
		System.out.println(countOfClean);
	}

	/**
	 * step 2 - 청소하는 메서드
	 * @return true : 청소를 할 수 있음 / false : 청소를 더이상 못함. 청소 끝내
	 */
	private static boolean clean() {
		int r = robotCleaner.r;
		int c = robotCleaner.c;
		int d = robotCleaner.d;

		// step 2 (1) 청소 가능한 빈칸이면 청소
		if (room[r][c] == 0) {
			room[r][c] = 2;
			countOfClean++;
			countOfDirty--;
		}

		// step 2 (2) 상하좌우 4방향에 청소 가능한 칸이 있는지 확인
		if (!isDirty(r, c)) {	// 없읍
			int backR = r + dr[(d + 2) % 4];
			int backC = c + dc[(d + 2) % 4];

			// step 2 (2) - 1 : 후진 가능하면 후진, 불가능하면 청소 끝
			if (room[backR][backC] == 1) return false;
			else robotCleaner.setPos(backR, backC);
		} else {				// 있음

			// step 2 (2) - 2 : 반시계로 돌면서 청소 가능한 칸 찾으면 이동
			for (int i = 0; i < 4; ++i) {
				d = (d + 3) % 4;
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (room[nr][nc] == 0) {
					robotCleaner.setPosAndDir(nr, nc, d);
					break;
				}
			}
		}

		return true;
	}

	/**
	 * step 2 (2) 상하좌우에 청소가능한 빈 칸이 있는지 확인하는 메서드
	 * @param r 청소기의 row
	 * @param c 청소기의 colunm
	 * @return true : 있음 / false : 없음
	 */
	private static boolean isDirty(int r, int c) {
		for (int i = 0; i < 4; ++i) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (room[nr][nc] == 0) return true;
		}
		return false;
	}
}
