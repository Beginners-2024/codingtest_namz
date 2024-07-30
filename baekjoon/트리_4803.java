package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 풀이시간 : 45분
 *
 * 메모리 : 62844 KB
 * 시간 : 488 ms
 *
 * 시간복잡도 : O(T *(N + M))
 */
public class 트리_4803 {

	private static boolean[] visit;
	private static List<Integer>[] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int numberOfCase = 1;
		while (true) {
			st = new StringTokenizer(br.readLine());

			// step 1 - n & m 입력
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			// 테스트케이스 종결 조건
			if (n == 0 && m == 0) break;

			// 데이터 초기화
			tree = new ArrayList[n+1];
			visit = new boolean[n+1];
			for (int i = 1; i < n+1; ++i) {
				tree[i] = new ArrayList<>();
			}

			// step 2 - 간선 입력
			for (int i = 0; i < m; ++i) {
				st = new StringTokenizer(br.readLine());

				int node1 = Integer.parseInt(st.nextToken());
				int node2 = Integer.parseInt(st.nextToken());

				tree[node1].add(node2);
				tree[node2].add(node1);
			}

			// step 3 - 트리 계산
			int count = 0;
			for (int i = 1; i < n+1; ++i) {
				if (visit[i]) continue;

				if (findTree(i)) count++;
 			}

			// step 4 - 출력
			sb.append("Case ").append(numberOfCase);
			if (count == 1) {
				sb.append(": There is one tree.\n");
			} else if (count > 1) {
				sb.append(": A forest of ").append(count).append(" trees.\n");
			} else {
				sb.append(": No trees.\n");
			}
			numberOfCase++;
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());

		bw.close();
		br.close();
	}

	/**
	 * step 3 - 트리 여부 검사하는 함수 (BFS)
	 *
	 * 노드를 순회하면서 간선의 개수를 센다
	 *
	 * 기준 : edge = node - 1
	 *
	 * @param i : 시작 노드 번호
	 * @return true : tree / false : tree 아님
	 */
	private static boolean findTree(int i) {
		Queue<Integer> q = new LinkedList<>();
		q.add(i);
		visit[i]= true;

		int edge = 0;
		int node = 0;

		while (!q.isEmpty()) {
			int now = q.poll();
			node++;

			for (int next : tree[now]) {
				edge++;
				if (!visit[next]) q.add(next);

				visit[next] = true;
			}
		}

		// node를 순회하면서 edge가 중복으로 count 됐으므로 /2
		return (edge/2) == node-1 ? true : false;
	}

}
