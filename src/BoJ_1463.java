import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// 백준 알고리즘_1로 만들기 문제
// 답을 구할 수 없는 가장 작은경우 계산하기...
// 어려운게 당연한 것 이거는 먼저 받아들이고 응용하면서 이해가 되도록 만들어야 한다.

public class BoJ_1463 {
    public static int[] answers;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        answers = new int[N + 1];
        int answer = solve(N);
        System.out.println(answer);
    }

    // Top-down strategy..
    public static int solve(int n) {

        // (n/2) +1 자체가 이미 일반화된 계산식임.. ㅅㅂ 이떻게 이렇게 뽑아낼 수 있는거지.?
        if (n == 1) return 0;
        if (answers[n] > 0) return answers[n]; // 이미 메모이제이션 된 값이 존재한다면, 바로 리턴.
        answers[n] = solve(n - 1) + 1; // 이게 왜 플러스 1번이야
        if (n % 2 == 0) {
            int comp = solve(n / 2) + 1;
            if (answers[n] > comp) answers[n] = comp;
        }
        if (n % 3 == 0) {
            int comp = solve(n / 3) + 1;
            if (answers[n] > comp) answers[n] = comp;
        }
        return answers[n];
    }

    public static void solveBottomUp(int n) {
        answers[1] = 0;
        for (int i = 2; i < n; i++) { // 2 부터가 아닌 2번 인덱스, 그러니까 0안쓰고 1은 원소문제라서 값 넣어놓고, 분해 가능한 가장 작은 문제 2무터 시작하는거임.
            answers[i] = answers[i - 1] + 1;
            if (i % 2 == 0 && answers[i] > answers[i / 2] + 1) {
                answers[i] = answers[i / 2] + 1;
            }
            if (i % 3 == 0 && answers[i] > answers[i / 3] + 1) {
                answers[i] = answers[i / 3] + 1;
            }
        }
    }
}
