package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 소요시간 : 1시간 30분
 * 메모리 : 15332 KB
 * 시간 : 156 ms
 */
public class 경사로_14890 {

	private static int N, L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		int[][] load = new int[2*N][N];

		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				int num = Integer.parseInt(st.nextToken());

				load[i][j] = num;
				load[N + j][i] = num;
			}
		}

		int count = 0;
		for (int i = 0; i < 2*N; ++i) {
			if (checkLoad(load[i])) count++;
		}

		System.out.println(count);
	}

	private static boolean checkLoad(int[] load) {
		Queue<Integer> q = new LinkedList<>();

		int before = load[0];
		for (int i = 0; i < N; ++i) {
			int now = load[i];
			int diff = Math.abs(before - now);

			if (diff > 1) return false;

			else if (diff == 0) {
				q.add(now);
				before = now;
				continue;
			}

			if (now > before) {
				if (q.size() < L)  return false;

				q.clear();
				q.add(now);
				before = now;
				continue;
			}
			else if (now < before) {
				q.clear();
				q.add(now);

				while (++i <= N) {
					if (q.size() == L) {
						q.clear();
						break;
					}
					if (i == N) break;
					if (load[i] != now)  return false;

					q.add(load[i]);
				}
				if (i == N && !q.isEmpty()) return false;
				i--;
			}

			before = now;
		}
		return true;
	}
}