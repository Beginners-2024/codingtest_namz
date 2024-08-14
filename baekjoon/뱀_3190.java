package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 풀이시간 : 40분
 * 메모리 : 14540 KB
 * 시간 : 108 ms
 * 시간복잡도 : O(N*N)
 */
public class 뱀_3190 {

	private static int N, K, L;
	private static int[][] board;
	private static Queue<Change> change;
	private static int count = 0;
	private static Snake snake = new Snake();
	private static int[] dr = {-1, 0, 1, 0};	// 상 좌 하 우
	private static int[] dc = {0, -1, 0, 1};


	// 뱀 머리 방향 바꾸는 명령어 구조체
	private static class Change {
		int time;
		char dir;

		Change(int t, char d) {
			time = t;
			dir = d;
		}
	}

	// (r, c) 구조체
	private static class Pair {
		int r, c;
		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	// Snake class
	private static class Snake {
		Deque<Pair> body;	// first : 꼬리 , last : 머리
		int length;
		int dir;

		Snake() {
			dir = 3;
			length = 1;
			body = new LinkedList<>();
			body.add(new Pair(0, 0));
		}

		// 사과 먹었을 때 뱀의 움직임
		void moveEat(int r, int c) {
			length++;					// 길이 증가
			body.add(new Pair(r, c));	// new 머리 추가
		}

		// 사과를 먹지 않았을 떄 뱀의 움직임
		void moveNoEat(int r, int c) {
			body.add(new Pair(r, c));		// new 머리 추가
			Pair tail = body.pollFirst();	// 꼬리 제거
			board[tail.r][tail.c] = 0;
		}

	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// step 1 - 입력
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		board = new int[N][N];
		board[0][0] = 1;		// 뱀 : 1

		StringTokenizer st;

		for (int i = 0; i < K; ++i) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;

			board[r][c] = 2;	// 사과 : 2
		}

		L = Integer.parseInt(br.readLine());

		change = new LinkedList<>();
		for (int i = 0; i < L; ++i) {
			st = new StringTokenizer(br.readLine());

			int X = Integer.parseInt(st.nextToken());
			char C = st.nextToken().charAt(0);
			change.add(new Change(X, C));
		}

		// step 2 - 종료 전까지 게임
		while (game()) { count++; }

		// step 3 - 출력
		System.out.println(++count);
	}

	/**
	 * step 2 - 뱀이 보드 위에서 움직이는 매서드
	 *
	 * @return	true : 게임 계속 / false : 게임 종료
	 */
	private static boolean game() {
		// (1) 뱀 머리 회전
		if (!change.isEmpty() && count == change.peek().time) {
			char dir = change.poll().dir;
			if (dir == 'L') snake.dir = (snake.dir + 1) % 4;
			else if (dir == 'D') snake.dir = (snake.dir + 3) % 4;
		}

		// (2) new head 위치
		Pair head = snake.body.peekLast();
		int nr = head.r + dr[snake.dir];
		int nc = head.c + dc[snake.dir];

		// (3) 종결조건 : 벽 or 몸
		if (!isValid(nr, nc)) return false;
		if (board[nr][nc] == 1) return false;

		// (4) 이동
		if (board[nr][nc] == 2) snake.moveEat(nr, nc);
		else snake.moveNoEat(nr, nc);
		board[nr][nc] = 1;

		return true;
	}

	private static boolean isValid(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}
