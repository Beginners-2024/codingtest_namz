package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 소요시간 : 1시간 10분
 * 메모리 : 93216 KB
 * 시간 : 2664 ms (list.remove(index) 사용새) / 864 ms (start, end 사용시)
 */
public class AC_5430 {
    private static List<Integer> answerList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; ++i) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());

            List<Integer> numList = new ArrayList<>();
            String str = br.readLine();
            String[] numStr = str.substring(1, str.length()-1).split(",");

            for (int j = 0; j < n; ++j) {
                numList.add(Integer.parseInt(numStr[j]));
            }

            StringBuilder sb = new StringBuilder();
            if (command(p, numList)) {
                sb.append("[");
                for (int l = 0; l < answerList.size(); ++l) {
                    sb.append(answerList.get(l));
                    if (l < answerList.size() - 1)
                        sb.append(",");
                }
                sb.append("]\n");
            }
            else sb.append("error\n");
            System.out.print(sb.toString());
        }
    }

    private static boolean command(String p, List<Integer> numList) {
        int countOfR = 0;
        int start = 0, end = numList.size();
        int count = numList.size();

        for (int i = 0; i < p.length(); ++i) {
            if (p.charAt(i) == 'R') {
                countOfR++;
//                < 시간초과 > Collections.reverse(list) : O(N)
//                Collections.reverse(numList);

            } else if (p.charAt(i) == 'D') {
                if (count == 0) return false;

                if (countOfR % 2 == 1) end--;
                else start++;

                count--;
            }
        }

        answerList = new ArrayList<>();
        if (countOfR % 2 == 1) {
            for (int i = end-1; i >= start; --i) {
                answerList.add(numList.get(i));
            }
        } else {
            for (int i = start; i < end; ++i) {
                answerList.add(numList.get(i));
            }
        }

        return true;
    }
}
