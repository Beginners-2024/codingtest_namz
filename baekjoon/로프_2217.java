package baekjoon;

import java.util.*;
import java.io.*;

/**
 * 시간복잡도 : O(NlogN)
 * 		입력 O(N) + 정렬 O(NlogN) + 계산 O(N)
 */
public class 로프_2217 {

	private static int N;
	private static List<Integer> rope = new ArrayList<>();
	private static int maxWeight = 0;

	public static void main(String[] args) throws IOException {
		// step 1 - 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; ++i) {
			rope.add(Integer.parseInt(br.readLine()));
		}

		// step 2 - 오름차순 정렬
		Collections.sort(rope);

		// step 3 - 최댓값 계산
		/**
		 * 오름차순으로 정렬된 list에서 현재 로프부터 마지막로프까지는 현재 로프가 버틸 수 있는 최대 중량을 버틸 수 있다
		 * => (현재 로프의 최대 중량) * (현재부터 마지막 로프까지의 개수) 가 max일 때를 찾는다
		 */
		for (int i = 0; i < N; ++i) {
			maxWeight = Math.max(maxWeight, rope.get(i) * (N - i));
		}

		System.out.println(maxWeight);
	}
}
