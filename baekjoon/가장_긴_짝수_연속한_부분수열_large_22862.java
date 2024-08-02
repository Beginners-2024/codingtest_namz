package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 풀이시간 : 1시간 10분
 * 메모리 : 98224 KB
 * 소요시간 : 596 ms
 * 시간복잡도 : O(N)
 */
public class 가장_긴_짝수_연속한_부분수열_large_22862 {
	private static int N, K;
	private static List<Pair> even = new ArrayList<>();

	private static class Pair {
		int start, end;

		Pair(int s, int e) {
			this.start = s;
			this.end = e;
		}
	}

 	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// step 1 - 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		boolean isEven = false;
		int s = 0, e = 0;

		// 수열 S에 연속된 짝수 구간을 even 리스트에 저장한다
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			int num = Integer.parseInt(st.nextToken());

			// 1. 짝수 수열 시작이면
			if (num % 2 == 0 && !isEven) {
				isEven = true;
				s = i;
			}
			// 2. 짝수 수열 끝, 홀수 수열 시작이면
			if (num % 2 == 1 && isEven) {
				isEven = false;
				e= i-1;

				even.add(new Pair(s, e));
			}
			// 3. 짝수 수열로 입력이 끝났으면
			if (num % 2 == 0 && i == N-1) {
				even.add(new Pair(s, i));
			}
		}

		// step 2 - 계산 & 출력
		System.out.println(findMax());
	}

	/**
	 * step 2 - 가장 긴 짝수 수열을 찾는 함수 (투포인터)
	 *
	 * odd : 지워진 홀수 개수
	 * K : 지울 수 있는 최대 홀수 개수
	 *
	 * if (odd < K) {
	 * 	  	start 고정
	 *		end++
	 *     	odd 증가
	 *     	length 증가
	 * } else if (odd >= K) {
	 *		start++
	 *		end 고정
	 *		odd 감소
	 *		length 감소
	 * }
	 *
	 * @return 가장 긴 짝수 수열 길이
	 */
	private static int findMax() {
		if (even.isEmpty()) return 0;

		int start = 0, end = 0;

		int MaxLength = even.get(end).end - even.get(start).start + 1;
		int odd = 0;
		int length = MaxLength;

		while(start < even.size() && end < even.size()) {
			if (odd < K) {
				end++;
				if (end == even.size()) break;

				odd += (even.get(end).start - even.get(end-1).end - 1);
				length += (even.get(end).end - even.get(end).start + 1);
			} else {
				start++;
				if (start == even.size()) break;

				odd -= (even.get(start).start - even.get(start-1).end - 1);
				length -= (even.get(start-1).end - even.get(start-1).start + 1);
			}
			if (odd <= K) MaxLength = Math.max(MaxLength, length);
		}
		return MaxLength;
	}
}
