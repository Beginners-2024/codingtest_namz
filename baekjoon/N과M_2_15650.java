package baekjoon;

import java.util.*;
import java.io.*;

public class N과M_2_15650 {

	private static int N, M;
	private static int[] arr;
	private static boolean[] visit;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[M];
		visit = new boolean[N];

		dfs(0, 0);

		System.out.print(sb);
	}

	private static void dfs(int depth, int before) {
		if (depth == M) {
			for (int i : arr)
				sb.append(i).append(' ');
			sb.append('\n');
			return ;
		}

		for (int i = before; i < N; ++i) {
			if (!visit[i]) {
				visit[i] = true;
				arr[depth] = i + 1;
				dfs(depth+1, i+1);
				visit[i] = false;
			}
		}
	}
}
