package baekjoon;

import java.io.*;
import java.util.*;

public class 회전하는_큐_1021 {
	private static Deque<Integer> dq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		dq = new LinkedList<>();

		for (int i = 1; i < N+1; ++i) {
			dq.addLast(i);
		}

		int answer = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; ++i) {
			int num = Integer.parseInt(st.nextToken());
			answer += findNum(num);
		}

		System.out.print(answer);
	}

	private static int findNum(int keyNum) {
		int topCount = 0;
		Deque<Integer> tmp = new LinkedList<>(dq);
		while (true) {
			if (tmp.peek() == keyNum) {
				tmp.pollFirst();
				break;
			}

			int top = tmp.pollFirst();
			tmp.addLast(top);

			topCount++;
		}

		int bottomCount = 0;
		tmp = new LinkedList<>(dq);
		while (true) {
			if (tmp.peek() == keyNum) {
				tmp.pollFirst();
				break;
			}

			int bottom = tmp.pollLast();
			tmp.addFirst(bottom);

			bottomCount++;
		}

		int count = 0;
		if (topCount < bottomCount) {
			while (true) {
				if (dq.peek() == keyNum) {
					dq.pollFirst();
					break;
				}

				int top = dq.pollFirst();
				dq.addLast(top);
			}
			count = topCount;
		} else {
			while (true) {
				if (dq.peek() == keyNum) {
					dq.pollFirst();
					break;
				}

				int bottom = dq.pollLast();
				dq.addFirst(bottom);
			}
			count = bottomCount;
		}
		return count;
	}
}
