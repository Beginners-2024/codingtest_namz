package baekjoon;

import java.util.*;
import java.io.*;

public class 불_4179 {

	// 입력값
	private static int R, C; 		// 가로, 세로
	private static int[][] maze;	// 미로

	// BFS용 변수 : move jihun/fire
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	private static Queue<Pair> routeQueue = new LinkedList<>();
	private static Queue<Pair> fire = new LinkedList<>();
	private static boolean[][] visit;

	// 기타 변수들
	private static boolean escape = false;		// 지훈이 탈출 성공 플래그
	private static int minTimeOfEscape = 0;		// 탈출 최단 시간

	// (r, c)
	private static class Pair {
		int r, c;
		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] arg) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		maze = new int[R][C];
		visit = new boolean[R][C];

		// 입력받을 떄 정수값으로 변환 (-3 : 불, -1 : 벽, 0 : 길)
		for (int r  = 0; r < R; ++r) {
			String s = br.readLine();
			for (int c = 0; c < C; ++c) {
				char ch = s.charAt(c);
				if (ch == '#')
					maze[r][c] = -1;
				else if (ch == '.')
					maze[r][c] = 0;
				else if (ch == 'J') {
					maze[r][c] = 0;
					visit[r][c] = true;

					// 지훈이의 위치를 queue에 넣고 bfs 처리
					routeQueue.add(new Pair(r, c));
				}
				else if (ch == 'F') {
					maze[r][c] = -3;

					// 불의 위치를 queue에 넣음
					fire.add(new Pair(r, c));
				}
			}
		}

		// 미로 탐색 반복문
		while (!routeQueue.isEmpty()) {
			// 1. 지훈이를 이동시키고
			moveJihun();
			// 2. 탈출 가능한 상황이면 탐색 종료
			if (escape) break;
			// 3. 불을 확산
			moveFire();
		}

		if (escape) System.out.println(minTimeOfEscape);
		else System.out.println("IMPOSSIBLE");
	}

	/**
	 * BFS
	 * routeQueue : 지훈이의 현재 위치 리스트
	 *
	 * step 1 - 지훈이의 현재 위치 확인
	 * 		a. 불인가? -> YES : 현재 위치 사용 불가. 다음 리스트 확인
	 * 		b. 탈출가능한 위치인가? -> YES : 탈출 성공, 최소 시간 저장
	 * step 2 - 현재 위치의 상하좌우 중 갈 수 있는 길 큐에 저장. maze에는 해당 위치까지 도달하는데 걸린 최소 시간을 저장.
	 * step 3 - 1, 2를 반복하여 지훈이의 현재 위치 전체에 대한 검사를 한다.
	 */
	private static void moveJihun() {
		// queue 사이즈는 계속 바뀌므로 현재 위치에 대한 사이즈를 저장 후 사용
		int size = routeQueue.size();

		while (size > 0) {
			// step1 - 현재 위치 체크
			int r = routeQueue.peek().r;
			int c = routeQueue.peek().c;
			routeQueue.poll();
			size--;

			// a. 불인가?
			if (maze[r][c] == -3) continue;

			// b. 탈출 가능한 위치인가?
			if (canEscape(r, c)) {
				minTimeOfEscape = maze[r][c] + 1;
				escape = true;
				return ;
			}

			// step2 - 다음 위치 체크
			for (int i = 0; i < 4; ++i) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (!isValid(nr, nc)) continue;
				if (maze[nr][nc] == -1 || visit[nr][nc] || maze[nr][nc] == -3) continue;

				maze[nr][nc] = maze[r][c] + 1;
				visit[nr][nc] = true;
				routeQueue.add(new Pair(nr, nc));
			}
		}
	}

	/**
	 * fire 큐에 저장된 불에서 상하좌우로 한 칸씩 확산시킴
	 *
	 * queue를 사용한 이유 : 지난 단계의 불은 이미 상하좌우로 불을 확산시켰으므로 또 다시 체크할 필요가 없음.
	 */
	private static void moveFire() {
		// queue 사이즈는 계속 바뀌므로 현재 위치에 대한 사이즈를 저장 후 사용
		int size = fire.size();

		while (size > 0) {
			// 현재 위치
			int r = fire.peek().r;
			int c = fire.peek().c;
			fire.poll();
			size--;

			// 상하좌우로 불 확산
			for (int i = 0; i < 4; ++i) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (!isValid(nr, nc)) continue;
				if (maze[nr][nc] == -1) continue;
				if (maze[nr][nc] == -3) continue;

				maze[nr][nc] = -3;
				fire.add(new Pair(nr, nc));
			}
		}
	}

	private static boolean isValid(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}

	/**
	 * (r, c)가 maze의 가장자리에 있는지 확인
	 *
	 * @param r r 좌표
	 * @param c c 좌표
	 * @return true : 탈출 가능, false : 탈출 불가능
	 */
	private static boolean canEscape(int r, int c) {
		return r == R-1 || c == C-1 || r == 0 || c == 0;
	}
}
