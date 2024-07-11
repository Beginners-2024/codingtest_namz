package baekjoon;

import java.util.*;
import java.io.*;

/**
 * - 메모리 : 172716 KB
 * - 시간 : 1028 ms
 * - 시간복잡도 : O(N + M)
 * 		입력 O(N) + 출력 O(M)
 */
public class 숫자카드2_10816 {

	private static int N, M;

	// 중복제거, 카드 당 개수 카운트를 위해 hashMap 사용
	private static HashMap<Integer, Integer> card = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		// step 1 - 카드 목록 & 개수 저장
		/**
		 * HashMap.getOrDefault : O(1)
		 */
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			int key = Integer.parseInt(st.nextToken());

			// 카드가 중복이면 value++, 처음이면 value = 1
			card.put(key, card.getOrDefault(key, 0) + 1);
		}

		M = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		// step 2 - 찾아야할 카드 map에서 바로 찾고 string 만듦
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; ++i) {
			int key = Integer.parseInt(st.nextToken());

			// card map에 있으면 value, 없으면 0 반환
			sb.append(card.getOrDefault(key, 0)).append(' ');
		}

		System.out.println(sb);
	}
}
