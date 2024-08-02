package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 풀이시간 : 1시간
 * 메모리 : 14,500 KB
 * 시간 : 144 ms
 * 시간복잡도 : O(N*M + K)
 */
public class 주사위_굴리기_14499 {
	private static int N, M, r, c, K;
	private static int[][] map;
	private static int[] dice = {0, 0, 0, 0, 0, 0};
	private static int[] dr = {0, 0, 0, -1, 1};
	private static int[] dc = {0, 1, -1, 0, 0};
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// step 1 - 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// step 2 - 주사위 굴리기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; ++i) {
			move(Integer.parseInt(st.nextToken()));
		}

		// step 3 - 출력
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());

		bw.close();
		br.close();
	}

	/**
	 * step 2 (1) - 주사위를 굴리는 함수
	 *
	 * 1. 새로운 (r, c)가 유효한가
	 * 2. (r,c) : map == 0 인가
	 * 		(yes) => dice 바닥(5)의 숫자를 map에 복사
	 * 		(no) => map의 숫자를 dice 바닥(5)에 복사하고, map = 0
	 * 3. 주사위 윗면 출력
	 * @param dir 주사위가 구를 방향
	 */
	private static void move(int dir) {
		int nr = r + dr[dir];
		int nc = c + dc[dir];

		if (!isValid(nr, nc)) return ;

		r = nr;
		c = nc;
		setDice(dir);

		if (map[r][c] == 0)
			map[r][c] = dice[5];
		else {
			dice[5] = map[r][c];
			map[r][c] = 0;
		}

		sb.append(dice[2]).append('\n');
	}

	/**
	 * step 2 (2) - 주사위를 굴렸을 때 모양 결정하는 함수 (노가다 구현)
	 *
	 * @param dir 방향 (1 : 동 / 2 : 서 / 3 : 북 / 4 : 남)
	 */
	private static void setDice(int dir) {
		int[] tmp = new int[6];

		if (dir == 1) {
			tmp[0] = dice[0];
			tmp[1] = dice[5];
			tmp[2] = dice[1];
			tmp[3] = dice[2];
			tmp[4] = dice[4];
			tmp[5] = dice[3];
		} else if (dir == 2) {
			tmp[0] = dice[0];
			tmp[1] = dice[2];
			tmp[2] = dice[3];
			tmp[3] = dice[5];
			tmp[4] = dice[4];
			tmp[5] = dice[1];
		} else if (dir == 3) {
			tmp[0] = dice[2];
			tmp[1] = dice[1];
			tmp[2] = dice[4];
			tmp[3] = dice[3];
			tmp[4] = dice[5];
			tmp[5] = dice[0];
		} else {
			tmp[0] = dice[5];
			tmp[1] = dice[1];
			tmp[2] = dice[0];
			tmp[3] = dice[3];
			tmp[4] = dice[2];
			tmp[5] = dice[4];
		}

		dice = tmp.clone();
	}

	private static boolean isValid(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
