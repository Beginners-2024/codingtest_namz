package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 풀이시간 : 40분
 * 메모리 : 14372 KB
 * 시간 : 108 ms
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
				if (room[r][c] == 0) countOfDirty++;
			}
		}

		while(clean()){
			if (countOfDirty == 0) break;
		}

		System.out.println(countOfClean);
	}

	private static boolean clean() {
		int r = robotCleaner.r;
		int c = robotCleaner.c;
		int d = robotCleaner.d;

		if (room[r][c] == 0) {
			room[r][c] = 2;
			countOfClean++;
			countOfDirty--;
		}

		if (!isDirty(r, c)) {
			int backR = r + dr[(d + 2) % 4];
			int backC = c + dc[(d + 2) % 4];
			if (room[backR][backC] == 1) return false;
			else robotCleaner.setPos(backR, backC);
		} else {
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

	private static boolean isDirty(int r, int c) {
		for (int i = 0; i < 4; ++i) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (room[nr][nc] == 0) return true;
		}
		return false;
	}
}
