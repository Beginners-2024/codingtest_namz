package baekjoon;

import java.util.*;
import java.io.*;

public class 동전0_11047 {

	private static int N, K;
	private static ArrayList<Integer> coin = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; ++i){
			int value = Integer.parseInt(br.readLine());

			if (value > K) break;
			coin.add(value);
		}
		N = coin.size();

		System.out.println(countMoney());
	}

	public static int countMoney() {
		int countOfCoin = 0;

		for (int i = N-1; i >= 0; --i) {
			if (K >= coin.get(i)) {
				int count = (K / coin.get(i));
				K -= (coin.get(i) * count);
				countOfCoin += count;
			}
		}

		return countOfCoin;
	}
}
