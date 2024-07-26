package baekjoon;

import java.io.*;
import java.util.*;

public class 회전하는_큐_1021 {
	private static List<Integer> deque;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		deque = new ArrayList<>(N);
		for (int i = 0; i < N; ++i) {
			deque.add(i);
		}

		int answer = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; ++i) {
			int num = Integer.parseInt(st.nextToken()) -1;
			answer += findNum(num);
		}
	}

	private static int findNum(int num) {
		int count = 0;


		return count;
	}
}
