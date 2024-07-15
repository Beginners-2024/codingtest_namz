package baekjoon;

import java.io.*;
import java.util.*;

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

		Tree tree = new Tree();
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());

			char name = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);

			tree.create(name, left, right);
		}

		tree.preorder(tree.root);
		System.out.println();
		tree.inorder(tree.root);
		System.out.println();
		tree.postorder(tree.root);
	}

	private static class Tree {
		public Node root;

		public void create(char name, char left, char right) {
			if (name == 'A') {
				root = new Node(name);
				root.left = new Node(left);
				root.right = new Node(right);
			} else {
				Node node = search(root, name);
				node.left = new Node(left);
				node.right = new Node(right);
			}
		}

		public Node search(Node node, char name) {
			if (node == null) return null;
			if (node.name == name) return node;

			Node leftNode = search(node.left, name);
			if (leftNode != null) return leftNode;

			return search(node.right, name);
		}

		public void preorder(Node node) {
			if (node.name == '.') return ;

			System.out.print(node.name);

			preorder(node.left);
			preorder(node.right);
		}

		public void inorder(Node node) {
			if (node.name == '.') return;

			inorder(node.left);
			System.out.print(node.name);
			inorder(node.right);

		}

		public void postorder(Node node) {
			if (node.name == '.') return ;

			postorder(node.left);
			postorder(node.right);
			System.out.print(node.name);
		}
	}
}
