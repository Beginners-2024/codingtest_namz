package baekjoon;

import java.util.*;
import java.io.*;
public class 일로_만들기_1463 {
	private static int N;
	private static int[] d = new int[1000001];	// N에 대한 연산 횟수를 담을 배열

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		d[0] = 0;
		d[1] = 0;
		System.out.println(dp(N));
	}

	/**
	 * DP : Top-Down 방식. N -> 1 까지 내려감
	 *
	 * < 점화식 >
	 * N에 대한 연산 횟수 = 이전 숫자의 연산 횟수 + 1
	 * 이때, 이전 횟수는 N/3, N/2, N-1 중 하나가 된다
	 *
	 * @param N : 연산횟수를 구할 정수
	 * @return 정수 N에 대한 연산 횟수
	 */
	public static int dp(int N) {
		if (N > 1 && d[N] == 0) {
			if (N % 6 == 0) {
				d[N] = Math.min(dp(N - 1), Math.min(dp(N / 3), dp(N / 2))) + 1;
			} else if (N % 3 == 0) {
				d[N] = Math.min(dp(N / 3), dp(N - 1)) + 1;
			} else if (N % 2 == 0) {
				d[N] = Math.min(dp(N / 2), dp(N - 1)) + 1;
			} else {
				d[N] = dp(N - 1) + 1;
			}
		}

		return d[N];
	}
}
