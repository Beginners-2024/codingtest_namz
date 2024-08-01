package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 풀이 시간 : 1시간
 * 메모리 : 25608 KB (boolean[]) / 35596 KB (hashSet) (제한 : 32 MB)
 * 시간 : 328 ms (boolean[]) / 424 ms (hashSet)
 * 시간복잡도 : O(N)
 *
 * boolean[100001]
 * 		boolean : 1 byte
 * 		boolean [N] : N byte
 * 		boolean [100001] : 100,001 byte => 100 KB
 * HashSet
 * 		Integer(객체) : 약 24 byte
 *		100,001 : 100,001 * 24 byte => 2,400 KB
 */

public class ListOfUniqueNumber_13144 {
	private static int N;
	private static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// step 1 - 입력
		N = Integer.parseInt(br.readLine());

		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// step 2 - 계산 & 출력
		System.out.print(select());
	}

	/**
	 * step 2 - 배열 계산하는 함수 (투포인터)
	 *
	 * 1. 중복되는 수가 나오기 전까지 end++;
	 * 2. 중복되는 수가 나오면, start부터 end까지 수열의 개수를 구함
	 * 		수열의 종류 : start, start+1, start+2, ... , start+end
	 * 		수열의 개수 : end - start
	 *
	 * @return 수열의 최대 개수
	 */
	private static long select() {
		long count = 0;

		boolean[] used = new boolean[100001];

		int start = 0;
		int end = 0;

		while (start < N) {
			if (end == N || used[arr[end]]) {
				count += (end - start);
				used[arr[start]] = false;
				start++;
			} else {
				used[arr[end]] = true;
				end++;
			}

		}

		return count;
	}

	/*
	private static long select() {
		HashSet<Integer> numSet = new HashSet<>();

		int start = 0;
		int end = 0;

		long count = 0;

		while (start < N) {
			if (end == N || numSet.contains(arr[end])) {
				count += (end - start);
				numSet.remove(arr[start]);
				start++;
			} else {
				numSet.add(arr[end]);
				end++;
			}
		}

		return count;
	}
	*/

}
