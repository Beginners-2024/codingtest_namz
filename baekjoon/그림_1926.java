package baekjoon;

import java.io.*;
import java.util.*;

public class 그림_1926 {

	private static int n, m;
	private static int[][] paper;
	private static int[] dicRow = {-1, 1, 0, 0};
	private static int[] dicCol = {0, 0, -1, 1};
	private static boolean visit[][];
	private static int maxSizeOfPainting = 0;
	private static int countOfPainting = 0;

	static class Node {
		int row;
		int col;
		Node(int r, int c) {
			this.row = r;
			this.col = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		paper = new int[n][m];
		for (int r = 0; r < n; ++r) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < m; ++c) {
				paper[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		visit = new boolean[n][m];

		for (int r = 0; r < n; ++r) {
			for (int c = 0; c < m; ++c) {
				if (visit[r][c] || paper[r][c] == 0) continue;

				maxSizeOfPainting = Math.max(findPainting(r, c), maxSizeOfPainting);
				countOfPainting++;
			}
		}

		System.out.println(countOfPainting);
		System.out.println(maxSizeOfPainting);

	}

	public static int findPainting(int row, int col) {
		int size = 0;

		Queue<Node> q = new LinkedList<>();
		q.add(new Node(row, col));

		visit[row][col] = true;

		while (q.size() > 0) {
			int r = q.peek().row;
			int c = q.peek().col;
			q.poll();

			size++;

			for (int i = 0; i < 4; ++i) {
				int nr = r + dicRow[i];
				int nc = c + dicCol[i];

				if (!isValid(nr, nc)) continue;
				if (visit[nr][nc]) continue;
				if (paper[nr][nc] == 0) continue;

				visit[nr][nc] = true;
				q.add(new Node(nr, nc));
			}
		}
		return size;
	}

	public static boolean isValid(int r, int c) {
		if (r < 0 || n <= r || c < 0 || m <= c) return false;
		return true;
	}
}
