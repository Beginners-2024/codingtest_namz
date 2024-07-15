package baekjoon;

import java.util.*;
import java.io.*;

public class 보물_1026 {

	private static int N;
	private static List<Integer> A = new ArrayList<>();
	private static List<Integer> B = new ArrayList<>();
	private static int S = 0;

	public static void main(String[] args) throws IOException {
		// step 1 - 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			A.add(Integer.parseInt(st.nextToken()));
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			B.add(Integer.parseInt(st.nextToken()));
		}

		// step 2 - 정렬. A : 오름차순, B : 내림차순
		Collections.sort(A);
		Collections.sort(B, (a,b) -> b-a);

		// step 3 - S 계산
		/**
		 * S가 min이 되려면 (A의 max * B의 min) + ... + (A의 min * B의 max) 형태여야함
		 */
		for (int i = 0; i < N; ++i) {
			S += (A.get(i) * B.get(i));
		}

		System.out.println(S);
	}
}
