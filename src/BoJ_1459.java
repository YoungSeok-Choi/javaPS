import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// 너무 복잡하게 생각해서 고생했었던 문제, 추상화의 중요성을 느낌
// 경우의 수가 어떻게 있는지 잘 추상화해서 가르는게 나중 문제풀때라도 꼭 필요하다.
// 대각을 넘어가는게 꽤나 재밌었음. 신박하기도 하고
// 난 이걸 반복문으로 풀었는데, 수식을 도출하는게 참 부족함..
// 반복문 이전에 수식으로 뽑아낼 순 없는지 생각하기.
public class BoJ_1459 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long x = Long.parseLong(st.nextToken()); // row
        long y = Long.parseLong(st.nextToken());// column
        long w = Long.parseLong(st.nextToken()); // 칸 이동하는데 걸리는 시간
        long s = Long.parseLong(st.nextToken()); // 대각이동시간

        if (x < y) { // x가 큰값으로 오도록 하기.
            long temp = x;
            x = y;
            y = temp;
        }

        if (2 * w > s) {
            // 대각선으로 질러만가기. (하나의 좌표가 홀수일땐 대각이동이 안되므로, 한칸 앞에서 수직이동.)

            if (w < s) { // 한 쪽 방향에 끝에 다다랐을땐. 수직으로 이동하는게 효과적 대각 지그재그보다
                System.out.println(y * s + (x - y) * w);
            }  else {
                if ((x + y) % 2 != 0) { // 짝수 칸이 아닌경우, 수직/수평이동을 한 번 해야함.
                    System.out.println((x - 1) * s + w);
                } else {
                    System.out.println(x * s); // 지그재그로 다이렉트
                }
            }

        } else if (2 * w < s) { // 한 방향으로 쭉 가서 나머지 방향으로 쭉 가기.
            System.out.println((x + y) * w);
        } else {
            System.out.println((x + y) * w);
        }
    }

}
