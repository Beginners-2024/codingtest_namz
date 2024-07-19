package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 시간복잡도 : O(L)
 * 		입력 O(L) + 배열 O(L) + 출력 O(L)
 * 메모리 : 69684 KB
 * 시간 : 896 ms
 */

public class 수강신청_13414 {

	private static int K, L;

	// 학생 중복 방지용 맵 (입력값 저장)
	private static Map<String, Integer> studentList = new HashMap<>();

	// 학생 우선순위 지정용 배열 (맵 -> 배열)
	private static String[] studentArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		// step 1 - 입력 : 중복 방지, 우선순위 체크
		for (int i = 0; i < L; ++i) {
			String student = br.readLine();

			if (studentList.containsKey(student))
				studentList.replace(student, i);
			else
				studentList.put(student, i);
		}

		// step 2 - 우선순위에 맞게 학생들 배열로 옮김
		studentArr = new String[L];
		for (String key : studentList.keySet()) {
			studentArr[studentList.get(key)] = key;
		}

		// step 3 - 출력
		StringBuilder sb = new StringBuilder();
		int count = 0;

		for (int i = 0; i < L; ++i) {
			if (count == K) break;

			if (studentArr[i] != null) {
				sb.append(studentArr[i] + "\n");
				count++;
			}
		}

		bw.write(sb.toString());

		bw.flush();
		bw.close();
		br.close();
	}
}
