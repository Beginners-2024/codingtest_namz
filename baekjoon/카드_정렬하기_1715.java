package baekjoon;

import java.util.*;
import java.io.*;

/**
 * 소요시간 : 10분
 * 메모리 : 25320 KB
 * 시간 : 380 ms
 */
public class 카드_정렬하기_1715 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < N; ++i) {
			pq.add(Integer.parseInt(br.readLine()));
		}

		int sum = 0;
		while (pq.size() > 1) {
			int n1 = pq.poll();
			int n2 = pq.poll();

			sum += n1 + n2;

			pq.add(n1 + n2);
		}

		System.out.println(sum);
	}
}
