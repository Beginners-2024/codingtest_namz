package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 풀이시간 : 50분
 * 메모리 : 30596 KB
 * 시간 : 632 ms
 * 시간복잡도 : O(N * M)
 */
public class 테트로미노_14500 {
	static int N, M;
	static int[][] paper;
	static int[][][] shapes = {
		{{1,1,1,1}},
		{{1,1}, {1,1}},
		{{1,0}, {1,0}, {1,1}},
		{{1,0}, {1,1}, {0,1}},
		{{1,1,1}, {0,1,0}}
	};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// step 1 - 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		paper = new int[N][M];

		for (int r = 0; r < N; ++r) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; ++c) {
				paper[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// step 2 - shape을 1개씩 올리면서 최대값을 확인함
		int max = 0;
		for (int i = 0; i < 5; ++i) {
			max = Math.max(max, putOnPaper(shapes[i]));
		}

		// step 3 - 출력
		System.out.println(max);
	}

	/**
	 * step 2 - shape을 paper에 올렸을 때 점수를 계산하는 메서드
	 * 90도씩 회전, 대칭의 상황에 대해 모두 체크함
	 *
	 * 이때, 회전은 shape가 아닌 paper를 90도씩 회전한다고 했다가
	 * (shape 회전시키기가 어려워서 이렇게 한건데 이제와서 생각하니 같은 방식으로 하면 되서
	 * shape를 회전시키는게 시간소요가 더 적을 듯..아무레도 paper가 훨씬 크니까..)
	 * shape를 회전시기기로 변경함!
	 * => 근데 시간은 똑같고 메모리 사용량이 크게 줄었음 (63524 -> 30596)
	 *
	 * @param shape
	 * @return shape가 paper에 올라갔을 때 점수
	 */
	private static int putOnPaper(int[][] shape) {
		int count = 0;

		// 반시계 90도 4방향 회전
		for (int i = 0; i < 4; ++i) {

			// rotatePaper(i);

			// step 2 (1) - shape을 회전
			shape = rotateShape(shape);

			// step 2 (2) - shape을 paper 좌측 상단부터 우측 하단까지 올리면서 점수 확인
			for (int r = 0; r <= paper.length - shape.length; ++r) {
				for (int c = 0; c <= paper[r].length - shape[0].length; ++c) {
					count = Math.max(count, checkShape(r, c, shape));
				}
			}
		}

		return count;
	}

	// private static void rotatePaper(int i) {
	//
	// 	int[][] tmp = new int[paper[0].length][paper.length];
	//
	// 	for (int r = 0; r < paper.length; ++r) {
	// 		for (int c = 0; c < paper[r].length; ++c) {
	// 			int nr = c;
	// 			int nc = paper.length - r - 1;
	//
	// 			tmp[nr][nc] = paper[r][c];
	// 		}
	// 	}
	//
	// 	paper = new int[tmp.length][tmp[0].length];
	//
	// 	for (int r = 0; r < tmp.length; ++r) {
	// 		for (int c = 0; c < tmp[r].length; ++c) {
	// 			paper[r][c] = tmp[r][c];
	// 		}
	// 	}
	// }

	/**
	 * step 2 (1) - shape을 회전시키는 메서드
	 *
	 * 회전 방법 : 가로 한 줄을 세로 한 줄로 회전시킴
	 *
	 * new row = old column
	 * new column = old row length - old row - 1
	 *
	 * @param shape
	 * @return 회전한 shape
	 */
	private static int[][] rotateShape(int[][] shape) {

		int[][] tmp = new int[shape[0].length][shape.length];

		for (int r = 0; r < shape.length; ++r) {
			for (int c = 0; c < shape[r].length; ++c) {
				int nr = c;
				int nc = shape.length - r - 1;

				tmp[nr][nc] = shape[r][c];
			}
		}

		return tmp;
	}

	/**
	 * step 2 (2) - paper 위에 올렸을 떄 점수 계산하는 메서드
	 *
	 * 대칭한 것과 원래 모양 점수를 구해서 최댓값을 찾음
	 *
	 * @param r shape을 놓을 영역에 대한 paper의 좌측 상단 좌표 row
	 * @param c shape을 놓을 영역에 대한 paper의 좌측 상단 좌표 column
	 * @param shape
	 * @return 점수
	 */
	private static int checkShape(int r, int c, int[][] shape) {
		int count1 = 0, count2 = 0;

		for (int i = 0; i < shape.length; ++i) {
			for (int j = 0; j < shape[0].length; ++j) {
				// 원래모양대로 올리기
				if (shape[i][j] == 1)
					count1 += paper[r + i][c + j];
				// 대칭 모양대로 올리기
				if (shape[shape.length - i - 1][j] == 1)
					count2 += paper[r + i][c + j];
			}
		}
		return Math.max(count1, count2);
	}
}
