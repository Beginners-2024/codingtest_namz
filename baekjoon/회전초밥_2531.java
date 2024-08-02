package baekjoon;

import java.util.*;
import java.io.*;

/**
 * 풀이시간 : 25분
 * 메모리 : 21756 KB
 * 시간 : 252 ms
 * 시간복잡도 : O(N)
 */
public class 회전초밥_2531 {

	private static int N, d, k, c;
	private static int[] sushi;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// step 1 - 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		sushi = new int[N];
		for (int i = 0; i < N; ++i) {
			sushi[i] = Integer.parseInt(br.readLine());
		}

		// step 2 - 최대 종류 계산
		System.out.println(eat());
	}

	/**
	 * step 2 - 최대 종류 계산 (투포인터)
	 *
	 * K개의 스시에서 start 뺘고, end 더하면서 한바퀴를 돎.
	 * 중복 확인은 hashMap 사용 (동일한 종류의 초밥에 대한 개수도 카운트 해야하므로 map)
	 *
	 * @return 최대 종류 개수
	 */
	private static int eat() {
		int maxType = 0;

		int start = 0;
		int end = k;

		// <종류, 개수>
		HashMap<Integer, Integer> sushiType = new HashMap<>();

		// 투포인터 loop 시작 전 초기값 세팅
		for (int i = start; i < end; ++i) {
			sushiType.put(sushi[i], sushiType.getOrDefault(sushi[i], 0) + 1);
		}
		maxType = sushiType.size();
		if (!sushiType.containsKey(c)) maxType++;

		while (start < N) {
			start %= N;
			end %= N;

			// start 제거
			if (sushiType.get(sushi[start]) == 1) sushiType.remove(sushi[start]);
			else sushiType.replace(sushi[start], sushiType.get(sushi[start]) - 1);
			start++;

			// end 추가
			sushiType.put(sushi[end], sushiType.getOrDefault(sushi[end], 0) + 1);
			end++;

			int type = sushiType.size();

			// 쿠폰
			if (!sushiType.containsKey(c)) type++;

			maxType = Math.max(maxType, type);
		}

		return maxType;
	}
}
