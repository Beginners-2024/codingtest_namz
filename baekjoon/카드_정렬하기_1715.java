package baekjoon;

import java.util.*;
import java.io.*;

/**
 * 소요시간 : 10분
 * 메모리 : 25320 KB
 * 시간 : 380 ms
 *
 * 시간복잡도 : O(NlogN)
 * 		입력 O(N)
 * 		카드묶음 순회 O(N)
 * 		우선순위큐 poll O(logN)
 */
public class 카드_정렬하기_1715 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < N; ++i) {
			pq.add(Integer.parseInt(br.readLine()));
		}


		// 최소합이 되려면 오름차순 정렬해서 작은 것부터 합치면 됨
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
