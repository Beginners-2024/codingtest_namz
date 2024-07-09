package baekjoon;

import java.util.*;
import java.io.*;

public class 수찾기_1920 {

	private static int N, M;
	private static int[] numA;
	private static int[] numX;

	public static void main(String[] args) throws IOException {
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

		Arrays.sort(numA);

		for (int i = 0; i < M; ++i) {
			if (isExist(numX[i]))
				System.out.println("1");
			else
				System.out.println("0");
		}
	}

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
