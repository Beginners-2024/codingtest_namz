package baekjoon;

import java.io.*;
import java.util.*;

public class 그림_1926 {

	private static int n, m;						// n : paper row, m : paper column
	private static int[][] paper;					// 도화지
	private static int[] dicRow = {-1, 1, 0, 0};	// 상, 하, 좌, 우
	private static int[] dicCol = {0, 0, -1, 1};
	private static boolean visit[][];				// paper 방문 여부
	private static int maxSizeOfPainting = 0;		// 그림 최대 사이즈
	private static int countOfPainting = 0;			// 그림 개수


	static class Pair {		// (r, c) 사용을 위한 Pair 자료구조 구현
		int row;
		int col;
		Pair(int r, int c) {
			this.row = r;
			this.col = c;
		}
	}

	public static void main(String[] args) throws IOException {

		// step 1 - input
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

		// step 2 - BFS
		visit = new boolean[n][m];
		for (int r = 0; r < n; ++r) {
			for (int c = 0; c < m; ++c) {
				// 이미 방문했거나 그림이 아니면 다음으로 넘어감
				if (visit[r][c] || paper[r][c] == 0) continue;

				maxSizeOfPainting = Math.max(findPainting(r, c), maxSizeOfPainting);
				countOfPainting++;
			}
		}

		System.out.println(countOfPainting);
		System.out.println(maxSizeOfPainting);
	}

	/**
	 * 현재 좌표의 상하좌우를 확인하여 그림(1)이면 queue의 대기열에 넣음.(방문해야할 좌표)
	 * queue에 더이상 방문할 좌표가 없으면 그림이 끝난 것
	 *
	 * @param row 시작좌표 row
	 * @param col 시작좌표 column
	 * @return 그림 사이즈
	 */
	public static int findPainting(int row, int col) {
		int size = 0;

		Queue<Pair> q = new LinkedList<>();	// 방문할 좌표를 저장
		q.add(new Pair(row, col));

		visit[row][col] = true;

		while (q.size() > 0) {

			// 현재 좌표
			int r = q.peek().row;
			int c = q.peek().col;
			q.poll();

			size++;

			// 상하좌우 검사
			for (int i = 0; i < 4; ++i) {

				// new 좌표
				int nr = r + dicRow[i];
				int nc = c + dicCol[i];

				// 유효성, 방문여부, 그림여부 판단
				if (!isValid(nr, nc)) continue;
				if (visit[nr][nc]) continue;
				if (paper[nr][nc] == 0) continue;

				// 여기까지 왔다면 유효하고, 아직 방문하지 않았고, 그림인 좌표이다.

				visit[nr][nc] = true;
				q.add(new Pair(nr, nc));
			}
		}
		return size;
	}

	public static boolean isValid(int r, int c) {
		if (r < 0 || n <= r || c < 0 || m <= c) return false;
		return true;
	}
}
