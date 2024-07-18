package baekjoon;

import java.io.*;
import java.util.*;

public class 회사에_있는_사람_7785 {

	private static int n;
	private static final String ENTER = "enter";
	private static final String LEAVE = "leave";

	private static Map<String, String> company = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());

		StringTokenizer st;
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			String key = st.nextToken();
			String value = st.nextToken();

			if (company.containsKey(key)) {
				company.remove(key);
			} else {
				company.put(key, value);
			}
		}

		List<String> keyList = new ArrayList<>(company.keySet());
		Collections.sort(keyList);

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
