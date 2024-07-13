package baekjoon;

import java.util.*;
import java.io.*;

public class 이공사팔_easy_12100 {

	private static int N;
	private static int[][] board;

	private static int countOfblock = 0;
	private static int maxOfBlock = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		board = new int[N][N];
		StringTokenizer st;
		for (int r = 0; r < N; ++r) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; ++c) {
				board[r][c] = Integer.parseInt(st.nextToken());
				if (board[r][c] > 0) {
					countOfblock++;
					maxOfBlock = Math.max(maxOfBlock, board[r][c]);
				}
			}
		}

		game(countOfblock, 0, board);

		System.out.println(maxOfBlock);
	}

	private static void game(int count, int move, int[][] b) {
		if (count == 1 || move == 5) return;

		int[][] copyBoard;

		for (int i = 0; i < 4; ++i) {
			Queue<Integer> q = new LinkedList<>();
			copyBoard = new int[N][N];
			if (i == 0) {
				for (int c = 0; c < N; ++c) {
					for (int r = 0; r < N; ++r) {
						if (b[r][c] > 0) q.add(b[r][c]);
					}

					int k = 0;
					while (!q.isEmpty()) {
						int num1 , num2 = 0;

						num1 = q.poll();
						if (q.size() > 0 && q.peek() == num1) {
							num2 = q.poll();
							count--;
						}

						copyBoard[k++][c] = num1 + num2;
						maxOfBlock = Math.max(maxOfBlock, num1 + num2);
					}
				}
			} else if (i == 1) {
				for (int c = 0; c < N; ++c) {
					for (int r = N-1; r >= 0; --r) {
						if (b[r][c] > 0) q.add(b[r][c]);
					}

					int k = N-1;
					while (!q.isEmpty()) {
						int num1 , num2 = 0;

						num1 = q.poll();
						if (q.size() > 0 && q.peek() == num1) {
							num2 = q.poll();
							count--;
						}

						copyBoard[k--][c] = num1 + num2;
						maxOfBlock = Math.max(maxOfBlock, num1 + num2);
					}
				}
			} else if (i == 2) {
				for (int r = 0; r < N; ++r) {
					for (int c = 0; c < N; ++c) {
						if (b[r][c] > 0) q.add(b[r][c]);
					}

					int k = 0;
					while (!q.isEmpty()) {
						int num1, num2 = 0;

						num1 = q.poll();
						if (q.size() > 0 && q.peek() == num1) {
							num2 = q.poll();
							count--;
						}

						copyBoard[r][k++] = num1 + num2;
						maxOfBlock = Math.max(maxOfBlock, num1 + num2);
					}
				}
			} else if (i == 3) {
				for (int r = 0; r < N; ++r) {
					for (int c = N-1; c >= 0; --c) {
						if (b[r][c] > 0) q.add(b[r][c]);
					}

					int k = N-1;
					while (!q.isEmpty()) {
						int num1, num2 = 0;

						num1 = q.poll();
						if (q.size() > 0 && q.peek() == num1) {
							num2 = q.poll();
							count--;
						}

						copyBoard[r][k--] = num1 + num2;
						maxOfBlock = Math.max(maxOfBlock, num1 + num2);
					}
				}
			}
			q.clear();

			game(count, move+1, copyBoard);
		}


	}
}
