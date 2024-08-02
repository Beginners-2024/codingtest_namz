package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 풀이시간 : 20분
 *
 * 메모리 : 26420 KB
 * 시간 : 380 ms
 *
 * 시간복잡도 : O(N + M)
 * 		입력 O(N + M) + 출력 O(M)
 */
public class 듣보잡_1764 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// step 1 - 듣도 못한 사람 입력
		TreeSet<String> noSee = new TreeSet<>();
		for (int i = 0; i < N; ++i) {
			noSee.add(br.readLine());
		}

		// step 2 - 보도 못한 사람 입력 & 듣도보도 못한 사람 찾기
		TreeSet<String> whoAreYou = new TreeSet<>();
		for (int i = 0; i < M; ++i) {
			String key = br.readLine();
			if (noSee.contains(key))
				whoAreYou.add(key);
		}

		// step 3 - 출력
		StringBuilder sb = new StringBuilder();
		sb.append(whoAreYou.size()).append("\n");
		for (String s : whoAreYou) {
			sb.append(s).append("\n");
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());

		bw.close();
		br.close();
	}
}
