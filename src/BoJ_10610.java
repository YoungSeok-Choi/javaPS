import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



// 1의자리수가 0인지, 자리수의 숫자를 누적했을때 3으로 나뉘어지는지 체크하는 아이디어는 문제를 자주 풀어봐야 알 수 있는건지..
// 해당 로직을 자주 복습할 것.
public class BoJ_10610 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();

        // 문자열로 입력받고, 자릿수로 접근.
        int[] num = new int[10];
        int len = N.length();
        int total = 0;

        for (int i = 0; i < len; i++) {
            int temp = Integer.parseInt(N.substring(i, i + 1)); //  i번째 자리수의 빈도 수 측정
            num[temp] += 1;
            total += temp;
        }

        // 1의 자리수가 0이 아니고 뭐가 있으면 30으로 안나누어 떨어지고.. 해당 숫자를 누적해서 더한 값이 3의 배수... 이런 아이디어는 어떻게 생각하지?
        if (N.contains("0") && total % 3 == 0) {
            for (int i = 9; i >= 0; i--) {
                while (num[i] > 0) {
                    System.out.print(i);
                    num[i]--; // 0 ~ 9 까지 기록된 빈도 수에 따라 높은 9 부터 출력해 나감
                }
            }
        } else {
            System.out.println(-1);
        }
    }
}
