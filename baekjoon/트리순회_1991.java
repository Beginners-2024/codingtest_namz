package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 시간복잡도 : O(N)
 * 		create : worst O(N)
 * 		(pre/in/post)order : O(N)
 */
public class 트리순회_1991 {

	private static int N;

	private static class Node {
		Node left, right;
		char name;

		Node(char n) {
			this.name = n;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		StringTokenizer st;

		// step 1 - 트리 생성
		Tree tree = new Tree();
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());

			char name = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);

			tree.create(name, left, right);
		}

		// step 2 - 순회
		tree.preorder(tree.root);
		System.out.println();
		tree.inorder(tree.root);
		System.out.println();
		tree.postorder(tree.root);
	}

	private static class Tree {
		public Node root;

		public void create(char name, char left, char right) {
			// 루트 노드 생성
			if (name == 'A') {
				root = new Node(name);
				root.left = new Node(left);
				root.right = new Node(right);
			}
			// 루트 노드가 아니라면 search에서 재귀를 통해 name을 root로 갖는 노드를 찾아라
			else {
				Node node = search(root, name);
				node.left = new Node(left);
				node.right = new Node(right);
			}
		}

		// 재귀
		public Node search(Node node, char name) {
			// 재귀 종결조건
			// 노드가 끝났거나(서치 실패), 노드를 찾았거나(서치 성공)
			if (node == null) return null;
			if (node.name == name) return node;

			// 왼쪽부터 탐색하고
			Node leftNode = search(node.left, name);
			if (leftNode != null) return leftNode;

			// 왼쪽에서 못찾았으면 오른쪽 탐색
			return search(node.right, name);
		}

		/**
		 * < 재귀 종결 조건 >
		 * 자식 노드가 없는 경우 끝
		 *
		 * < 순회 방식 >
		 * 현재 노드를 (루트)라고 생각하고 출력 시점을 정하면 됨.
		 * "루트 == 출력" 이라고 생각!
		 */

		public void preorder(Node node) {
			if (node.name == '.') return ;

			System.out.print(node.name);	// 1. 출력 (루트)
			preorder(node.left);			// 2. 왼쪽
			preorder(node.right);			// 3. 오른쪽
		}

		public void inorder(Node node) {
			if (node.name == '.') return;

			inorder(node.left);				// 1. 왼쪽
			System.out.print(node.name);	// 2. 출력 (루트)
			inorder(node.right);			// 3. 오른쪽

		}

		public void postorder(Node node) {
			if (node.name == '.') return ;

			postorder(node.left);			// 1. 왼쪽
			postorder(node.right);			// 2. 오른쪽
			System.out.print(node.name);	// 3. 출력 (루트)
		}
	}
}
