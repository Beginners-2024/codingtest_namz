package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 소요시간 : 30분
 * 메모리 : 14316 KB
 * 시간 : 128 ms
 *
 * 시간복잡도 : O(M*N)
 * 		입력 O(N)
 * 		findNum O(N)
 * 		명령어 처리 O(M)
 */
public class 회전하는_큐_1021 {
	private static Deque<Integer> dq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// step 1 - 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		dq = new LinkedList<>();

		for (int i = 1; i < N+1; ++i) {
			dq.addLast(i);
		}

		// step 2 - 찾기
		int answer = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; ++i) {
			int num = Integer.parseInt(st.nextToken());
			answer += findNum(num);
		}

		System.out.print(answer);
	}

	/**
	 * step2 - keyNum이 되려면 최소 회전시켜야하는 횟수를 계산하는 함수
	 *
	 * (tmp 사용하여 회전 방향 결정)
	 * 1. top->bottom 으로 회전 횟수 count
	 * 2. botton->top 으로 회전 횟수 count
	 *
	 * (dq 실제로 회전시키기)
	 * 3. 1과 2 중 더 최소값으로 dq를 회전시킴
	 *
	 * @param keyNum 찾아야하는 숫자
	 * @return 회전 횟수
	 */
	private static int findNum(int keyNum) {

		// step 2 (1) - top -> bottom 회전 => topCount 계산
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

		// step 2 (2) - bottom -> top 회전 => bottomCount 계산
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

		// step 2 (3) - (1), (2) 중 더 작은걸로 실제 회전
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
