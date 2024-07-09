package baekjoon;

import java.util.*;
import java.io.*;

/**
 * - 메모리 : 49088 KB
 * - 시간 : 1288 ms
 * - 시간복잡도 : O(NlogN + Mlogn)
 * 		입력 O(N + M) + 정렬 O(nlogn) or O(n^2) + 이진탐색 O(logN) * M번 (M)
 */
public class 수찾기_1920 {

	private static int N, M;
	private static int[] numA;
	private static int[] numX;

	public static void main(String[] args) throws IOException {
		// step 1 - input : O(N + M)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		numA = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			numA[i] = Integer.parseInt(st.nextToken());
		}

		M = Integer.parseInt(br.readLine());
		numX = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; ++i) {
			numX[i] = Integer.parseInt(st.nextToken());
		}

		// step 2 - sort(ASC) : O(nlogn) or O(n^2)
		Arrays.sort(numA);

		// step 3 - binary search : M번 * O(logn)
		for (int i = 0; i < M; ++i) {
			if (isExist(numX[i]))
				System.out.println("1");
			else
				System.out.println("0");
		}
	}

	/**
	 * 이분탐색 시간복잡도 : O(logn)
	 *
	 * 중앙값을 기준으로 보다 작으면 앞에서 탐색, 크면 뒤에서 탐색하는 과정을 반복한다
	 * index가 start <= mid <= end 가 아니게 되는 순간 탐색은 종료된다
	 *
	 * @param x 탐색할 key value
	 * @return
	 */
	private static boolean isExist(int x) {
		int start = 0;
		int end = N-1;

		while (start <= end) {
			int mid = (start + end)/2;
			if (numA[mid] > x) {
				end = mid-1;
			} else if (numA[mid] < x) {
				start = mid+1;
			} else {
				return true;
			}
		}

		return false;
	}


}
