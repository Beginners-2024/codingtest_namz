package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 소요시간 : 20분
 * 시간복잡도 : O(NlogN)
 * 			입력 O(N) + 정렬 O(NlogN) + findMin O(N)
 * 메모리 : 28472 KB
 * 시간 : 392 ms
 */
public class 수고르기_2230 {

	private static int N, M;
	private static int[] num;

	private static int minResult = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// step 1 - 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		num = new int[N];
		for (int i = 0; i < N; ++i) {
			num[i] = Integer.parseInt(br.readLine());
		}

		// step 2 - 정렬
		Arrays.sort(num);

		// step 3 - 최솟값 차이 찾기
		findMin();

		// step 4 - 출력
		System.out.println(minResult);
	}

	/**
	 * index : start, end로 관리
	 *
	 * "end의 숫자 - start의 숫자 = 차이" 리고 하자
	 *
	 * 1. 차이 < M && end 아직 증가 가능 : end++
	 * 		(start가 고정되어 있는 상황에서 end가 커질수록 차이는 더 커진다)
	 * 2. 차이 >= M : start++
	 * 		(end가 고정되어 있는 상황에서 start가 커질수록 차이는 작아진다)
	 * 3. 그 외(1번에 속하지만 end가 증가하지 못하는 경우) : start++
	 * 		(근데 이 경우는 엣지 케이스. M 이상의 차이가 나오지 않는 경우임. 문제에서는 M이상인 차이가 존재함을 보장하였음)
	 */
	public static void findMin() {
		int start = 0, end = 0;

		while (start < N) {
			int result = num[end] - num[start];

			// 가장 최소의 차이는 M 이므로 빠른 종료
			if (result == M) {
				minResult = M;
				break;
			}

			// start, end 증가시키기
			if (result < M && end < N-1) end++;
			else if (result >= M) {
				minResult = Math.min(minResult, result);
				start++;
			} else {
				start++;
			}
		}
	}
}
