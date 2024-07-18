package baekjoon;

import java.util.*;
import java.io.*;

public class N과M_1_15649 {

	private static int N, M;

	private static boolean[] visit;
	private static int[] arr;

	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visit = new boolean[N];
		arr = new int[M];

		dfs(0);
		System.out.print(sb);
	}

	private static void dfs(int depth) {
		if (depth == M) {
			for (int val : arr)
				sb.append(val).append(' ');
			sb.append('\n');
			return;
		}

		for (int i = 0; i < N; ++i) {
			if (!visit[i]) {
				visit[i] = true;
				arr[depth] = i + 1;
				dfs(depth + 1);
				visit[i] = false;
			}
		}
	}
}
