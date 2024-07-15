package baekjoon;

import java.util.*;
import java.io.*;

public class 치킨배달_15686 {
	private static int N, M;
	// private static int[][] city;
	private static List<Pair> house = new ArrayList<>();
	private static List<Pair> chicken = new ArrayList<>();

	private static class Pair {
		int r, c;

		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// city = new int[N][N];
		for (int r = 0; r < N; ++r) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; ++c) {
				int num = Integer.parseInt(st.nextToken());
				// city[r][c] = num;
				if (num == 1) house.add(new Pair(r, c));
				else if (num == 2) chicken.add(new Pair(r, c));
			}
		}
	}


	private static int chickenDistance(int r1, int c1, int r2, int c2) {
		return (Math.abs(r1 - r2) + Math.abs(c1 - c2));
	}
}
