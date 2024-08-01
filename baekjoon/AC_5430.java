package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 소요시간 : 1시간 10분
 * 메모리 : 93216 KB
 * 시간 : 2664 ms (list.remove(index) 사용시) / 864 ms (start, end 사용시)
 *
 * 시간복잡도 : O(T*(P+N))
 */
public class AC_5430 {
    private static List<Integer> answerList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; ++i) {
            // step 1 - 입력
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());

            List<Integer> numList = new ArrayList<>();
            String str = br.readLine();
            String[] numStr = str.substring(1, str.length()-1).split(",");

            for (int j = 0; j < n; ++j) {
                numList.add(Integer.parseInt(numStr[j]));
            }

            // step 2 - 명령어 처리
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

    /**
     * step 2 - 명령어를 처리하는 함수
     *
     * R : reverse 수를 세서 마지막에 한번 진행한다
     *     countOfR : 홀수 - reverse / 짝수 - 그대로
     * D : countOfR을 확인해서 현재 reverse 상태면 end를, 아닌 상태면 start를 지운다
     *      방식 1. deque 사용
     *      방식 2. list에서 start,end index 관리하기
     *      방식 3. list에서 remove 하기 (시간 많이 걸림)
     *
     * @param p 명령어 리스트
     * @param numList 숫자 리스트
     * @return true : 명령어 처리 성공 / false : error
     */
    private static boolean command(String p, List<Integer> numList) {
        int countOfR = 0;
        int start = 0, end = numList.size();
        int count = numList.size();

        // 명령어 순차 처리
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

        // 명령어 처리 끝나고 R count 확인해서 reverse 시키기
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
