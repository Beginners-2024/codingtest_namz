package baekjoon;

import java.io.*;
import java.util.*;
public class N과M_4_15652 {

	private static int N, M;
	private static int[] arr;
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[M];

		dfs(0, 1);
		System.out.print(sb);
	}

	private static void dfs(int depth, int index) {
		if (depth == M) {
			for (int i : arr) {
				sb.append(i).append(' ');
			}
			sb.append('\n');
			return ;
		}

		for (int i = index; i <= N; ++i) {
			arr[depth] = i;
			dfs(depth+1, i);
		}
	}
}
