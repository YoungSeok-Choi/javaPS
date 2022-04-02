import java.io.*;

// 설탕배달 문제
// 알고리즘 분류 보고 풀었던 문제
// 노가다 문제
// 8번 인덱스를 처리하지 않아서 틀림 떴었음.
//최초의 동적 프록래밍 풀었던 문제다!
public class BoJ_2839 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int sugar = Integer.parseInt(br.readLine());

        int cases[] = new int[5001];

        for (int i = 1; i <= 10; i++) {
            if (i % 3 != 0 && i % 5 != 0) {
                cases[i] = -1;
            }
        }

        cases[3] = 1;
        cases[5] = 1;
        cases[6] = 2;
        cases[8] = 2;
        cases[9] = 3;
        cases[10] = 2;

        for (int i = 11; i < 16; i++) {
            if (i % 2 != 0) {
                cases[i] = 3;
            } else cases[i] = 4;
        }

        for (int i = 16; i < cases.length; i++) {
            cases[i] = cases[i - 5] + 1;
        }

        System.out.println(cases[sugar]);
        br.close();
        bw.close();
    }
}
