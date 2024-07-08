package baekjoon;

import java.util.*;
import java.io.*;

public class 불_4179 {

	private static int R, C;
	private static char[][] maze;

	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};

	private static Pair jihun;
	private static ArrayList<Pair> fire;

	private static boolean escape = false;
	private static boolean gameOver = false;

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

		maze = new char[R][C];
		fire = new ArrayList<>(R*C);

		for (int r  = 0; r < R; ++r) {
			String s = br.readLine();
			for (int c = 0; c < C; ++c) {
				maze[r][c] = s.charAt(c);
				if (maze[r][c] == 'J') jihun  = new Pair(r, c);
				else if (maze[r][c] == 'F') fire.add(new Pair(r, c));
			}
		}

		while (true) {
			if (gameOver) break;
			if (escape) break;


		}
	}

	private static void moveJihun() {

	}

	private static void moveFire() {
		for (Pair p : fire) {
			for (int i = 0; i < 4; ++i) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];

				if (!isValid(nr, nc)) continue;
				if (maze[nr][nc] == '#' || maze[nr][nc] == 'J') continue;

				maze[nr][nc] = 'F';
				fire.add(new Pair(nr, nc));
			}
		}
	}

	private static boolean isValid(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
}
