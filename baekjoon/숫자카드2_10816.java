package baekjoon;

import java.util.*;
import java.io.*;

public class 숫자카드2_10816 {

	private static int N, M;
	private static HashMap<Integer, Integer> card = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			int key = Integer.parseInt(st.nextToken());

			card.put(key, card.getOrDefault(key, 0) + 1);
		}

		M = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; ++i) {
			int key = Integer.parseInt(st.nextToken());

			sb.append(card.getOrDefault(key, 0)).append(' ');
		}

		System.out.println(sb);
	}
}
