package baekjoon;

import java.io.*;
import java.util.*;

/**
 * Array.sort 사용시
 * - 메모리 : 265284 KB
 * - 시간 : 1776 ms
 * - 시간복잡도 : O(n^2) or O(nlogn)
 * 		입력 O(N) + 정렬 O(n^2) or O(nlogn) + hashMap 세팅 O(N) + 출력 O(N)
 *
 * Collections.sort 사용시
 * - 메모리 : 251444 KB
 * - 시간 : 1988 ms
 * - 시간복잡도 : O(nlogn)
 * 		입력 O(N) + 정렬 O(nlogn) + hashMap 세팅 O(N) + 출력 O(N)
 */

public class 좌표압축_18870 {

	private static int N;

	// 입력값을 저장한 배열. 하나는 오름차순으로 정렬
	private static int[] numArr;
	private static ArrayList<Integer> numArrSorted;

	// 중복값 제거를 위한 hashmap
	private static HashMap<Integer, Integer> numMap = new HashMap<>();

	public static void main(String[] args) throws IOException {
		// step 1 : input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		numArr = new int[N];		// 정렬 안한 버전
		numArrSorted = new ArrayList<>(N);	// 정렬한 버전

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			numArr[i] = Integer.parseInt(st.nextToken());
			numArrSorted.add(numArr[i]);
		}

		// step 2 - 오름차순 정렬
		/**
		 * Arrays.sort : 평균 O(nlogn) / 최악 O(n^2)
		 * Collections.sort : 평균, 최악 O(nlogn)
		 */
		Collections.sort(numArrSorted);

		// step 3 - 작은 수부터 value를 증가시키며 map에 저장
		/**
		 * hashMap.containsKey : O(1)
		 */
		int value = 0;
		for (Integer key : numArrSorted) {
			if (!numMap.containsKey(key))
				numMap.put(key, value++);
		}

		// step 4 - 정렬 안한 배열을 기준으로 map에서 value를 가져옴
		/**
		 * hashMap.get : O(1)
		 */
		StringBuilder sb = new StringBuilder();
		for (int key : numArr) {
			sb.append(numMap.get(key)).append(' ');
		}

		// step 5 - output
		System.out.println(sb);
	}
}
