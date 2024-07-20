package baekjoon;

import java.io.*;
import java.util.*;
public class N과M_9_15663 {

	private static int N, M;
	private static int[] num;
	private static int[] combi;
	private static boolean[] visit;
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		num = new int[N];
		combi = new int[M];
		visit = new boolean[N];

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

		int before = 0;	// 같은 depth에서만 중복선택 안하면 됨.
						// 다음 depth에서는 중복 가능하니까 before를 dfs의 인자로 넘길 필요가 없음
		for (int i = 0; i < N; ++i) {
			if (visit[i]) continue;

			if (before != num[i]) {
				visit[i] = true;
				combi[depth] = num[i];
				before = num[i];
				dfs(depth+1);
				visit[i] = false;
			}
		}
	}
}
