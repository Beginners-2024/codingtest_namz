package baekjoon;

import java.io.*;
import java.util.*;

public class 구슬찾기_2617 {
	private static int N, M;
	private static Tree[] marble;
	private static boolean[] lightArr, heavyArr;

	private static class Tree {
		boolean[] light, heavy;
		int lightCount = 0, heavyCount = 0;

		Tree(int n) {
			light = new boolean[n];
			heavy = new boolean[n];
		}

		void setCount() {
			for (int i = 0; i < N; ++i) {
				if (light[i]) lightCount++;
				if (heavy[i]) heavyCount++;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		marble = new Tree[N];
		for (int i = 0; i < N; ++i) {
			marble[i] = new Tree(N);
		}

		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int mh = Integer.parseInt(st.nextToken()) - 1;
			int ml = Integer.parseInt(st.nextToken()) - 1;

			marble[mh].light[ml] = true;
			marble[ml].heavy[mh] = true;

		}

		for (int i = 0; i < N; ++i) {
			lightArr = new boolean[N];
			heavyArr = new boolean[N];

			checkLight(i, i);
			checkHeavy(i, i);

			marble[i].setCount();
		}

		System.out.println(findNoMiddle());
	}

	private static void checkLight(int root, int now) {
		for (int next =  0; next < N; ++next) {
			if (marble[now].light[next] == false) continue;
			marble[root].light[next] = true;
			checkLight(root, next);
		}
	}

	private static void checkHeavy(int root, int now) {
		for (int next =  0; next < N; ++next) {
			if (marble[now].heavy[next] == false) continue;
			marble[root].heavy[next] = true;
			checkHeavy(root, next);
		}
	}

	private static int findNoMiddle() {
		int mid = (N + 1) / 2;
		int lightCount = mid - 1;
		int heavyCount = N - mid;

		int noMid = 0;
		for (int i = 0; i < N; ++i) {
			if (marble[i].lightCount > lightCount
				|| marble[i].heavyCount > heavyCount) noMid++;
		}

		return noMid;
	}
}
