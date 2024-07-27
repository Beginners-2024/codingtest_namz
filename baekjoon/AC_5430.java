package baekjoon;

import java.io.*;
import java.util.*;

public class AC_5430 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; ++i) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());

            List<Integer> numList = new ArrayList<>(n);
            String str = br.readLine();
            String[] numStr = str.substring(1, str.length()-1).split(",");

            for (int j = 0; j < n; ++j) {
                numList.add(Integer.parseInt(numStr[j]));
            }

            command(p, numList);

            if (!numList.isEmpty()) System.out.println(numList);
        }
    }

    private static void command(String p, List<Integer> numList) {
        for (int i = 0; i < p.length(); ++i) {
            if (p.charAt(i) == 'R') {
                Collections.reverse(numList);
            } else if (p.charAt(i) == 'D') {
                if (numList.isEmpty())
                    System.out.println("error");
                else
                    numList.remove(0);
            }
        }
    }
}
