package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class 감시_15683 {

	public static class Cctv {
		int number;
		int row;
		int col;

		Cctv(int n, int r, int c) { number = n; row = r; col = c;}
	}
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


	}
}
