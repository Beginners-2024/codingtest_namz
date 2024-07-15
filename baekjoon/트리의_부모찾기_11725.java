package baekjoon;

import java.util.*;
import java.io.*;

public class 트리의_부모찾기_11725 {

	private static int N;

	private static int[] parent;
	private static boolean[] isVisit;
	private static List<Integer>[] nodeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		parent = new int[N+1];
		isVisit = new boolean[N+1];
		nodeList = new ArrayList[N+1];
		for (int i = 0; i <= N; ++i) {
			nodeList[i] = new ArrayList<>();
		}

		StringTokenizer st;
		for (int i = 0; i < N-1; ++i) {
			st = new StringTokenizer(br.readLine());
			int data1 = Integer.parseInt(st.nextToken());
			int data2 = Integer.parseInt(st.nextToken());

			nodeList[data1].add(data2);
			nodeList[data2].add(data1);
		}

		findParent(1);

		StringBuilder sb = new StringBuilder();
		for (int i = 2; i <= N; ++i) {
			sb.append(parent[i]).append("\n");
		}
		System.out.print(sb);
	}

	private static void findParent(int index) {
		isVisit[index] = true;

		for (int i : nodeList[index]) {
			if (!isVisit[i]) {
				parent[i] = index;
				findParent(i);
			}
		}
	}
}
