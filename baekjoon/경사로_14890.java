package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 소요시간 : 1시간 30분
 * 메모리 : 15332 KB
 * 시간 : 156 ms
 *
 * 시간복잡도 : O(N*N)
 * 	입력 O(N*N) + 검사 O(N*N)
 */
public class 경사로_14890 {

	private static int N, L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// step 1 - 입력
		// O(N*N)
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		// 2N 개의 길을 입력과 동시에 저장함
		int[][] load = new int[2*N][N];

		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				int num = Integer.parseInt(st.nextToken());

				load[i][j] = num;
				load[N + j][i] = num;
			}
		}

		// step 2 - 유효한 길인지 검사
		// O(N)
		int count = 0;
		for (int i = 0; i < 2*N; ++i) {
			if (checkLoad(load[i])) count++;
		}

		System.out.println(count);
	}

	/**
	 * step 2 - 유효한 길인지 검사하는 함수
	 *
	 * 시간복잡도 : O(N)
	 *
	 * if ( 연속된 번호가 나오면 ) queue에 저장
	 * else ( 다른 번호가 나오면 )
	 * 		if ( 번호 차이가 2 이상 ) return false
	 * 		else if ( 올라가는 경사로)
	 * 			지나온 길에 경사로를 설치할 수 있는지 확인
	 * 				1. 설치 가능 continue
	 * 				2. 실치 불가능 return false
	 * 		else if ( 내려가는 경사로 )
	 * 			앞으로  자나갈 길에 경사로를 설치할 수 있는지 확인
	 * 				1. 설치 가능 continue
	 * 				2. 실치 불가능 return false
	 *
	 * @param load 검사할 길
	 * @return 유효성 여부 (true : 유효함 , false : 유효하지 않음)
	 */
	private static boolean checkLoad(int[] load) {
		/* 경사로를 설치할 수 있는지 검사할 길을 저장하는 큐
		    1. 올라가는 길이면, 지나온 길에 대해 저장을 하고
		    2. 내려가는 길이면, 앞으로 갈 길에 대해 저장을 한다
		   경사로를 설치하고 나면 queue를 비운다 */
		Queue<Integer> q = new LinkedList<>();

		// 이전 길의 번호를 저장하는 변수
		int before = load[0];

		for (int i = 0; i < N; ++i) {

			int now = load[i];					// 현재 길의 번호
			int diff = Math.abs(before - now);	// 현재 길과 이전 길의 번호 차이

			// step 2 (1) - false : 차이가 2 이상
			if (diff > 1) return false;

			// step 2 (2) - 이전과 같은 번호일 때 큐에 넣고 continue
			else if (diff == 0) {
				q.add(now);
				before = now;	// 다음 번째로 넘어가기 전에 before 얍데이트
				continue;
			}

			// step 2 (3) - 올라가는 계단일 때, queue를 확인하여 경사로 설치여부를 판단
			if (now > before) {
				// false : 같은 번호로 이어진 길이 짧아서 경사로를 설치할 수 없음
				if (q.size() < L)  return false;

				/* 경사로를 설치할 수 있는 상황 */

				q.clear();		// 지나온 길에 경사로를 설치했으므로 큐를 비운다
				q.add(now);		// 현재 길 큐에 저장
				before = now;	// 다음턴 가기 전, before 업데이트
				continue;
			}
			// step 2 (4) - 내려가는 계단일 때 다음 계단을 확인한다
			else if (now < before) {
				q.clear();		// 지나온 길은 필요 없으므로 큐 비음
				q.add(now);		// 현재 길을 큐에 저장. (현재 길부터 경사로 설치여부 판단해야하므로)

				// 길이 끝날때까지 반복
				while (++i <= N) {
					// 앞에 놓인 길이 경사로 걸치 가능한 길이임. 반복문 끝
					if (q.size() == L) {
						q.clear();
						break;
					}
					// 길을 끝까지 봤으면 반복 종료
					if (i == N) break;
					// false : 아직 경사로 설치 안했는데 길 번호가 달라짐
					if (load[i] != now)  return false;

					// 아직 검사 중. 큐에 저장
					q.add(load[i]);
				}
				// false : 길이 끝났는데 큐가 비워지지 않음. (= 경사로를 설치하기 전에 길이 끝남)
				if (i == N && !q.isEmpty()) return false;

				// while 에서 마지막에 ++ 했으므로 다시 --
				i--;
			}
			// before 업데이트
			before = now;
		}
		return true;
	}
}