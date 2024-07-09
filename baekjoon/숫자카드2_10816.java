package baekjoon;

import java.util.*;
import java.io.*;

public class 숫자카드2_10816 {

	private static int N, M;
	private static TreeMap<Integer, Integer> card = new TreeMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			int num = Integer.parseInt(st.nextToken());

			if (card.containsKey(num))
				card.replace(num, card.get(num) + 1);
			else
				card.put(num, 1);
		}

		M = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; ++i) {
			int num = Integer.parseInt(st.nextToken());

			Integer answer = card.get(num);
			if (answer != null)
				System.out.print(answer);
			else
				System.out.print(0);
			System.out.print(" ");
		}
	}
}
