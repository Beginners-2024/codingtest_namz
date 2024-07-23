package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 소요시간 : 2시간
 * 메모리 : 14264 KB
 * 시간 : 124 ms
 *
 * 시간복잡도 : O(K)
 * 		입력 O(4*8) = O(32)
 * 		checkRotate O(4)
 * 		rotate O(4)
 * 		setScore O(4)
 * 		명령 수행 횟수 O(K)
 */
public class 톱니바퀴_14891 {

	// 입력
	private static int K;
	private static Gear[] gears = new Gear[4];

	// 톱니바퀴 클래스
	private static class Gear {
		int[] pole = new int[8];	// N극, S극
		int left, right;			// 다른 톱니바퀴와 맞닿는 왼쪽, 오른쪽 index
		int newLeft, newRight;		// 회전 후 변화한 left, right를 계산하여 저장
		boolean check = false;		// 회전여부를 검사했는가
		boolean ready = false;		// 이번 단계에서 회전하는가

		Gear(String poleStr) {
			for (int i = 0; i < 8; ++i)
				pole[i] = poleStr.charAt(i) - '0';

			left = 6;
			right = 2;
		}

		/**
		 * 회전 여부를 검사함
		 *
		 * @param near 이웃하는 톱니의 N/S극 값
		 * @param leftOrRight 기준톱니에게서 왼쪽에 있는가 오른쪽에 있는가
		 *			왼쪽에 있음(-1) : 내 오른쪽(right) 톱니를 검사해야삼
		 *			오른쪽에 있음(1) : 내 왼쪽(left) 톱니를 검사해야함
		 * @return 기준톱니의 극 == 내 톱니 ? true : false
		 */
		public boolean isRotate(int near, int leftOrRight) {
			if (leftOrRight == -1)
				return pole[right] == near ? false : true;
			if (leftOrRight == 1)
				return pole[left] == near ? false : true;
			return true;	// 컴파일에러 때문에 넣음. 실행될 일 없음
		}

		/**
		 * 톱니를 회전했을 때 달라지는 left, right를 계산
		 * 톱니의 회전은 연쇄적이 아닌, 모두 동시에 이루어져야하므로 new 값을 바로 반영하지 않고 일단 계산하여 저장만 해둔다.
		 *
		 * @param dir 회전 방향 (-1 : 반시계, 1 : 시계)
		 */
		public void readyToRotate(int dir) {
			// 반시계 : index가 모두 1씩 커짐
			if (dir == -1) {
				newLeft = (left+1) % 8;
				newRight = (right+1) % 8;
			}
			// 시계 : index가 모두 1씩 작아짐
			else if (dir == 1) {
				newLeft = (left-1) % 8;
				newRight = (right-1) % 8;

				if (newLeft < 0) newLeft += 8;
				if (newRight < 0) newRight += 8;
			}

			ready = true;
		}

		/**
		 * 톱니바퀴를 회전시킴.
		 * pole 배열을 변화시키지 않고 달라진 left, right index 값을 저장하는 방식으로 진행
		 */
		public void rotate() {
			left = newLeft;
			right = newRight;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// step 1 - 입력
		for (int i = 0; i < 4; ++i) {
			gears[i] = new Gear(br.readLine());
		}

		K = Integer.parseInt(br.readLine());

		StringTokenizer st;
		for (int i = 0; i < K; ++i) {
			st = new StringTokenizer(br.readLine());

			int number = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());

			// step 2 - 회전 준비
			checkRotate(number-1, direction, 0);

			// step 3 - 회전
			rotate();
		}

		// step 4 - score 계산 후 출력
		System.out.println(setScore());
	}

