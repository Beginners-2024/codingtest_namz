package baekjoon;

import java.io.*;
import java.util.*;

public class N과M_7_15656 {
	private static int N, M;
	private static int[] num;
	private static int[] combi;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		num = new int[N];
		combi = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(num);

		dfs(0);
		System.out.print(sb);
	}

	private static void dfs(int depth) {
		if (depth == M) {
			for (int i : combi)
				sb.append(i).append(' ');
			sb.append('\n');
			return;
		}

		for (int i = 0; i < N; ++i) {
			combi[depth] = num[i];
			dfs(depth+1);
		}
	}
}
