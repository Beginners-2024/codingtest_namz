package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 소요시간 : 40분
 * 메모리 : 27120 KB
 * 시간 : 628 ms
 */
public class 절대값_힙_11286 {
	private static class Number {
		int num;
		int abs;

		Number(int n) {
			this.num = n;
			this.abs = Math.abs(n);
		}

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

		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Number> pq = new PriorityQueue<>(Number.Comparator);

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
