package baekjoon;

import java.awt.image.DataBufferDouble;
import java.util.*;
import java.io.*;

public class 보물_1026 {

	private static int N;
	private static List<Integer> A = new ArrayList<>();
	private static List<Integer> B = new ArrayList<>();
	private static int S = 0;

	public static void main(String[] args) throws IOException {
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

		Collections.sort(A);
		Collections.sort(B, (a,b) -> b-a);

		for (int i = 0; i < N; ++i) {
			S += (A.get(i) * B.get(i));
		}

		System.out.println(S);
	}
}
