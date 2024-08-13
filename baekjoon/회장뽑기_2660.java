package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 풀이시간 : 50분
 * 메모리 : 14256 KB
 * 시간 : 128 ms
 * 시간복잡도 : O(n * (n + E))
 * 		E : 간선의 수
 */
public class 회장뽑기_2660 {

	private static int n;
	private static List<Integer>[] members;
	private static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// step 1 - 입력
		n = Integer.parseInt(br.readLine());

		members = new ArrayList[n+1];
		for (int i = 1; i <= n; ++i) {
			members[i] = new ArrayList<>();
		}

		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine());

			int m1 = Integer.parseInt(st.nextToken());
			int m2 = Integer.parseInt(st.nextToken());

			if (m1 == -1) break;

			members[m1].add(m2);
			members[m2].add(m1);
		}

		// step 2 - 친구 찾기
		int min = 50;
		int[] depth = new int[n+1];
		for (int i = 1; i <= n; ++i) {
			visit = new boolean[n+1];

			depth[i] = makeFriend(i);
			min = Math.min(min, depth[i]);
		}

		// step 3 - 출력
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();

		int count = 0;
		for (int i = 1; i < n+1; ++i) {
			if (depth[i] != min) continue;

			count++;
			sb2.append(i).append(" ");
		}
		sb1.append(min).append(' ').append(count);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb1.toString());
		bw.write("\n");
		bw.write(sb2.toString());

		br.close();
		bw.close();
	}

	/**
	 * step 2 - 친구찾는 메서드 (BFS)
	 *
	 * 몇다리 건넌 친구인지 어떻게 계산할까 하다가 최단거리 생각나서 BFS 사용했음
	 * => 그래프의 depth 구하는 상황에서는 BFS 활용하자 ~
	 *
	 * @param me 나
	 * @return 가장 먼 친구와의 거리
	 */
	private static int makeFriend(int me) {
		Queue<Integer> q = new LinkedList<>();
		q.add(me);
		visit[me] = true;

		// n번 친구와의 거리를 저장할 배열
		int[] depth = new int[n+1];
		depth[me] = 0;

		int maxDepth = 0;

		while(!q.isEmpty()) {
			int now = q.poll();
			for (int next : members[now]) {
				if (visit[next] == true) continue;
				q.add(next);
				visit[next] = true;
				depth[next] = depth[now] + 1;
				maxDepth = Math.max(depth[next], maxDepth);
			}
		}
		return maxDepth;
	}

}
