import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

// 위대한 슈퍼스타K문제
// 잘 풀어놓고 마지막에 출력 바보같이 해서 해맸던 문제..
// 실제 코딩테스트떄 이러면 큰일남
// 어떤 실수했는지 코드 볼 때마다 생각하기.
public class BoJ_2865 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        double sum = 0.0; // float는 마지막에  F붙여줘야한다나..

        // 0 번 인덱스는 사용하지 않음.
        Double points[] = new Double[N+1];
        Arrays.fill(points, -1.0);

        // i 번째 round 별로 맵 에 삽입
        for (int i = 0; i < M; i++) {
            HashMap<Integer, Double> abilities = new HashMap<>();
            StringTokenizer stTemp = new StringTokenizer(br.readLine(), " ");

            for (int j = 1; j <= N; j++) {
                int numOfCandi = Integer.parseInt(stTemp.nextToken());
                double point = Double.parseDouble(stTemp.nextToken());
                abilities.put(numOfCandi, point);
            }

            // j번째 지원자의 최고능력 갱신
            for (int j = 1; j <= N; j++) {
                double temp = abilities.get(j);
                if (points[j] < temp) points[j] = temp;
            }
        }

        Arrays.sort(points, Collections.reverseOrder());

        // 역순정렬 이후 K 명에 대한 최대값 누적(본선진출자.)
        for (int i = 0; i < K; i++) {
            sum += points[i];
        }
        System.out.printf("%.1f\n", sum); // C스타일
        //System.out.println(String.format("%.1f",(double)Math.round(sum))); //Java스타일
    }
}
