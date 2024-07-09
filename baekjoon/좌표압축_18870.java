package baekjoon;

import java.io.*;
import java.util.*;

public class 좌표압축_18870 {

	private static int N;
	private static int[] numArr;
	private static int[] numArrSorted;
	private static HashMap<Integer, Integer> numMap = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		numArr = new int[N];
		numArrSorted = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			numArr[i] = Integer.parseInt(st.nextToken());
			numArrSorted[i] = numArr[i];
		}

		Arrays.sort(numArrSorted);

		int value = 0;
		for (int key : numArrSorted) {
			if (!numMap.containsKey(key))
				numMap.put(key, value++);
		}

		StringBuilder sb = new StringBuilder();
		for (int key : numArr) {
			sb.append(numMap.get(key)).append(' ');
		}

		System.out.println(sb);
	}
}
