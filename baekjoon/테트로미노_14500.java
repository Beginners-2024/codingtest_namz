package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 풀이시간 : 50분
 * 메모리 : 63524 KB
 * 시간 : 632 ms
 */
public class 테트로미노_14500 {
	static int N, M;
	static int[][] paper;

	static List<int[][]> shapes = new ArrayList<>();

	static int[][] shape1 = {
		{1,1,1,1},
	};
	static int[][] shape2 = {
		{1,1},
		{1,1},
	};
	static int[][] shape3 = {
		{1,0},
		{1,0},
		{1,1},
	};
	static int[][] shape4 = {
		{1,0},
		{1,1},
		{0,1},
	};
	static int[][] shape5 = {
		{1,1,1},
		{0,1,0},
	};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		paper = new int[N][M];

		shapes.add(shape1);
		shapes.add(shape2);
		shapes.add(shape3);
		shapes.add(shape4);
		shapes.add(shape5);

		for (int r = 0; r < N; ++r) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; ++c) {
				paper[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int max = 0;
		for (int i = 0; i < 5; ++i) {
			max = Math.max(max, putOnPaper(shapes.get(i)));
		}
		System.out.println(max);
	}

	private static int putOnPaper(int[][] shape) {
		int count = 0;

		for (int i = 0; i < 4; ++i) {
			rotatePaper(i);
			for (int r = 0; r <= paper.length - shape.length; ++r) {
				for (int c = 0; c <= paper[r].length - shape[0].length; ++c) {
					count = Math.max(count, checkShape(r, c, shape));
				}
			}
		}

		return count;
	}

	private static void rotatePaper(int i) {

		int[][] tmp = new int[paper[0].length][paper.length];

		for (int r = 0; r < paper.length; ++r) {
			for (int c = 0; c < paper[r].length; ++c) {
				int nr = c;
				int nc = paper.length - r - 1;

				tmp[nr][nc] = paper[r][c];
			}
		}

		paper = new int[tmp.length][tmp[0].length];

		for (int r = 0; r < tmp.length; ++r) {
			for (int c = 0; c < tmp[r].length; ++c) {
				paper[r][c] = tmp[r][c];
			}
		}
	}

	private static int checkShape(int r, int c, int[][] shape) {
		int count1 = 0, count2 = 0;
		for (int i = 0; i < shape.length; ++i) {
			for (int j = 0; j < shape[0].length; ++j) {
				if (shape[i][j] == 1)
					count1 += paper[r + i][c + j];
				if (shape[shape.length - i - 1][j] == 1)
					count2 += paper[r + i][c + j];
			}
		}
		return Math.max(count1, count2);
	}
}
