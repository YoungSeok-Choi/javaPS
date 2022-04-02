import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//BoJ_단어 수학
// 전혀 접근이나 풀이 시도가 불가했던문제
// 문자 입력 길이별로 자릿수를 만들었던 점.
// 알파벳을 인덱스로 치환한 부분.
// 값을 계속 더해나가
public class BoJ_1339 {

    public static int[] alpha = new int[26];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            String line = br.readLine();
            int size = line.length();

            // 문자 자릿수에 대해 가중치를 만드는 코드
            int base = (int) Math.pow(10, size - 1); // IF length4: THEN this var IS 1000

            for (int y = 0; y < size; y++) {

                //문자열 연산.. A - A == 0, A - B = -1, B - A = 1
                alpha[line.charAt(y) - 'A'] += base;
                base /= 10; // 10000 -> 1000 -> 100 -> 10 -> 1

            }
        }

        Arrays.sort(alpha);
        int ans = 0;


        // 가장 낮은순으로 정렬해서 맨 뒤 인덱스부터 접근 --> 가장 자릿수가 높은 알파벳 단위부터 9 -> 1 까지 곱해서 누적합을 한다.
        for (int x = 25; x >= 17; x--) {
            ans += alpha[x] * (x - 16);
        }

        System.out.println(ans);
    }
}
