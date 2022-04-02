import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준_국회의원 선거
// 결국에는 N^2복잡도인데... 입력값이 커지게된다면 어떻게 최대값 구하는 부분을 최적화할것인가? 문제
// 최대 힙을 사용해서 자신의 값 + 인덱스가 담긴 객체가 최상단에 나올때 까지?
public class BoJ_1417 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int candidates = Integer.parseInt(br.readLine());
        int[] cArr = new int[candidates];

        for (int i = 0; i < candidates; i++) {
            cArr[i] = Integer.parseInt(br.readLine());
        }


        // 최대값 인덱스 초기화
        int idx = 0;
        int votes = 0;
        do {
            idx = 0;
            int max = Integer.MIN_VALUE;

            // 같은 다솜이와 동률이 나와도 다솜이가 0번 인덱스이니깐. 뒤 순번 후보의 인덱스로 갱신이 됨
            for (int i = 0; i < cArr.length; i++) {
                if (max <= cArr[i]) {
                    max = cArr[i];
                    idx = i;
                }
            }
            if (idx == 0) break;
            votes++;
            --cArr[idx];
            ++cArr[0];

            // 최대값을 가지는 인덱스가 다솜이가 아닌동안 loof
            // 0301. 위의 if문에서 깨지는것이지 while조건에서 깨지는것은 아니다.
        } while (idx != 0);
        System.out.println(votes);
    }
}
