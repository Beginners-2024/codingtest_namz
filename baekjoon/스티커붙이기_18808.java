package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

public class 스티커붙이기_18808 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] laptop = new int[N][M];
		Sticker[] stickers = new Sticker[K];

		for (int i = 0; i < K; ++i) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			stickers[i] = new Sticker(R, C);

			for (int r = 0; r < R; ++r) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < C; ++c) {
					stickers[i].shape[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			// stickers[i].print();
			// System.out.println();
		}

	}

	public static class Sticker {
		int R;
		int C;
		int[][] shape;

		Sticker(int r, int c) { R = r; C = c; shape = new int[R][C]; }
		// public void print() {
		// 	for (int i = 0; i < R; ++i) {
		// 		for (int j = 0; j < C; ++j) {
		// 			System.out.print(shape[i][j] + " ");
		// 		}
		// 		System.out.println();
		// 	}
		// }
	}
}
