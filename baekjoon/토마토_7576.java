package baekjoon;

import java.util.*;
import java.io.*;

public class 토마토_7576 {

	// 입력값
	private static int M, N;
	private static int[][] tomato;

	// BFS용 변수
	private static int[] dr = {-1, 1, 0, 0};							// 상하좌우
	private static int[] dc = {0, 0, -1, 1};
	private static Queue<Pair> deliciousTomato = new LinkedList<>();	// 익은 토마토 리스트
	private static int dayToBeDelicious = 0;

	// (r, c)
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
		boolean start = false;	// 토마토 익히기를 시작할 수 있는지 여부 판단

		for (int r = 0; r < N; ++r) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; ++c) {
				tomato[r][c] = Integer.parseInt(st.nextToken());
				if (tomato[r][c] == 1) deliciousTomato.add(new Pair(r, c));
				if (tomato[r][c] == 0) start = true;
			}
		}

		// 모든 토마토가 이미 익어서 시작 불가능
		if (!start) {
			System.out.println(0);
			return ;
		}

		// 토마토 익히기
		while (!deliciousTomato.isEmpty()) {
			if (makeDelicious(deliciousTomato.size()))
				dayToBeDelicious++;
		}

		// 안익은 토마토가 낭아있는 상황. 개수로 비교하려다가 귀찮음 이슈 발발..........
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

	/**
	 * 현재 토마토의 상하좌우에 위치한 안익은 토마토를 익함
	 *
	 * @param size 현재 단계에서 확인해야할 익은 토마토 리스트의 사이즈
	 * @return true : 안익음->익음 으로 바뀐 토마토 있음, false : 없음
	 */
	private static boolean makeDelicious(int size) {
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
