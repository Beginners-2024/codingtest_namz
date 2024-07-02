package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

public class 감시_15683 {

	public static int blind_spot = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] office = new int[N][M];
		Cctv[] cctv = new Cctv[8];

		int C = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());

				if (num == 6) num = -1;
				if (num > 0) cctv[C++] = new Cctv(num, i, j);

				office[i][j] = num;
			}
		}

		// for (int i = 0; i < N; i++) {
		// 	for (int j = 0; j < M; j++) {
		// 		System.out.print(office[i][j] + " ");
		// 	}
		// 	System.out.println();
		// }
		view(N, M, C, office, cctv, 0);
		System.out.println(blind_spot);
	}

	public static class Cctv {
		int number;
		int row;
		int col;

		Vector<Integer> dir = new Vector<>();

		Cctv(int n, int r, int c) { number = n; row = r; col = c; initDir();}

		void initDir() {
			if (number == 1) {
				dir.add(1);
			} else if (number == 2) {
				dir.add(1);
				dir.add(3);
			} else if (number == 3) {
				dir.add(0);
				dir.add(1);
			} else if (number == 4) {
				dir.add(0);
				dir.add(1);
				dir.add(3);
			} else {
				dir.add(0);
				dir.add(1);
				dir.add(2);
				dir.add(3);
			}
		}
	}

	public static int[] dir_r = {-1, 0, 1, 0};
	public static int[] dir_c = {0, 1, 0, -1};

	public static void view(int N, int M, int C, int[][] office, Cctv[] cctv, int index) {
		if (index == C) {
			int count = 0;
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < M; ++j) {
					if (office[i][j] == 0) count++;
				}
			}

			if (count < blind_spot) blind_spot = count;
			return;
		}



		int range = 4;
		if (cctv[index].number == 2) range = 2;
		else if (cctv[index].number == 5) range = 1;

		for (int i = 0; i < range; ++i) {
			int[][] copy_office = new int[N][M];
			for (int r = 0; r < N; ++r) {
				for (int c = 0; c < M; ++c) {
					copy_office[r][c] = office[r][c];
				}
			}
			for (int j = 0; j < cctv[index].dir.size(); ++j) {
				int new_dir = (cctv[index].dir.get(j) + i) % 4;

				int nr = cctv[index].row + dir_r[new_dir];
				int nc = cctv[index].col + dir_c[new_dir];

				while (isValid(nr, nc, N, M) && copy_office[nr][nc] != -1) {
					if (copy_office[nr][nc] == 0) copy_office[nr][nc] = -2;

					nr += dir_r[new_dir];
					nc += dir_c[new_dir];
				}
			}

			view(N, M, C, copy_office, cctv, index+1);
		}
	}

	public static boolean isValid(int r, int c, int N, int M) {
		if (r < 0 || N <= r || c < 0 || M <= c) return false;
		return true;
	}
}
