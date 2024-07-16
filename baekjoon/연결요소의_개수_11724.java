package baekjoon;

import java.util.*;
import java.io.*;

/**
 * 시간복잡도 : O(N)
 * 		입력 O(N) + 그래프 계산 O(N)
 *
 * 메모리 : 141776 KB (512 MB)
 * 시간 : sout - 612 ms / bw - 720 ms (3 s)
 * 		(출력이 복잡하지 않은 상태에서는 큰 차이 없는 듯?)
 */

public class 연결요소의_개수_11724 {

	private static int N, M;

	private static List<Integer>[] nodeList;	// 간선으로 연결된 노드 리스트
	private static boolean[] isVisit;			// bfs용

	private static int countOfGraph = 0;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// step 1 - 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nodeList = new ArrayList[N+1];
		isVisit = new boolean[N+1];
		for (int i = 1; i <= N; ++i) {
			nodeList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());

			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());

			// 각 노드에 연결된 다른 노드들을 리스트로 갖고 있음
			nodeList[node1].add(node2);
			nodeList[node2].add(node1);
		}

		// step 2 - 그래프 계산. dfs
		for (int i = 1; i <= N; ++i) {
			if (!isVisit[i]) {
				findGraph(i);
				countOfGraph++;
			}
		}

		// step 3 - 출력
		bw.write(countOfGraph + "\n");
		bw.close();
		br.close();
	}

	/**
	 * now node와 연결된 next node, next, next, ... 이런식으로 그래프를 쭉 순회함 => DFS 방식
	 * 순회가 끝나면 하나의 그래프가 끝이 난 것
	 *
	 * @param now 현재 노드의 index
	 */
	private static void findGraph(int now) {
		isVisit[now] = true;
		for (int next : nodeList[now]) {
			if (!isVisit[next])
				findGraph(next);
		}
	}
}
