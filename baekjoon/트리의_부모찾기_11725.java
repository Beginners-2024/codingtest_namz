package baekjoon;

import java.util.*;
import java.io.*;

/**
 * 시간복잡도 : O(N)
 * 		입력 O(N) + 부모찾기 O(N) + 출력 O(N)
 */
public class 트리의_부모찾기_11725 {

	private static int N;

	private static int[] parent;				// 각 노드의 부모를 저장
	private static boolean[] isVisit;			// 각 노드의 방문 여부 확인
	private static List<Integer>[] nodeList;	// 노드와 간선으로 연결된 노드들 저장

	public static void main(String[] args) throws IOException {

		// step 1 - 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		parent = new int[N+1];
		isVisit = new boolean[N+1];
		nodeList = new ArrayList[N+1];
		for (int i = 0; i <= N; ++i) {
			nodeList[i] = new ArrayList<>();
		}

		StringTokenizer st;
		for (int i = 0; i < N-1; ++i) {
			st = new StringTokenizer(br.readLine());
			int data1 = Integer.parseInt(st.nextToken());
			int data2 = Integer.parseInt(st.nextToken());

			// 간선으로 연결된 노드를 리스트에 저장
			nodeList[data1].add(data2);
			nodeList[data2].add(data1);
		}

		// step 2 - 루트 노드인 1번 노드부터 탐색
		findParent(1);

		// step 3 - 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i <= N; ++i) {
			sb.append(parent[i]).append("\n");
		}
		System.out.print(sb);
	}

	/**
	 * 현재 노드의 자식을 탐색하는 방식으로 dfs 진행
	 * @param index 현재 노드의 번호
	 */
	private static void findParent(int index) {
		isVisit[index] = true;

		for (int i : nodeList[index]) {
			if (!isVisit[i]) {
				parent[i] = index;
				findParent(i);
			}
		}
	}
}
