package baekjoon;

import java.util.*;
import java.io.*;

/**
 * 시간복잡도 : O(T * (N * M + K))
 *		입력 O(T * K) + dfs O(T * N * M)
 * 메모리 : 16236 KB
 * 시간 : 176 ms
 */
public class 유기농배추_1012 {

	// 입력
	private static int[][] field;

	private static int M, N, K;

	// dfs용
	private static boolean[][] visit;
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};

	// 출력
	private static StringBuilder sb = new StringBuilder();

	// pair (r,c)
	private static class Pair {
		int r, c;

		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		// 테스트 케이스만큼 돌린다
		for (int i = 0; i < T; ++i) {
			st = new StringTokenizer(br.readLine());

			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			field = new int[N][M];
			visit = new boolean[N][M];

			for (int j = 0; j < K; ++j) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());

				// 배추 : 1, 밭 : 0
				field[r][c] = 1;
			}

			int answer = 0;
			for (int r = 0; r < N; ++r) {
				for (int c = 0; c < M; ++c) {
					if (visit[r][c] || field[r][c] == 0) continue;

					dfs(r, c);
					answer++;
				}
			}

			sb.append(answer).append('\n');
		}

		bw.write(sb.toString());

		bw.flush();
		bw.close();
		br.close();
	}

	private static void dfs(int r, int c) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(r, c));
		visit[r][c] = true;

		while (!q.isEmpty()) {
			Pair nowPos = q.poll();

			for (int i = 0; i < 4; ++i) {
				int nextR = nowPos.r + dr[i];
				int nextC = nowPos.c + dc[i];

				if (!isValid(nextR, nextC) || field[nextR][nextC] == 0 || visit[nextR][nextC]) continue;

				visit[nextR][nextC] = true;
				q.add(new Pair(nextR, nextC));
			}
		}
	}

	private static boolean isValid(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < M);
	}
}
