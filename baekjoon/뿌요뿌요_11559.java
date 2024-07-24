package baekjoon;

import java.util.*;
import java.io.*;

/**
 * 소요시간 : 40분
 * 메모리 : 14504 KB
 * 시간 : 128 ms
 *
 * 시간복잡도 : O(288 + 72 + 72) = O(432)
 * 		- findPuyoBFS worst O(12 * 6 * 4) = O(288)
 * 		- deletePuyo worst O(12 * 6) = O(72)
 * 		- gravity O (12 * 6) = O(72)
 */
public class 뿌요뿌요_11559 {

	private static char[][] field = new char[12][6];

	// findPuyoBRS 용 변수들
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	private static boolean[][] visit;

	// 4개 쌍이 맞아서 삭제해아하는 뿌요 큐
	private static Queue<Pair> deleteQ = new LinkedList<>();

	// 콤보 개수
	private static int combo = 0;

	// (r, c)
	private static class Pair{
		int r, c;

		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// step 1 - 입력
		for (int r = 0; r < 12; ++r) {
			String str = br.readLine();
			for (int c = 0; c < 6; ++c) {
				field[r][c] = str.charAt(c);
			}
		}

		// 뿌요 게임 시작 : 더이상 삭제할 뿌요가 없을때까지 반복한다
		while (true) {

			// step 2 - 삭제할 뿌요 찾기 (BFS)
			visit = new boolean[12][6];
			for (int r = 0; r < 12; ++r) {
				for (int c = 0; c < 6; ++c) {
					// 이미 확인했거나 뿌요가 아니면 넘어감
					if (visit[r][c] || field[r][c] == '.') continue;

					// 뿌요를 발견했으면 BFS로 4개 이상인지 확인
					findPuyoBFS(r, c, field[r][c]);
				}
			}

			// 게임 종결조건 : 찾은 뿌요 블럭이 없다면 게임 끝
			if (deleteQ.isEmpty()) break;

			// step 3 - 2에서 찾은 뿌요 리스트 삭제
			deletePuyo();

			// step 4 - 중력 발생
			gravity();

			// step 5 - 콤보 + 1
			combo++;
		}

		// step 6 - 출력
		System.out.println(combo);
	}

	/**
	 * step 3 - 삭제할 뿌요 블럭을 찾는 함수 (BFS)
	 * - 뿌요가 4개 이상의 블럭을 이루는지 확인하고
	 * - 조건에 맞으면 deleteQ에 넣는다
	 *
	 * @param r 뿌요 r
	 * @param c 뿌요 c
	 * @param puyo 뿌요 색상
	 */
	private static void findPuyoBFS(int r, int c, char puyo) {

		// step 2 (1) - 변수 초기화 및 기본 세팅
		Queue<Pair> q = new LinkedList<>();			// BFS용
		Queue<Pair> deleteTmp = new LinkedList<>();	// 삭제할 뿌요 블럭을 임시로 담아놓을 큐
													// 아직 4개 이상인지 확인안되었기 때문에 deleteQ에 넣기전 임시로 보관한다

		q.add(new Pair(r, c));
		deleteTmp.add(new Pair(r, c));
		visit[r][c] = true;

		while (!q.isEmpty()) {
			Pair nowPos = q.poll();

			// step 2 (2) - nowPos에 대한 상하좌우 확인
			for (int i = 0; i < 4; ++i) {
				Pair newPos = new Pair(nowPos.r + dr[i], nowPos.c + dc[i]);

				if (!isValid(newPos) || visit[newPos.r][newPos.c] || field[newPos.r][newPos.c] != puyo) continue;

				// step 2 (3) - newPos가 같은 뿌요일 때
				visit[newPos.r][newPos.c] = true;
				q.add(newPos);
				deleteTmp.add(newPos);	// tmp에 넣는다
			}
		}

		// step 2 (4) - 찾은 뿌요가 4개 이상이면 deleteQ에 넣는다
 		if (deleteTmp.size() >= 4) {
			while (!deleteTmp.isEmpty())
				deleteQ.add(deleteTmp.poll());
		}

		deleteTmp.clear();
	}

	/**
	 * step 3 - 찾은 뿌요 블럭 삭제하는 함수
	 */
	private static void deletePuyo() {
		while (!deleteQ.isEmpty()) {
			Pair pos = deleteQ.poll();

			field[pos.r][pos.c] = '.';
		}
	}

	/**
	 * step 4 - 뿌요 블럭을 모두 아래로 내리는 함수
	 *
	 * 큐를 이용해서 유효한 블럭을 다시 밑에서부터 새로 쌓는 방식으로 진행
	 */
	private static void gravity() {
		Queue<Character> puyo = new LinkedList<>();	// 뿌요을 담을 큐

		// 세로 기준으로 한줄씩 밑에서부터 검사한다
		for (int c = 0; c < 6; ++c) {

			// step 4 (1) - 빈공간이 아닌 뿌요라면 queue에 넣는다
			for (int r = 11; r >= 0; --r) {
				if (field[r][c] == '.') continue;

				puyo.add(field[r][c]);
			}

			// step 4 (2) - queue를 field의 밑에서부터 다시 쌓는다. queue가 끝나면 빈공간을 쌓음
			for (int r = 11; r >= 0; --r) {
				if (puyo.isEmpty()) field[r][c] = '.';
				else field[r][c] = puyo.poll();
			}
		}
	}

	private static boolean isValid(Pair pos) {
		return 0 <= pos.r && pos.r < 12 && 0 <= pos.c && pos.c < 6;
	}
}
