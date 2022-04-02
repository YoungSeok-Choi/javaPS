import com.sun.jdi.PathSearchingVirtualMachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// 돌머가리... 씨이발
public class BoJ_14502O {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long x = Long.parseLong(st.nextToken()); // row
        long y = Long.parseLong(st.nextToken());// column
        long w = Long.parseLong(st.nextToken()); // 칸 이동하는데 걸리는 시
        long s = Long.parseLong(st.nextToken()); // 대각

        if (x < y) { // x가 큰값으로 오도록 하기.
            long temp = x;
            x = y;
            y = temp;
        }

        if (2 * w > s) {
            // 대각선으로 질러만가기. (하나의 좌표가 홀수일땐 대각이동이 안되므로, 한칸 앞에서 수직이동.)
            if (w < s) {
                System.out.println(y * s + (x - y) * w);
            }  else {
                if ((x + y) % 2 != 0) {
                    System.out.println((x - 1) * s + w);
                } else {
                    System.out.println(x * s);
                }
            }
        } else if (2 * w < s) {
            System.out.println((x + y) * w);
        } else {
            System.out.println((x + y) * w);
        }
    }
}
