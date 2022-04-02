import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// O(n^4)... 브루트포스 하는 방식 접근을 잘 모르겠음.. -> O(n^3)으로 만드는 방법이 있다고 함. 바꾸는 행 열만 계산해서... 이거 만들어보기.
// 이중포문 돌면서, 먼저 바꾸고, 최대값  비교하교, 최대값이 아니면 복구해주고?(다른탐색을 위해 최대값을 찾았더라도 다시 복구하는 방향으로 하자.)
// 브루트포스 할 때 경우의 수 구하는 방식이랑 줄일 수 있는 방법이 있는지 계속 생각해보기..
// 나는 네 방향 다 탐색해야하나? 생각했는데, 강의에서는 오른, 아래쪽만 계산하면 모든 칸에대해서 계산 가능하다고함.. 진짜 대다내..
public class BoJ_3085_사탕게임 {

    public static class Points {
        int x;
        int y;
        public Points(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str;

        int N = Integer.parseInt(br.readLine());
        int[][] candies = new int[N][N];
        int ans = 0;

        // C -> 1   P -> 2   Z -> 3   Y -> 4
        // 입력
        for (int i = 0; i < N; i++) {
            str = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                if ("C".equals(str[j])) candies[i][j] = 1;
                else if ("P".equals(str[j])) candies[i][j] = 2;
                else if ("Z".equals(str[j])) candies[i][j] = 3;
                else candies[i][j] = 4;
            }
        }

        // 오른쪽과 아래
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j + 1 < N) { // 행 스왑해나가며 최대값 계산
                    swap(candies, new Points(i, j), new Points(i, j + 1)); // from  -> to
                    int temp = check(candies);
                    ans = ans < temp ? temp : ans;
                    swap(candies, new Points(i, j + 1), new Points(i, j)); // 위에서의 스왑 복구
                }
                if (i + 1 < N) { // 열 스왑해 나가며 최대값 계산
                    swap(candies, new Points(i, j), new Points(i + 1, j)); // from  -> to
                    int temp = check(candies);
                    ans = ans < temp ? temp : ans;
                    swap(candies, new Points(i + 1, j), new Points(i, j)); // 위에서의 스왑 복구
                }
            }
        }
        System.out.println(ans);
    }

    public static int check(int[][] candies) {

        int size = candies.length;
        int ans = 1; // 적어도 한 칸은 먹을 수 있다.

        for (int i = 0; i < size; i++) {
            int cnt = 1;
            for (int j = 0; j < size - 1; j++) {
                if (candies[i][j] == candies[i][j + 1]) cnt++;
                else cnt = 1;
                ans = Math.max(ans, cnt);
            }
            cnt = 1; // 열 탐색 이전 초기화
            for (int j = 0; j < size - 1; j++) {
                if (candies[j][i] == candies[j + 1][i]) cnt++;
                else cnt = 1;
                ans = Math.max(ans, cnt);
            }
        }
        return ans;
    }

    public static void swap(int[][] candies, Points from, Points to) {
        int temp = candies[from.x][from.y];
        candies[from.x][from.y] = candies[to.x][to.y];
        candies[to.x][to.y] = temp;
    }
}
