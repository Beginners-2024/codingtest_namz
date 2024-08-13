package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 풀이시간 : 1시간
 * 메모리 : 116,144 KB
 * 시간 : 1,408 ms
 * 시간복잡도 : O(N * M * logM)
 */
public class 멀티버스2_18869 {

	private static int M, N;
	private static int[][] universe;
	private static int[][] universeSorted;
	private static boolean[][] combi;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// step 1 - 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		combi = new boolean[N][N];
		universe = new int[N][M];		// 순서 유지
		universeSorted = new int[N][M];	// 정렬됨

		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; ++j) {
				universe[i][j] = Integer.parseInt(st.nextToken());
			}
			universeSorted[i] = universe[i].clone();
			Arrays.sort(universeSorted[i]);
		}

		// step 2 - 현재 행성이 몇번째로 큰 행성인지 체크함
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				universe[i][j] = replaceUniverse(universe[i][j], universeSorted[i]);
			}
		}

		// step 3 - 우주쌍 찾기
		int count = 0;
		for (int i = 0; i < N; ++i) {
			count += makeCombi(i);
		}
		System.out.println(count);
	}

	/**
	 * step 2 - 몇번째로 큰 행성인지 찾는 메서드
	 *
	 * 이분탐색을 사용해서 num 행성이 몇번째로 큰 행성인지 계산
	 *
	 * @param num 행성의 크기
	 * @param uni 정렬된 우주 배열
	 * @return n번째로 큰 행성 (index)
	 */
	private static int replaceUniverse(int num, int[] uni) {
		int mid = M/2;
		int start = 0;
		int end = M-1;

		while (start <= end) {
			if (num == uni[mid]) break;
			if (num < uni[mid]) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
			mid = (start + end) / 2;
		}

		return mid;
	}

	/**
	 * step 3 - me 우주와 우주쌍을 이루는 우주의 개수를 세는 메서드
	 * @param me
	 * @return 우주쌍의 개수
	 */
	private static int makeCombi(int me) {
		int count = 0;
		for (int i = 0; i < N; ++i) {
			// 같은 우주거나 이미 슨서 바꿔서 확인한 경우
			if (i == me || combi[me][i]) continue;

			// visit 배열과 같은 역할
			combi[me][i] = true;
			combi[i][me] = true;

			// 우주쌍인지 아닌지 확인
			if (!compare(universe[me], universe[i])) continue;

			count++;
		}
		return count;
	}

	/**
	 * 크기 순서가 같으면 균등한 우주임
	 * @param a 우주 1
	 * @param b 우주 2
	 * @return true : 균등한 우주 / false : 균등하지 않음
	 */
	private static boolean compare(int[] a, int[] b) {
		for (int i = 0; i < M; ++i) {
			if (a[i] != b[i]) return false;
		}
		return true;
	}
}
