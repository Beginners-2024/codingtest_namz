package baekjoon;

import java.util.*;
import java.io.*;
public class 회의실배정_1931 {

	private static int N;
	private static int[][] meetingTime;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		meetingTime = new int[N][2];

		StringTokenizer st;
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			meetingTime[i][0] = Integer.parseInt(st.nextToken());
			meetingTime[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(meetingTime, (a, b) -> {
			if (a[1] != b[1]) return a[1] - b[1];
			else return a[0] - b[0];
		});

		System.out.println(makeTimeTable());
	}

	public static int makeTimeTable() {
		int countOfMeeting = 0;

		int end = 0;
		for (int i = 0; i < N; ++i) {
			if (end <= meetingTime[i][0]) {
				end = meetingTime[i][1];
				countOfMeeting++;
			}
		}

		return countOfMeeting;
	}
}
