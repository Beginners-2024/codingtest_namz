package baekjoon;

import java.util.*;
import java.io.*;

public class 로프_2217 {

	private static int N;
	private static List<Integer> rope = new ArrayList<>();
	private static int maxWeight = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; ++i) {
			rope.add(Integer.parseInt(br.readLine()));
		}

		Collections.sort(rope);

		for (int i = 0; i < N; ++i) {
			maxWeight = Math.max(maxWeight, rope.get(i) * (N - i));
		}

		System.out.println(maxWeight);
	}
}
