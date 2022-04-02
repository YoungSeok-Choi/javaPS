import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// 왜 이런방식의 풀이를 생각하지 못했을까 반성하기
// 관련 문제가 나왔을때 어떻게 응용할지 생각하기.
// 계속 생각하고 코딩하기...
public class BoJ_1932 {

    public static int N;
    public static int[][] input;
    public static Integer[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        input = new int[N][N];
        dp = new Integer[N][N];

        for (int i = 0; i < N; i++) {
            String[] tempInput = br.readLine().split(" ");
            for (int j = 0; j < tempInput.length; j++) {
                input[i][j] = Integer.parseInt(tempInput[j]);
            }
        }

        for (int i = 0; i < N; i++) {
            dp[N - 1][i] = input[N - 1][i];
        }
        // dp배열의 가장 위 인덱스 결과값이 최대값이 된다.
        int ans = solve(0, 0);
        System.out.println(ans);
    }

    public static int solve(int depth, int idx) {
        if (depth == N - 1) return dp[depth][idx];

        // 초기화 되지않은 Integer배열은 null값일까?
        if (dp[depth][idx] == null) {
            dp[depth][idx] = Math.max(solve(depth + 1, idx), solve(depth + 1, idx + 1)) + input[depth][idx];
        }
        return dp[depth][idx];
    }
}