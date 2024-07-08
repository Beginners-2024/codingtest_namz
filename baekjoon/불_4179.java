package baekjoon;

import java.util.*;
import java.io.*;

public class 불_4179 {

	private static int R, C;
	private static int[][] maze;

	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	private static Queue<Pair> routeQueue = new LinkedList<>();
	private static boolean[][] visit;

	private static Queue<Pair> fire = new LinkedList<>();

	private static boolean escape = false;
	private static int minTimeOfEscape = Integer.MAX_VALUE;

	private static class Pair {
		int r, c;
		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public void setNew(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] arg) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		maze = new int[R][C];
		visit = new boolean[R][C];

		for (int r  = 0; r < R; ++r) {
			String s = br.readLine();
			for (int c = 0; c < C; ++c) {
				char ch = s.charAt(c);
				if (ch == '#')
					maze[r][c] = -1;
				else if (ch == '.')
					maze[r][c] = 0;
				else if (ch == 'J') {
					maze[r][c] = 0;
					visit[r][c] = true;
					routeQueue.add(new Pair(r, c));
				}
				else if (ch == 'F') {
					maze[r][c] = -3;
					fire.add(new Pair(r, c));
				}
			}
		}

		while (!routeQueue.isEmpty()) {
			moveJihun();
			if (escape) break;
			moveFire();
		}

		if (escape) System.out.println(minTimeOfEscape);
		else System.out.println("IMPOSSIBLE");
	}

	private static void moveJihun() {
		int size = routeQueue.size();

		while (size > 0) {
			int r = routeQueue.peek().r;
			int c = routeQueue.peek().c;
			routeQueue.poll();
			size--;

			if (maze[r][c] == -3) continue;

			if (canEscape(r, c)) {
				minTimeOfEscape = maze[r][c] + 1;
				escape = true;
				return ;
			}

			for (int i = 0; i < 4; ++i) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (!isValid(nr, nc)) continue;
				if (maze[nr][nc] == -1 || visit[nr][nc] || maze[nr][nc] == -3) continue;

				maze[nr][nc] = maze[r][c] + 1;
				visit[nr][nc] = true;
				routeQueue.add(new Pair(nr, nc));
			}
		}
	}

	private static void moveFire() {
		int size = fire.size();

		while (size > 0) {
			int r = fire.peek().r;
			int c = fire.peek().c;
			fire.poll();
			size--;

			for (int i = 0; i < 4; ++i) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (!isValid(nr, nc)) continue;
				if (maze[nr][nc] == -1) continue;
				if (maze[nr][nc] == -3) continue;

				maze[nr][nc] = -3;
				fire.add(new Pair(nr, nc));
			}
		}
	}

	private static boolean isValid(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}

	private static boolean canEscape(int r, int c) {
		return r == R-1 || c == C-1 || r == 0 || c == 0;
	}
}
