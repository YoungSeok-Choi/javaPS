import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// BoJ_1912 연속합
// 부분 최대합 문제
// 강의에서 배웠더 문제인데 헷갈렸던 부분이 많았다.
// 전체 최대값, 특정시점 최대값을 비교하며 갱신해 나가는게 참신했다
public class BoJ_1912 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = solve(arr);
        System.out.println(ans);
    }

    private static int solve(int[] arr) {

        int max = arr[0];
        int allMax = arr[0];

        for (int i = 1; i < arr.length; i++) {
            max = Math.max(arr[i], max + arr[i]);
            allMax = Math.max(max, allMax);
        }
        return allMax;
    }
}
