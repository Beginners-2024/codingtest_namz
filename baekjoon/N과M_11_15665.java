package baekjoon;

import java.util.*;
import java.io.*;

public class N과M_11_15665 {
	private static int N, M;
	private static int[] combi;
	private static int[] num;
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		combi = new int[M];
		num = new int[N];

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
			return ;
		}

		int before = 0;
		for (int i = 0; i < N; ++i) {
			if (before == num[i]) continue;

			combi[depth] = num[i];
			before = num[i];
			dfs(depth+1);
		}
	}
}
