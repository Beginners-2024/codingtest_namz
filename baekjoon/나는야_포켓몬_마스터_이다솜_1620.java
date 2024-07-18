package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 시간복잡도 : O(N + M)
 * 		입력 O(N) + 찾기 O(M)
 * 메모리 : 76572 KB
 * 시간 : 732 ms
 */
public class 나는야_포켓몬_마스터_이다솜_1620 {

	private static int N, M;
	private static Map<String, Integer> poketmonName = new HashMap<>();
	private static Map<Integer, String> poketmonNumber = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int number = 1;
		for (int i = 0; i < N; ++i) {
			String poketmon = br.readLine();
			poketmonName.put(poketmon, number);
			poketmonNumber.put(number, poketmon);
			number++;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; ++i) {
			String question = br.readLine();

			if (isNumber(question)) {
				sb.append(poketmonNumber.get(Integer.parseInt(question)) + "\n");

				// 반복문 사용하니 시간초과 => 시간초과 안나게 찾는 방법이 있나?
				// for (String key : poketmonName.keySet()) {
				// 	if (poketmonName.get(key) == Integer.parseInt(question))
				// 		sb.append(key + "\n");
				// }
			} else {
				sb.append(poketmonName.get(question) + "\n");
			}
		}

		bw.write(sb.toString());

		bw.flush();
		bw.close();
		br.close();
	}

	private static boolean isNumber(String s) {
		return s.chars().allMatch(Character::isDigit);
	}
}
