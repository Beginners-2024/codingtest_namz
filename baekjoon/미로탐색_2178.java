package baekjoon;

import java.util.*;
import java.io.*;

public class 미로탐색_2178 {

	private static int N, M;
	private static int[][] maze;

	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};

	private static int minCount = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		maze = new int[N][M];
		for (int r = 0; r < N; ++r) {
			String s = br.readLine();
			for (int c = 0; c < M; ++c) {
				maze[r][c] = s.charAt(c) - '0';
			}
		}


	}
}
