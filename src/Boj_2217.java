import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

// 백준_로프 문제
// 일반적인 그리디 문제, 여기서 골드로 넘어가면 벽이 확 느껴진다.. 코딩 잘하고싶음 ㅠ
public class Boj_2217 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ropes = Integer.parseInt(br.readLine());
        Integer rArr[] = new Integer[ropes];

        for (int i = 0; i < ropes; i++) {
            rArr[i] = Integer.parseInt(br.readLine());
        }

        // 역순 정렬을 위해 wrapper 클래스 사용
        Arrays.sort(rArr, Collections.reverseOrder());
        int maxWeight = rArr[0];
        for (int i = 1; i < rArr.length; i++) {
            // 최대값을 갱신. -> 가장 큰 뒤의 값{rArr[i]}으로 병렬연결한 밧줄의 수{i+1}를 곱할떄 나오는 수와 비교해 큰 값 리턴.
            maxWeight = Math.max(maxWeight, rArr[i] * (i + 1));
        }
        System.out.println(maxWeight);
    }
}
