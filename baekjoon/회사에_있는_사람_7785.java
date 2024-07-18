package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 시간복잡도 O(N + NlogN)
 * 		입력 O(N) + 정렬 O(NlogN) + 출력 O(N)
 * 메모리 : 55392 KB
 * 시간 : 788 ms
 */

public class 회사에_있는_사람_7785 {

	private static int n;
	private static Map<String, String> company = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// step 1 - 입력
		// 출근-출근-퇴근 이런식으로 예외처리를 위한 쓰레기 입력값이 없다는 전제 (즉, 출퇴근은 1대1 구조)
		n = Integer.parseInt(br.readLine());

		StringTokenizer st;
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			String key = st.nextToken();
			String value = st.nextToken();

			// 출근 명단에 있으면 퇴근
			if (company.containsKey(key)) {
				company.remove(key);
			}
			// 출근 명단에 없으면 출근
			else {
				company.put(key, value);
			}
		}

		// step 2 - key 정렬 (출근한 사람만 있음)
		List<String> keyList = new ArrayList<>(company.keySet());
		Collections.sort(keyList);

		// step 3 - 출력
		StringBuilder sb = new StringBuilder();
		for (int i = keyList.size() - 1; i >= 0; --i) {
			sb.append(keyList.get(i) + "\n");
		}

		bw.write(sb.toString());

		bw.flush();
		bw.close();
		br.close();
	}
}
