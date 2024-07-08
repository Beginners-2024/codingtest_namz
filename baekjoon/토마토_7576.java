package baekjoon;

import java.util.*;
import java.io.*;

public class 토마토_7576 {

	private static int M, N;
	private static int[][] tomato;

	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	private static Queue<Pair> deliciousTomato = new LinkedList<>();
	private static int dayToBeDelicious = 0;

	private static class Pair {
		int r, c;
		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		tomato = new int[N][M];
		boolean start = false;
		for (int r = 0; r < N; ++r) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; ++c) {
				tomato[r][c] = Integer.parseInt(st.nextToken());
				if (tomato[r][c] == 1) deliciousTomato.add(new Pair(r, c));
				if (tomato[r][c] == 0) start = true;
			}
		}

		if (!start) {
			System.out.println(0);
			return ;
		}

		while (!deliciousTomato.isEmpty()) {
			if (makeDelicious(deliciousTomato.peek().r, deliciousTomato.peek().c, deliciousTomato.size()))
				dayToBeDelicious++;
		}

		for (int r = 0; r < N; ++r) {
			for (int c = 0; c < M; ++c) {
				if (tomato[r][c] == 0) {
					System.out.println(-1);
					return ;
				}
			}
		}

		System.out.println(dayToBeDelicious);
	}

	private static boolean makeDelicious(int r, int c, int size) {
		boolean flag = false;
		while (size > 0) {
			Pair nowTomato = deliciousTomato.poll();

			for (int i = 0; i < 4; ++i) {
				int nr = nowTomato.r + dr[i];
				int nc = nowTomato.c + dc[i];

				if (!isValid(nr, nc)) continue;
				if (tomato[nr][nc] == 1 || tomato[nr][nc] == -1) continue;

				tomato[nr][nc] = 1;
				deliciousTomato.add(new Pair(nr, nc));
				flag = true;
			}
			size--;
		}

		return flag;
	}

	private static boolean isValid(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
