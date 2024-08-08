package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 풀이시간 : 25분
 * 메모리 : 15,388 KB
 * 시간 : 172 ms
 * 시간복잡도 : O((n + m)log n)
 *
 * 중간 합계 및 최종 합계가 int 범위 넘어선다 => long
 */
public class 카드합체놀이_15903 {
	private static int n, m;
	private static PriorityQueue<Long> card;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// step 1 - 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		card = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i) {
			card.add(Long.parseLong(st.nextToken()));
		}

		// step 2 - 가장 작은 숫자카드 2개 합침 (반복)
		for (int i = 0; i < m; ++i) {
			long sum = card.poll() + card.poll();
			card.add(sum);
			card.add(sum);
		}

		// step 3 - 전체 합산
		long score = 0;
		for (long i : card) {
			score += i;
		}

		System.out.println(score);

	}
}
