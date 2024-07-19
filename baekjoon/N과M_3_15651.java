package baekjoon;

import java.util.*;
import java.io.*;

public class N과M_3_15651 {

	private static int N, M;
	// private static boolean[] visit;
	private static int[] arr;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[M];
		// visit = new boolean[N];

		dfs(0);
		System.out.print(sb);
	}

	private static void dfs(int depth) {
		if (depth == M) {
			for (int i : arr) {
				sb.append(i).append(' ');
			}
			sb.append('\n');
			return ;
		}

		for (int i = 1; i <= N; ++i) {
			arr[depth] = i;
			dfs(depth+1);
		}
	}
}
