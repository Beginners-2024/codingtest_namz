package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 시간복잡도 :  O(N*N)
 * 		입력 O(N*N) + dfs1 O(N*N) + dfs1(N*N)
 * 메모리 : 16728 KB
 * 시간 : 160 ms
 */
public class 적록색약_10026 {

	// 입력값
	private static int N;
	private static char[][] colorMap;
	private static char[][] colorMapForRG;

	// dfs용
	private static boolean[][] visit;
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};

	private static class Pair{
		int r, c;

		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		visit = new boolean[N][N];
		colorMap = new char[N][N];			// 적록색 아닌 사람용 컬러 맵
		colorMapForRG = new char[N][N];		// 적록색용 컬러 맵

		for (int r = 0; r < N; ++r) {
			String s = br.readLine();

			for (int c = 0; c < N; ++c) {
				char ch = s.charAt(c);
				colorMap[r][c] = ch;

				// 적록색용 맵에는 G를 R로 취급한다
				if (ch == 'G') ch = 'R';
				colorMapForRG[r][c] = ch;
			}
		}

		// 적록색 아닌사람 dfs
		int answerNormal = 0;
		for (int r = 0; r < N; ++r) {
			for (int c = 0; c < N; ++c) {
				if (visit[r][c]) continue;

				char colorKey = colorMap[r][c];
				dfs(r, c, colorKey, colorMap);
				answerNormal++;
			}
		}

		// 적록색약 dfs
		visit = new boolean[N][N];
		int answerRG = 0;
		for (int r = 0; r < N; ++r) {
			for (int c = 0; c < N; ++c) {
				if (visit[r][c]) continue;

				char colorKey = colorMapForRG[r][c];
				dfs(r, c, colorKey, colorMapForRG);
				answerRG++;
			}
		}

		System.out.println(answerNormal + " " + answerRG);
	}

	private static void dfs(int r, int c, char color, char[][] map) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(r, c));

		visit[r][c] = true;
		while (!q.isEmpty()) {
			Pair nowPos = q.poll();

			for (int i = 0; i < 4; ++i) {
				int nr = nowPos.r + dr[i];
				int nc = nowPos.c + dc[i];

				if (!isValid(nr, nc) || visit[nr][nc] || map[nr][nc] != color) continue;

				visit[nr][nc] = true;
				q.add(new Pair(nr, nc));
			}
		}
	}

	private static boolean isValid(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}
