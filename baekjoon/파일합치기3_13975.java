package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 풀이시간 : 15분
 * 메모리 : 322,680 KB
 * 시간 : 3,104 ms
 * 시간복잡도 : O(K logK)
 * 		입력 O(K logK) + 파일합치기 (K logK)
 */
public class 파일합치기3_13975 {
	private static int T, K;
	private static PriorityQueue<Long> novel;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int test = 0; test < T; ++test) {
			K = Integer.parseInt(br.readLine());

			// step 1 - 입력
			novel = new PriorityQueue<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; ++i) {
				novel.add(Long.parseLong(st.nextToken()));
			}

			// step 2 - 파일합치기
			sb.append(makeBook()).append('\n');
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());

		bw.close();
		br.close();
	}

	/**
	 * step 2 - 파일을 합쳐서 최소비용을 계산하는 함수
	 *
	 * 크기가 작은 순으로 파일을 합친다
	 *
	 * @return 최소비용
	 */
	private static Long makeBook() {
		Long pay = 0L;
		while (novel.size() > 1) {
			Long tmpFile = novel.poll() + novel.poll();

			novel.add(tmpFile);

			pay += tmpFile;
		}

		return pay;
	}
}
