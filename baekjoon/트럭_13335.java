package baekjoon;

import java.util.*;
import java.io.*;

/**
 * 풀이시간 : 25분
 * 메모리 : 14456 KB
 * 시간 : 144 ms
 * 시간복잡도 : 최악의 경우(다리에 차 1대씩만 가능할 때) O(1000 * 100)
 */
public class 트럭_13335 {
	private static int n, w, L;
	private static int[] truck;

	private static class Pair {
		int w, time;

		Pair(int n, int t) {
			this.w = n;
			this.time = t;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		truck = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i) {
			truck[i] = Integer.parseInt(st.nextToken());
		}

		/**
		 * 1초씩 시간 보내면서 체크함...노가다 뛰었다는 말...
		 *
		 * 1. 맨앞에 있는 트럭이 다리를 다 건넜는가?
		 * 		(no) => 2번 진행
		 * 		(yes) => poll 후에 2번 진행
		 *
		 * 2. 다리 위에 트럭이 더 올라갈 수 있는가?
		 * 		(no) => 맨앞에 있는 트럭이 다리를 다 건널때까지 time++
		 * 		(yes) => add (weight 증가)
		 *
		 * 3. 다리가 비었는가? (yes) => 끝
		 */
		int time = 0;
		int weight = 0;
		int i = 0;
		Queue<Pair> bridge = new LinkedList<>();

		while (true) {
			if (!bridge.isEmpty() && (time - bridge.peek().time) == w)
				weight -= bridge.poll().w;

			if (i < n && weight + truck[i] <= L) {
				weight += truck[i];
				bridge.add(new Pair(truck[i], time));
				i++;
			}

			time++;

			if (bridge.isEmpty()) break;
		}

		System.out.println(time);
	}
}
