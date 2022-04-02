import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// 반성하고 또 반성해라
// 처음 생각하고 잘못 짠 코드.. 경우의 수를 추상화 하지 못하고 모든 케이스에 대해 처리하려고 한 문제가 있었다.
public class BoJ_14502X {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long x = Integer.parseInt(st.nextToken()); // row
        long y = Integer.parseInt(st.nextToken()); // column
        long w = Integer.parseInt(st.nextToken()); // 칸 이동하는데 걸리는 시
        long s = Integer.parseInt(st.nextToken()); // 대각

        int startX = 0;
        int startY = 0;
        long cost = 0;
        // 지그재그 처리.. 곱하기 2 가 아니더라도 대각이 더 작은경우가 존재

        // 0,0 --> 0,1 --> 1,1이동의 비용 VS 0,0 --> 1,1 비용을 비교
        if (w >= s) { // 가로세로의 이동 가중치가 대각보다 애초에 큰 경우
            if (x % 2 != 0 ^ y % 2 != 0) {
                while (startX < x && startY < y) { // 좌표를 1,1씩 증가하며 대각의 비용을 더함
                    startX++;
                    startY++;
                    cost += s;
                }
                int toggle = -1;
                if (startX < x) { // 행 방향의 끝에 다다라서 깨진경우(세로로 더 이동이 필요한경우 대각선 지그재.)
                    while (startX < x - 1) {
                        startX++;
                        if (toggle == -1) {
                            startY--;
                        } else {
                            startY++;
                        }
                        toggle *= -1;
                        cost += s;
                    }
                    cost += w; // 마지막 한 칸은 직선으로 이동
                } else if (startY < y - 1) { // 열 방향의 끝에 다다라서 깨진경우(가로로 이동이 필요한경우)
                    while (startY < y) {
                        startY++;
                        if (toggle == -1) {
                            startX--;
                        } else {
                            startX++;
                        }
                        toggle *= -1;
                        cost += s;
                    }
                    cost += w;
                }
            } else {
                while (startX < x && startY < y) { // 좌표를 1,1씩 증가하며 대각의 비용을 더함
                    startX++;
                    startY++;
                    cost += s;
                }
                int toggle = -1;
                if (startX < x) { // 행 방향의 끝에 다다라서 깨진경우(세로로 더 이동이 필요한경우 대각선 지그재.)
                    while (startX < x) {
                        startX++;
                        if (toggle == -1) {
                            startY--;
                        } else {
                            startY++;
                        }
                        toggle *= -1;
                        cost += s;
                    }
                } else if (startY < y) { // 열 방향의 끝에 다다라서 깨진경우(가로로 이동이 필요한경우)
                    while (startY < y) {
                        startY++;
                        if (toggle == -1) {
                            startX--;
                        } else {
                            startX++;
                        }
                        toggle *= -1;
                        cost += s;
                    }
                }
            }
        } else { // 가로세로 이동의 가중치가 대각보다 작은경우의 분기.
            if (w * 2 <= s) {
                while (startX < x) {
                    startX++;
                    cost += w;
                }
                while (startY < y) {
                    startY++;
                    cost += w;
                }
            } else {
                while (startX < x && startY < y) { // 좌표를 1,1씩 증가하며 대각의 비용을 더함
                    startX++;
                    startY++;
                    cost += s;
                }
                if (startX < x) { // 행 방향의 끝에 다다라서 깨진경우(세로로 더 이동이 필요한경우 대각선 지그재.)
                    while (startX < x) {
                        startX++;
                        cost += w;
                    }
                } else if (startY < y) { // 열 방향의 끝에 다다라서 깨진경우(가로로 이동이 필요한경우)
                    while (startY < y) {
                        startY++;
                        cost += w;
                    }
                }
            }
        }
        System.out.println(cost);
    }
}
