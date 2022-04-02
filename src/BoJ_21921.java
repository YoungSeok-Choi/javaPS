import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 21921_블로그
// 슬라이딩 윈도우 방식? 인가 뭔가 인듯한데...
// 1 ~ X번째 윈도우를 미리 만들어서 마지막인덱스를 빼고 다음 슬라이딩에 포함되는 인덱스를 더해 나가는 방식은 진짜 대단했다.
// 어떻게 하면 그런 아이디어가 팍! 하고 떠오를까? 연습 많이해야겠지
public class BoJ_21921 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        int max; // 최대 방문자 일수를 저장
        int count = 1; // 최대 방문일수가 여러 번 있을때 저장.

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 처음 0번 ~ X -1번째 까지의 윈도우의 합을 계산
        int tempSum = 0;
        for (int i = 0; i < X; i++) {
            tempSum += arr[i];
        }

        max = tempSum;

        for (int i = 1; i < arr.length - X + 1; i++) {

            // i-1번째의 값을 뺴고, 슬라이딩 윈도우의 다음 칸 을 더하면 X크기의 슬라이딩 윈도우가 한칸 옮겨지는것
            tempSum -= arr[i - 1];
            tempSum += arr[i + X - 1];

            if (max < tempSum) {
                max = tempSum;
                count = 1; // 최대값이 갱신되는 것이므로, 다시 1일로 초기화
            } else if (max == tempSum) {
                count++; //같은 최대값이 나왔으므로 기간을 증가.
            }
        }

        if (max == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(max);
            System.out.println(count);
        }

    }
}
