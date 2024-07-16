package baekjoon;

import java.util.*;
import java.io.*;

public class 연결요소의_개수_11724 {

	private static int N, M;

	private static List<Integer>[] nodeList;
	private static boolean[] isVisit;

	private static int countOfGraph = 0;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nodeList = new ArrayList[N+1];
		isVisit = new boolean[N+1];
		for (int i = 1; i <= N; ++i) {
			nodeList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());

			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());

			nodeList[node1].add(node2);
			nodeList[node2].add(node1);
		}

		for (int i = 1; i <= N; ++i) {
			if (!isVisit[i]) {
				findGraph(i);
				countOfGraph++;
			}
		}

		bw.write(countOfGraph + "\n");
		bw.close();
		br.close();
	}

	private static void findGraph(int now) {
		isVisit[now] = true;
		for (int next : nodeList[now]) {
			if (!isVisit[next])
				findGraph(next);
		}
	}
}