	/**
	 * step 2 - 톱니의 회전 가능여부를 검사하고 회전을 준비하는 함수
	 * 이때, 회전을 준비한다는 것은 newLeft / newRight를 set 한다는 것이다.
	 *
	 * 반복문으로 할까 고민하다가 중복코드 나오는거 싫어서 재귀로 구현함.
	 * 중복 코드 없애고 싶으면 관리/회전 으로 함수 쪼개서 관리에서 왼쪽 회전 / 오른쪽 회전 방식으로 call 하면 될 듯. 근데..귀찮았다..
	 * 어차피 톱니가 4개이므로 재귀로 돌려도 시간/공간 복잡도가 커지지 않을거라고 판단했음
	 * 만약에 톱니의 개수도 변수가 된다면 재귀로 하면 안될 듯?
	 *
	 * 재귀의 가장 문제점 => 양방향 확인할 필요 없고 한쪽만 확인하면 되는데 재귀는 매번 양방향을 확인한다
	 *
	 * if (현재 톱니) set newRight / newLeft
	 * if (이웃하는 톱니)
	 * 		if (맞닿은 톱니가 다름)
	 * 			1. set newRight / newLeft
	 * 			2. 해당 톱니에 대해서 재귀 호출 (그 다음 이웃하는 톱니에 대해 계산하기 위함)
	 *
	 * @param number 현재 톱니의 번호
	 * @param direction 현재 톱니가 회전하는 방향 (-1 : 반시계, 1 : 시계)
	 * @param checkCount 회전여부를 검사한 톱니의 개수 (재귀 횟수를 줄이기 위함)
	 */
	private static void checkRotate(int number, int direction, int checkCount) {
		// 재귀 종결조건 : 4개의 톱니 모두 확인한 상태면 리턴
		// 함수 최종 종결조건은 아님. checkCount까지 관리하는 거 귀찮고...
		// 어차피 언젠간 끝날거니까 그냥 조금이라도 early return 시키기 위한 장치
		if (checkCount == 4)
			return ;

		// step 2 (1) - 현재 톱니에 대한 회전 (아직 체크 안했을 때만)
		if (gears[number].check == false) {
			// newLeft/Right를 set하고
			gears[number].readyToRotate(direction);
			// 회전여부 check 했다는 flag 올리고
			gears[number].check = true;
			// 재귀에서 사용할 checkCount 중가
			checkCount++;
		}

		for (int i = -1; i < 2; ++i) {

			// 반복문 skip 조건 (이미 처리했거나 처리할 필요 없는 것들)
			// 현재 톱니(이미 위에서 체크함) or 유효하지 않은 index or 이미 체크한 톱니
			if (i == 0) continue;
			if (!isValid(number + i)) continue;
			if (gears[number+i].check) continue;

			// step 2 (2) - 해당 톱니 체크 함 flag 올리기
			gears[number+i].check = true;
			checkCount++;

			// step 2 (3) - 0변 톱니의 정보 세팅
			// 닿은 쪽이 left or right 인지, 그 index
			int nearIndex = (i == -1) ? gears[number].left : gears[number].right;
			// 맞닿은 톱니의 N/S극
			int near = gears[number].pole[nearIndex];

			// step 3 (4) - 이웃하는 톱니가 회전 가능하다면 (이때, 0번 톱니의 정보를 넘겨줌)
			if (gears[number + i].isRotate(near, i)) {
				// step 3 (5) - new Right/Left 세팅해라 (회전방향은 0번과 반대)
				gears[number + i].readyToRotate(direction * -1);
				// step 4 (6) - 그 다음 이웃하는 톱니에 대해서 처리하기 위해 재귀 돌림
				checkRotate(number + i, direction * -1, checkCount);
			}

		}
	}

	/**
	 * step 3 - 톱니바퀴를 회전시키는 함수
	 *
	 * 톱니가 회전 가능하다면(ready = true)
	 * 톱니의 new Left/Right를 left/right에 업데이트 한다.
	 */
	private static void rotate() {
		for (int i = 0; i < 4; ++i) {
			// step 3 (1) - 회전시킴
			if (gears[i].ready)
				gears[i].rotate();

			// step 3 (2) - 플래그 초기화
			gears[i].check = false;
			gears[i].ready = false;
		}
	}

	/**
	 * step 4 - score 계산하는 함수
	 *
	 * 2의 거듭제곱 활용하여 계산함
	 * 12시 방향 index = (left + 2) mod 8
	 *
	 * @return score
	 */
	private static int setScore() {
		int score = 0;
		for (int i = 0; i < 4; ++i) {
			// step 4 (1) - 12시 방향 index 계산
			int twelve = (gears[i].left + 2) % 8;

			// step 4 (2) - S극이면 점수 계산
			if (gears[i].pole[twelve] == 1) {
				score += Math.pow(2, i);
			}
		}
		return score;
	}

	private static boolean isValid(int index) {
		return 0 <= index && index < 4;
	}
}
