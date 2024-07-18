package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 시간복잡도 : O(M + N)
 * 		초기화 O(N) + 입력 O(M) + dfs / bfs O(N + M)
 * 메모리 : 14136 KB
 * 시간 : 104 ms (BFS랑 DFS랑 큰 차이 없음)
 */

public class 바이러스_2606 {

	private static int N, M;

	private static List<Integer>[] nodeList;
	private static boolean[] visit;

	private static int virus = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// step 1 - 입력
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		visit = new boolean[N+1];
		nodeList = new ArrayList[N+1];

		for (int i = 1; i <= N; ++i) {
			nodeList[i] = new ArrayList<>();
		}

		StringTokenizer st;
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());

			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());

			nodeList[node1].add(node2);
			nodeList[node2].add(node1);
		}

		// step 2 - 계산
		bfs(1);

		// dfs(1);

		System.out.println(--virus);
	}

	private static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);

		visit[start] = true;

		while (!q.isEmpty()) {
			int now = q.poll();

			virus++;

			for (int next : nodeList[now]) {
				if (!visit[next]) {
					visit[next] = true;
					q.add(next);
				}
			}
		}
	}

	private static void dfs(int now) {
		visit[now] = true;

		virus++;

		for (int next : nodeList[now]) {
			if (!visit[next]) {
				visit[next] = true;
				dfs(next);
			}
		}
	}
}
