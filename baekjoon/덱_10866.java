package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 소요시간 : 20분
 * 메모리 : 20356 KB
 * 시간 : 388 ms
 */
public class 덱_10866 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		LinkedList<Integer> deque = new LinkedList<>();

		for (int i = 0; i < N; ++i) {
			String[] command = br.readLine().split(" ");

			switch(command[0]) {
				case "push_front" :
					deque.addFirst(Integer.parseInt(command[1]));
					break;

				case "push_back" :
					deque.addLast(Integer.parseInt(command[1]));
					break;

				case "pop_front" :
					try {
						System.out.println(deque.removeFirst());
					} catch (NoSuchElementException e) {
						System.out.println(-1);
					}
					break;

				case "pop_back" :
					try {
						System.out.println(deque.removeLast());
					} catch (NoSuchElementException e) {
						System.out.println(-1);
					}
					break;

				case "size" :
					System.out.println(deque.size());
					break;

				case "empty" :
					System.out.println(deque.isEmpty() ? 1 : 0);
					break;

				case "front" :
					System.out.println(deque.isEmpty() ? -1 : deque.peekFirst());
					break;

				case "back" :
					System.out.println(deque.isEmpty() ? -1 : deque.peekLast());
					break;
			}
		}
	}
}
