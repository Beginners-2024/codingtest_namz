package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 풀이시간 : 1시간 반
 * 메모리 : 15960 KB
 * 시간 : 216 ms
 * 시간복잡도 : O(N * (N + E))
 *
 * 1. hashMap & List 써서 트리로 구현 => 메모리 초과
 * 		: Integer 객체의 크기와 hashMap 자체적인 공간 할당 구조 때문인듯 함
 * 2. boolean[] 써서 가벼운 구슬, 무거운 구슬 찾고난 후 개수 세기 => 시간초과
 * 		: true인 구슬을 찾기 위해 계속 loop 돌아야함
 * 3. boolean[] 사용하되, check 함수에서 가벼운/무거운 구슬 찾음과 동시에 개수 세기, visit 사용 => 정답
 */
public class 구슬찾기_2617 {
	private static int N, M;
	private static Tree[] marble;
	private static boolean[] lightVisit, heavyVisit;

	private static class Tree {
		boolean[] light, heavy;
		int lightCount = 0, heavyCount = 0;

		Tree(int n) {
			light = new boolean[n];
			heavy = new boolean[n];
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// step 1 - 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		marble = new Tree[N];
		for (int i = 0; i < N; ++i) {
			marble[i] = new Tree(N);
		}

		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int mh = Integer.parseInt(st.nextToken()) - 1;
			int ml = Integer.parseInt(st.nextToken()) - 1;

			marble[mh].light[ml] = true;
			marble[ml].heavy[mh] = true;

		}

		// step 2 - i번째 구슬보다 더 가벼운 / 무거운 구슬의 개수를 센다
		for (int i = 0; i < N; ++i) {
			lightVisit = new boolean[N];
			heavyVisit = new boolean[N];

			checkLight(i);
			checkHeavy(i);
		}

		// step 3 - mid가 될 수 없는 구슬을 찾는다
		System.out.println(findNoMiddle());
	}

	/**
	 * step 2 (1) 가벼운 구슬을 찾는 함수 (bfs)
	 *
	 * @param root i 번째 구슬 (트리에서 부모 노드 역할)
	 */
	private static void checkLight(int root) {
		Queue<Integer> q = new LinkedList<>();

		q.add(root);
		lightVisit[root] = true;

		int count = -1;

		while(!q.isEmpty()) {
			int now = q.poll();
			count++;
			for (int i = 0; i < N; ++i) {
				if (!marble[now].light[i] || lightVisit[i]) continue;

				lightVisit[i] = true;
				q.add(i);
			}
		}
		marble[root].lightCount = count;
	}

	/**
	 * step 2 (2) - 무거운 구슬을 찾는 함수 (bfs)
	 *
	 * @param root
	 */
	private static void checkHeavy(int root) {
		Queue<Integer> q = new LinkedList<>();

		q.add(root);
		heavyVisit[root] = true;

		int count = -1;

		while(!q.isEmpty()) {
			int now = q.poll();
			count++;

			for (int i = 0; i < N; ++i) {
				if (!marble[now].heavy[i] || heavyVisit[i]) continue;

				heavyVisit[i] = true;
				q.add(i);
			}
		}
		marble[root].heavyCount = count;
	}

	/**
	 * step 3 - 중간이 될 수 없는 구슬의 개수를 세는 함수
	 *
	 * 중간이 될 수 없는 조건
	 * 1. 가벼운 구슬의 개수가 이미 중간값보다 많음
	 * 2. 무거운 구슬의 개수가 이미 중간값보다 많음
	 *
	 * @return 중간이 될 수 없는 구슬의 개수
	 */
	private static int findNoMiddle() {
		int mid = (N + 1) / 2;
		int lightCount = mid - 1;
		int heavyCount = N - mid;

		int noMid = 0;
		for (int i = 0; i < N; ++i) {
			if (marble[i].lightCount > lightCount
				|| marble[i].heavyCount > heavyCount) noMid++;
		}

		return noMid;
	}
}
