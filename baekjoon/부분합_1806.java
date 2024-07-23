package baekjoon;

import java.util.*;
import java.io.*;

/**
 * 소요시간 : 15분
 * 시간복잡도 : O(N)
 * 			입력 O(N) + makeSum O(N)
 * 메모리 : 23864 KB
 * 시간 : 304 ms
 */

public class 부분합_1806 {
	private static int N, S;
	private static int[] num;

	private static int minLength = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// step 1 - 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		// 주의 : 문제에서 주어진 수열의 연속된 수에서 찾아야하므로 정렬을 하면 안됨
		num = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		// step 2 - sum 찾기
		makeSum();

		// step 3 - 출력
		System.out.println(minLength);
	}

	// 투포인터 방식
	private static void makeSum() {
		int start = 0, end = 0;
		int sum = num[0];

		while (start < N) {
			if (sum < S && end < N-1) {
				end++;
				sum += num[end];
			}
			else if (sum >= S) {
				minLength = Math.min(minLength, end - start + 1);
				sum -= num[start];
				start++;
			}
			else
				start++;
		}

		if (minLength == Integer.MAX_VALUE) minLength = 0;
	}
}
