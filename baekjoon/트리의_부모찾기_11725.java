package baekjoon;

import java.util.*;
import java.io.*;

public class 트리의_부모찾기_11725 {

	private static int N;

	private static Tree tree = new Tree();

	private static class Node {
		Node left, right;
		int data;

		Node(int data) {
			this.data = data;
		}

	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		StringTokenizer st;
		for (int i = 0; i < N-1; ++i) {
			st = new StringTokenizer(br.readLine());
			int data1 = Integer.parseInt(st.nextToken());
			int data2 = Integer.parseInt(st.nextToken());

			tree.create(data1, data2);
		}

		for (int i = 2; i <= N; ++i) {
			System.out.println(tree.searchParent(tree.root, i));
		}

	}

	private static class Tree {
		public Node root;

		public void create(int data1, int data2) {
			if (root == null && (data1 == 1 || data2 == 1)) {
				root = new Node(data1 == 1 ? data1 : data2);
				if (root.left == null)
					root.left = new Node(data1 == 1 ? data2 : data1);
				else
					root.right = new Node(data1 == 1 ? data2 : data1);
			}
			else {
				Node node = search(root, data1);
				if (node == null)
					node = search(root, data2);

				if (node.left == null)
					node.left = new Node(node.data == data1 ? data2 : data1);
				else
					node.right = new Node(node.data == data1 ? data2 : data1);
			}

		}

		public Node search(Node node, int data) {
			if (node == null) return null;
			if (node.data == data) return node;

			Node leftNode = search(node.left, data);
			if (leftNode != null)
				return leftNode;
			return search(node.right, data);
		}

		public int searchParent(Node node, int data) {
			if (node == null) return 0;
			if (node.left != null && node.left.data == data) return node.data;
			if (node.right != null && node.right.data == data) return node.data;

			int parent = searchParent(node.left, data);
			if (parent == 0) parent = searchParent(node.right, data);

			return parent;

		}
	}


}
