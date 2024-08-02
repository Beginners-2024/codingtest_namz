package baekjoon;

import java.io.*;
import java.util.*;

public class 구슬찾기_2617 {

	private static int N, M;
	private static Tree[] marble;

	private static Set<Integer> lightSet;
	private static Set<Integer> heavySet;

	private static class Tree {
		Set<Integer> light, heavy;

		Tree() {
			light = new HashSet<>();
			heavy = new HashSet<>();
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		marble = new Tree[N];
		for (int i = 0; i < N; ++i) {
			marble[i] = new Tree();
		}

		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int mh = Integer.parseInt(st.nextToken()) - 1;
			int ml = Integer.parseInt(st.nextToken()) - 1;

			marble[mh].light.add(ml);
			marble[ml].heavy.add(mh);

		}

		for (int i = 0; i < N; ++i) {
			lightSet = new HashSet<>();
			heavySet = new HashSet<>();

			checkLight(i, i);
			checkHeavy(i, i);

			marble[i].light.addAll(lightSet);
			marble[i].heavy.addAll(heavySet);

		}

		System.out.println(findNoMiddle());
	}

	private static void checkLight(int root, int now) {
		if (marble[now].light.isEmpty()) return;

		for (int next : marble[now].light) {
			lightSet.add(next);
			checkLight(root, next);
		}
	}

	private static void checkHeavy(int root, int now) {
		if (marble[now].heavy.isEmpty()) return;

		for (int next : marble[now].heavy) {
			heavySet.add(next);
			checkHeavy(root, next);
		}
	}

	private static int findNoMiddle() {
		int mid = (N + 1) / 2;
		int lightCount = mid - 1;
		int heavyCount = N - mid;

		int noMid = 0;
		for (int i = 0; i < N; ++i) {
			if (marble[i].light.size() > lightCount
				|| marble[i].heavy.size() > heavyCount) noMid++;
		}

		return noMid;
	}
}
