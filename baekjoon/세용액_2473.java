package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 풀이시간 : 40분
 * 메모리 : 17484 ms
 * 시간 : 260 ms
 * 시간복잡도 : O(N*N)
 *
 * 방법 1 : 모든 조합 찾기 => O(N*N*N) 시간초과
 * 방법 2 : 투포인터 => O(N*N) 정답
 */
public class 세용액_2473 {
	private static int N;
	private static int[] solution;

	private static int[] arr;
	private static long min;
	// private static Pair min;
	//
	// private static class Pair {
	// 	long sum;
	// 	int[] solutions;
	//
	// 	Pair() {
	// 		solutions = new int[3];
	// 		sum = Long.MAX_VALUE;
	// 	}
	// 	void setMin(long s, int x, int y, int z) {
	// 		this.sum = s;
	//
	// 		solutions[0] = x;
	// 		solutions[1] = y;
	// 		solutions[2] = z;
	// 	}
	//
	// 	void sort() {
	// 		Arrays.sort(solutions);
	// 	}
	//
	// 	void print() {
	// 		StringBuilder sb = new StringBuilder();
	// 		sb.append(solutions[0]).append(' ').append(solutions[1]).append(' ').append(solutions[2]);
	// 		System.out.println(sb.toString());
	// 	}
	// }
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// step 1 - 입력 O(N)
		N = Integer.parseInt(br.readLine());

		solution = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			solution[i] = Integer.parseInt(st.nextToken());
		}

		// step 2 - 정렬 O(NlogN)
		Arrays.sort(solution);

		// step 3 - 0에 가까운 값 찾기 O(N*N)
		arr = new int[3];
		min = Long.MAX_VALUE;
		makeSum();

		// step 4 - 정렬 & 출력
		Arrays.sort(arr);
		System.out.println(arr[0] + " " + arr[1] + " " + arr[2]);

		// min = new Pair();
		// makeSum(0, 0);
		//
		// min.sort();
		// min.print();

	}

	/**
	 * step 3 - 0에 가까운 경우를 찾는 메서드 (투포인터)
	 *
	 * 0번 용액이 i 일 때, 투포인터를 사용해서 나머지 2개의 용액(second, third)를 정함
	 *
	 * if (합이 음수) 더 큰 수를 더해야함. second++
	 * if (합이 양수) 더 작은 수를 더해야함. third--
	 * if (합이 0) 최고의 경우를 찾았음. 종료
	 *
	 */
	private static void makeSum() {
		for (int i = 0; i < N-2; ++i) {
			int second = i+1;
			int third = N-1;

			while (second < third) {
				long sum = (long)solution[i] + (long)solution[second] + (long)solution[third];

				if (min > Math.abs(sum)) {
					arr[0] = solution[i];
					arr[1] = solution[second];
					arr[2] = solution[third];

					min = Math.abs(sum);
				}

				if (sum < 0) second++;
				else if (sum > 0) third--;
				else break;
			}
		}
	}

	// private static void makeSum(int depth, int index) {
	// 	if (depth == 3) {
	// 		long sum = Math.abs(arr[0] + arr[1] + arr[2]);
	//
	// 		// System.out.println(Arrays.toString(arr));
	//
	// 		if (min.sum > sum) min.setMin(sum, arr[0], arr[1], arr[2]);
	//
	// 		return ;
	// 	}
	//
	// 	for (int i = index; i < N; ++i) {
	// 		arr[depth] = solution[i];
	// 		makeSum(depth + 1, i + 1);
	// 	}
	// }
}
