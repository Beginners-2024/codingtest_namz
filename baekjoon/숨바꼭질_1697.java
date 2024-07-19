package baekjoon;

import java.util.*;
import java.io.*;

/**
 * BFS로 경로찾기 문제 => map을 만들어 놓고 각 위치에 도달할 수 있는 최단시간을 기록한다
 *
 * 소요시간 : 30분
 *
 * 시간복잡도 : O(400004)
 * 메모리 : 20740 KB
 * 시간 : 176 ms
 */
public class 숨바꼭질_1697 {

	private static int N, K;
	private static int[] arr = new int[100001];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		bfs(N);
		System.out.println(arr[K]);
	}

	/**
	 * BFS 시간복잡도 : O(정점 + 간선)
	 * 		정점 : 100001
	 * 		간선 : 300003 (정점 당 3개씩)
	 * 	=> 최악의 경우 O(400004)
	 *
	 * 	< BFS 최단거리 >
	 * 	map을 만들고, 각 pos에 도달할 수 있는 최단시간을 저장
	 *
	 * @param subin 수빈이의 시작 위치
	 */
	private static void bfs(int subin) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visit = new boolean[100001];

		q.add(subin);
		visit[subin] = true;

		while (!q.isEmpty()) {
			int nowPos = q.poll();

			// 동생 찾았으면 끝
			if (nowPos == K) break;

			for (int i = -1; i < 2; ++i) {

				// 위치 선택 (-1, 1, 2배)
				int nextPos;

				if (i == 0) nextPos = nowPos*2;
				else nextPos = nowPos + i;

				if (!isValid(nextPos) || visit[nextPos]) continue;

				visit[nextPos] = true;
				arr[nextPos] = arr[nowPos] + 1;

				// 동생 찾았으면 끝
				if (nextPos == K) break;

				q.add(nextPos);
			}
		}
	}

	private static boolean isValid(int pos) {
		return 0 <= pos && pos <= 100000;
	}
}
