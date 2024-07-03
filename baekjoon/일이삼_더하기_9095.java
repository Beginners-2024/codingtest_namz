package baekjoon;

import java.util.*;
import java.io.*;
public class 일이삼_더하기_9095 {

	private static int T;
	private static int n;
	private static int[] dp = new int[11];	// n에 대한 합의 경우의 수 결과를 저장할 배열
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		// 0~3 에 대한 결과 저장
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;

		for (int i = 0; i < T; ++i) {
			n = Integer.parseInt(br.readLine());
			System.out.println(makeSum(n));
		}
	}

	/**
	 * DP : Botton-up 방식. 4~n까지 경우의 수를 저장
	 *
	 * < 점화식 >
	 *     n의 경우의 수 = (n-1) + (n-2) + (n-3)
	 *
	 * @param n 양의 정수
	 * @return n에 대한 경우의 수
	 */
	public static int makeSum(int n) {
		for (int i = 4; i <= n; ++i) {
				dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
		}
		return dp[n];
	}
}
