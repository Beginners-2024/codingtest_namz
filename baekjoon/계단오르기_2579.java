package baekjoon;

import java.io.*;
import java.util.*;

public class 계단오르기_2579 {

	private static Integer[] stair;		// 계딘에 쓰여 있는 점수 배열
	private static Integer[] score;		// n번째 계단에 도착했을 때 얻는 점수 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int stairNum = Integer.parseInt(br.readLine());
		stair = new Integer[stairNum + 1];
		score = new Integer[stairNum + 1];

		for (int i = 1; i <= stairNum; ++i) {
			stair[i] = Integer.parseInt(br.readLine());
		}

		System.out.println(climbStair(stairNum));
	}

	/**
	 * DP : Bottom-up 방식. 1->n번쨰 계단까지 점수를 계산
	 *
	 * < 점화식 >
	 * 도착점 : n , 조건 : 3 계단 이상 연속할 수 없음
	 *
	 * case 1 : n-1번째 계단 밟았으면 무조건 n-3번째 계단을 밟음
	 * 		=> 점수 = (n-3) 까지의 점수 + (n-1) 계단에 적힌 점수 + (n) 계단에 적힌 점수
	 * case 2 : n-2번째 계단 밟았으면 그 이전 단계는 상관 없음 (모든 경우가 다 가능)
	 * 		=> 점수 = (n-2) 까지의 점수 + (n) 계단에 적힌 점수
	 *
	 * case 1, case 2 중 최댓값이 n 번째 계단에 도착하였을 때 얻을 수 있는 점수의 최댓값
	 *
	 * @param n 자연수 n (도착해야할 계단 변호)
	 * @return	n 번째 계단에 도착하였을 때 얻을 수 있는 점수의 최댓값
	 */
	public static int climbStair(int n) {
		score[1] = stair[1];

		if (n > 1) score[2] = stair[1] + stair[2];
		if (n > 2) score[3] = Math.max((stair[2] + stair[3]), (stair[1] + stair[3]));
		if (n > 3) {
			for (int i = 4; i <= n; ++i) {
				score[i] = Math.max((stair[i] + stair[i-1] + score[i-3]), (stair[i] + score[i-2]));
			}
		}

		return score[n];
	}
}
