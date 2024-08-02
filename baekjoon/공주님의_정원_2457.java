package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 풀이 시간 : 1시간
 * 메모리 : 55,652 KB
 * 시간 : 644 ms
 * 시간복잡도 : O(N logN)
 * 		입력 : O(N logN)
 * 		blossom : flowerPQ 비우기 O(NlogN) + flowers 채우기 O(NlogN) + flowers poll O(logN)
 */
public class 공주님의_정원_2457 {

	private static int N;
	private static PriorityQueue<Flower> flowerPQ;

	private static class Flower{
		int start;
		int end;

		// 날짜의 대소 비교를 위해 MMDD 형식으로 정수값 변환
		// MMDD가 더 작으면 더 앞의 날짜임
		Flower(int startMonth, int startDay, int endMonth, int endDay) {
			start = startMonth * 100 + startDay;
			end = endMonth * 100 + endDay;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// step 1 - 입략
		N = Integer.parseInt(br.readLine());

		/* 기준
			1순위 : 꽃이 피는 시기가 이른 것 (start)
			2순위 : 꽃이 지는 시기가 늦은 것 (end)
		*/
		flowerPQ = new PriorityQueue<>((o1, o2) -> {
			if (o1.start != o2.start)
				return o1.start - o2.start;
			return o2.end - o1.end;
		});

		StringTokenizer st;
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());

			// start Month = start Day - end Month - end Day
			flowerPQ.add(new Flower(Integer.parseInt(st.nextToken()),
									Integer.parseInt(st.nextToken()),
									Integer.parseInt(st.nextToken()),
									Integer.parseInt(st.nextToken())));
		}


		// step 2 - 꽃 심기
		int start = 301;		// 꽃은 3월 1일에 피어있어야 함. 시작점
		int count = 0;
		while(start <= 1130) {	// 꽃은 11월 30일 까지 피어있어야 함. 끝 (12월 1일에 져야함)
			start = blossom(start);

			if (start == 0) {
				System.out.println(0);
				return ;
			}

			count++;
		}

		System.out.println(count);
	}

	/**
	 * step 2 - 심을 꽃을 정하는 함수
	 *
	 * 1. 개화시기가 기준보다 같거나 이른 꽃들
	 * 2. 1이 여러개라면 지는 시기가 가장 늦은 것 선택
	 *
	 * => 1에서 뽑은 꽃 중 2에서 선택되지 못한 꽃은 버림
	 *
	 * 3. 2에서 선택한 꽃의 지는 시기 리턴
	 * => 다음 꽃은 이 시기보다 같거나 이르게 피어야한다.
	 *
	 * @param start 꽃이 피기 시작해야하는 최대 날짜 (이 날짜보다 같거나 이전이 피어야함)
	 * @return end : 심은 꽃이 지는 날짜 / 0 : 심을 수 있는 꽃이 없음
	 */
	private static int blossom(int start) {
		/* 기준
			1순위 : 지는 시기가 더 늦은 것
			=> 피는 시기가 start 보다 같거나 이른 것들의 집합이므로 피는 시기는 고려할 필요 없음
		*/
		PriorityQueue<Flower> flowers = new PriorityQueue<>((o1, o2) ->  o2.end - o1.end);

		while (!flowerPQ.isEmpty()) {
			// 종결조건 : 더이상 기준에 맞는 꽃이 없음
			if (start < flowerPQ.peek().start) break;

			flowers.add(flowerPQ.poll());
		}

		if (!flowers.isEmpty()) return flowers.poll().end;
		else return 0;
	}
}
