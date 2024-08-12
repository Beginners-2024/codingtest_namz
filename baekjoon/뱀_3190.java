package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 풀이시간 : 40분
 * 메모리 : 14540 KB
 * 시간 : 108 ms
 */
public class 뱀_3190 {

	private static int N, K, L;
	private static int[][] board;
	private static Queue<Change> change;
	private static int count = 0;
	private static Snake snake = new Snake();
	private static int[] dr = {-1, 0, 1, 0};
	private static int[] dc = {0, -1, 0, 1};


	private static class Change {
		int time;
		char dir;

		Change(int t, char d) {
			time = t;
			dir = d;
		}
	}

	private static class Pair {
		int r, c;
		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	private static class Snake {
		Deque<Pair> body;
		int length;
		int dir;

		Snake() {
			dir = 3;
			length = 1;
			body = new LinkedList<>();
			body.add(new Pair(0, 0));
		}

		void moveEat(int r, int c) {
			length++;
			body.add(new Pair(r, c));
		}

		void moveNoEat(int r, int c) {
			body.add(new Pair(r, c));
			Pair tail = body.pollFirst();
			board[tail.r][tail.c] = 0;
		}

	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		board = new int[N][N];

		StringTokenizer st;

		for (int i = 0; i < K; ++i) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;

			board[r][c] = 2;
		}

		L = Integer.parseInt(br.readLine());

		change = new LinkedList<>();
		for (int i = 0; i < L; ++i) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			char C = st.nextToken().charAt(0);
			change.add(new Change(X, C));
		}

		board[0][0] = 1;

		while (game()) { count++; }
		System.out.println(++count);
	}

	private static boolean game() {
		if (!change.isEmpty() && count == change.peek().time) {
			char dir = change.poll().dir;
			if (dir == 'L') snake.dir = (snake.dir + 1) % 4;
			else if (dir == 'D') snake.dir = (snake.dir + 3) % 4;
		}

		Pair head = snake.body.peekLast();
		int nr = head.r + dr[snake.dir];
		int nc = head.c + dc[snake.dir];

		if (!isValid(nr, nc)) return false;
		if (board[nr][nc] == 1) return false;

		if (board[nr][nc] == 2) {
			snake.moveEat(nr, nc);
			board[nr][nc] = 1;
		} else {
			snake.moveNoEat(nr, nc);
			board[nr][nc] = 1;
		}

		return true;
	}

	private static boolean isValid(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c <= N;
	}
}
