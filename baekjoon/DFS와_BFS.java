package baekjoon;

import java.util.*;
import java.io.*;

/**
 * 시간복잡도 : O(N + M + NlogN)
 * 		초기화 O(N) + 노드 입력 O(M) + 정렬 O(NlogN) + DFS O(N + M) + BFS O(N + M)
 * 		BFS / DFS : visit을 통해서 모든 노드(N)와 간선(M)에 한번씩만 방문한다
 * 메모리 : 20380 KB (128 MB)
 * 시간 : 280 ms (2 s)
 */
public class DFS와_BFS {

	private static int N, M, V;

	private static List<Integer>[] nodeList;
	private static boolean[] visit;

	private static StringBuilder sbDFS = new StringBuilder();
	private static StringBuilder sbBFS = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// step 1 - input
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		nodeList = new ArrayList[N+1];

		// 간선이 없는 노드도 있겠지만 모든 노드에 대해 초기화 안하면 nullPointer exception 발생
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

		// step 2 - sort : 번호가 작은 노드부터 방문하기 위함
		for (int i = 1; i <= N; ++i) {
			if (nodeList[i].size() == 0) continue;

			Collections.sort(nodeList[i]);
		}

		// step 3 - DFS
		visit = new boolean[N+1];
		dfs(V);

		// step 4 - BFS
		visit = new boolean[N+1];
		bfs(V);

		// step 5 - output
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
