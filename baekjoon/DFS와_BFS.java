package baekjoon;

import java.util.*;
import java.io.*;

public class DFS와_BFS {

	private static int N, M, V;

	private static List<Integer>[] nodeList;
	private static boolean[] visit;

	private static StringBuilder sbDFS = new StringBuilder();
	private static StringBuilder sbBFS = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		nodeList = new ArrayList[N+1];

		for (int i = 1; i <= N; ++i) {
			nodeList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());

			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());

			nodeList[node1].add(node2);
			nodeList[node2].add(node1);
		}

		for (int i = 1; i <= N; ++i) {
			if (nodeList[i].size() == 0) continue;

			Collections.sort(nodeList[i]);
		}

		visit = new boolean[N+1];
		dfs(V);

		visit = new boolean[N+1];
		bfs(V);

		System.out.println(sbDFS);
		System.out.println(sbBFS);
	}

	private static void dfs(int now) {
		visit[now] = true;

		sbDFS.append(now + " ");

		for (int next : nodeList[now]) {
			if (!visit[next])
				dfs(next);
		}
	}

	private static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);

		visit[start] = true;

		while (!q.isEmpty()) {
			int now = q.poll();

			sbBFS.append(now + " ");

			for (int next : nodeList[now]) {
				if (visit[next]) continue;

				visit[next] = true;
				q.add(next);
			}
		}
	}
}
