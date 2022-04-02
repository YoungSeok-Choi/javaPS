import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

// 백준_ 온라인판매 문제
// 그리디 문지이고, 정렬을 활용했어야했던 문제
// 역순으로 이미 정렬됐으므로, 계란의 가용수만 충족된다면 문제없다. 데드코드발견
public class BOJ_1246 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nums = br.readLine().split(" ");

        int eggs = Integer.parseInt(nums[0]); // 달걀 수
        int clis = Integer.parseInt(nums[1]); // 고객 수

        int target = 0;
        int sum = 0;

        Integer[] amounts = new Integer[clis];

        for (int i = 0; i < clis; i++) {
            amounts[i] = Integer.parseInt(br.readLine().trim());
        }
        Arrays.sort(amounts, Collections.reverseOrder());

        for (int i = 1; i < amounts.length; i++) {

            // i-1번째 고객보다 더 큰 금액으로 나누어 떨어지는 자리가 오면서, 자신포함 앞의 고객숫자 보다 더 많은 달걀수를 가지고 있다면.
            //  amounts[i - 1] / amounts[i] >= 1 && 이 코드는 데드코드 임.
            if (i + 1 <= eggs) {
                int tempSum = amounts[i] * (i + 1);

                if (sum < tempSum) {
                    sum = tempSum; //  최고수익 갱신
                    target = amounts[i]; // 책정가격 갱신
                }
            }
        }
        System.out.print(target + " " + sum);
    }
}
