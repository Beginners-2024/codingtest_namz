package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 소요시간 : 40분
 * 메모리 : 27120 KB
 * 시간 : 628 ms
 *
 * 시간복잡도 : O(NlogN)
 * 		우선순위큐	add  O(logN)
 * 				poll O(logN)
 * 		명령처리 O(N)
 */
public class 절대값_힙_11286 {
	private static class Number {
		int num;
		int abs;

		Number(int n) {
			this.num = n;
			this.abs = Math.abs(n);
		}

		/* 1순위 : 절뎃값(abs)이 작은 수
		   2순위 : 값(num)이 작은 수
		   Integer.compase : 음수 (x < y) / 0 (x == y) / 양수 (x > y) */
		public static final Comparator<Number> Comparator = new Comparator<Number>() {
			@Override
			public int compare(Number n1, Number n2) {
				int absCompareResult = Integer.compare(n1.abs, n2.abs);
				if (absCompareResult != 0)
					return absCompareResult;
				return Integer.compare(n1.num, n2.num);
			}
		};
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// step 1 - 입력
		int N = Integer.parseInt(br.readLine());

		// 절댓값과 수의 크기로 정렬
		PriorityQueue<Number> pq = new PriorityQueue<>(Number.Comparator);

		// step 2 - 명령어 처리
		for (int i = 0; i < N; ++i) {
			int num = Integer.parseInt(br.readLine());

			if (num == 0) {
				if (pq.isEmpty())
					System.out.println(0);
				else {
					Number min = pq.poll();
					System.out.println(min.num);
				}
			} else {
				pq.add(new Number(num));
			}
		}
	}
}
