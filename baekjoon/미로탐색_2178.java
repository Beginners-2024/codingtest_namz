package baekjoon;

import java.util.*;
import java.io.*;

public class 미로탐색_2178 {

	// 입력값
	private static int N, M;
	private static int[][] maze;

	// BFS용 변수
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	private static boolean[][] visit;

	// (r, c)
	private static class Pair {
		int r, c;
		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		maze = new int[N][M];
		visit = new boolean[N][M];

		for (int r = 0; r < N; ++r) {
			String s = br.readLine();
			for (int c = 0; c < M; ++c) {
				maze[r][c] = s.charAt(c) - '0';
			}
		}

		makeRoute();
		System.out.println(maze[N-1][M-1]);
	}

	/**
	 * 현재 위치의 상하좌우에 현재위치+1 을 넣어 각 칸에 도달하기 위한 최단경로 값을 구함.
	 */
	private static void makeRoute() {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(0, 0));
		visit[0][0] = true;

		while (!q.isEmpty()) {
			int r = q.peek().r;
			int c = q.peek().c;
			q.poll();

			for (int i = 0; i < 4; ++i) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (!isValid(nr, nc) || maze[nr][nc] == 0 || visit[nr][nc]) continue;

				maze[nr][nc] = maze[r][c] + 1;
				visit[nr][nc] = true;
				q.add(new Pair(nr, nc));
			}
		}
	}

	private static boolean isValid(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
