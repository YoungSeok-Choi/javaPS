import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 거스름돈
// 이건 기컴 예제수준.... 근데 무시는 ㄴㄴ해 이때 아마 50원 뺐을겨
public class BoJ_5585 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int input = Integer.parseInt(br.readLine());
        int count = 0;
        int[] en = {500, 100, 50, 10, 5, 1};

        input = 1000 - input;

        for (int i = 0; i < en.length; i++) {
            count = count + (input / en[i]);
            input %= en[i];
        }
        System.out.println(count);
    }
}
