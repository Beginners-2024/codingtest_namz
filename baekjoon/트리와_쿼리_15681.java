package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 소요시간 : 30분
 *
 * 메모리 : 93408 KB
 * 시간 : 780 ms
 *
 * 시간복잡도 : O(N + Q)
 * 		입력 O(N)
 * 		makeTree O(N)
 * 		checkSubTree O(N)
 * 		출력 O(Q)
 */
public class 트리와_쿼리_15681 {

	private static List<Integer>[] tree;	// 트리
	private static List<Integer>[] graph;	// 트리로 구성 전 그래프
	private static int[] subTree;			// 각 노드마다 서브트리에 속해있는 정점의 개수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// step 1 - 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		// 초기화
		tree = new ArrayList[N+1];
		graph = new ArrayList[N+1];
		subTree = new int[N+1];

		for (int i = 1; i < N+1; ++i) {
			tree[i] = new ArrayList<>();
			graph[i] = new ArrayList<>();
		}

		// 그래프 입력
		for (int i = 0; i < N-1; ++i) {
			st = new StringTokenizer(br.readLine());

			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());

			graph[node1].add(node2);
			graph[node2].add(node1);
		}

		// step 2 - 트리 만들기
		makeTree(R, 0);

		// step 3 - 서브트리에 속한 정점 계산하기
		checkSubTree(R);

		// step 4 - 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; ++i) {
			int q = Integer.parseInt(br.readLine());

			sb.append(subTree[q]).append("\n");
		}

		System.out.println(sb);
	}

	/**
	 * step 2 - tree 만드는 함수 (재귀)
	 *
	 * graph를 순회하면서 tree를 만든다
	 * (graph는 간선이 중복되어 있는 형태임. 이 중복을 없애는게 트리의 핵심)
	 *
	 * @param now 현재 노드 번호
	 * @param parent 부모 노드 번호
	 */
	private static void makeTree(int now, int parent) {
		for (int next : graph[now]) {
			if (next == parent) continue;

			tree[now].add(next);
			makeTree(next, now);
		}
	}

	/**
	 * step 3 - 서브트리에 속한 정점 개수 계산하는 함수 (재귀)
	 *
	 * root의 child가 있으면, 트리의 깊이 끝까지 재귀를 통해 순회하여 child가 자식으로 갖고있는 정점의 개수를 센다
	 * 그 값을 subTree 배열에 저장한다.
	 *
	 * @param root 서브트리 정점의 개수를 계산할 root 노드의 번호
	 */
	private static void checkSubTree(int root) {
		subTree[root] = 1;

		for (int child : tree[root]) {
			checkSubTree(child);
			subTree[root] += subTree[child];
		}
	}
}
