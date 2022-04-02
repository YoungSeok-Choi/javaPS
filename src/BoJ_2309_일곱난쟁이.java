import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 일곱난쟁이 문제
// 난쟁이가 아닌 사람 두명을 찾으면 됐다... 미친
public class BoJ_2309_일곱난쟁이 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int idxI = 0, idxJ = 0; // 난쟁이가 아닌 인덱스 저장변수

        int[] smalls = new int[9];
        int sum = 0;

        for (int i = 0; i < smalls.length; i++) {
            int temp = Integer.parseInt(br.readLine());
            smalls[i] = temp;
            sum += temp;
        }

        Arrays.sort(smalls);
        for (int i = 0; i < smalls.length; i++) {
            for (int j = i + 1; j < smalls.length; j++) {
                int tempSum = smalls[i] + smalls[j];
                if (sum - tempSum == 100) { // 두 명 제외하고 합이 100이라면 인덱스 저장
                    idxI = i;
                    idxJ = j;
                    break;
                }
            }
        }

        for (int i = 0; i < smalls.length; i++) {
            if (i == idxI || i == idxJ) continue;
            System.out.println(smalls[i]);
        }
    }
}
