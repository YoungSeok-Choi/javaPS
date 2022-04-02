import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class CodeUp_BestPizza {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int toppings = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int priceDow = Integer.parseInt(st.nextToken());
        int priceTopping = Integer.parseInt(st.nextToken());

        int kalDow = Integer.parseInt(br.readLine());

        Integer kalTopping[] = new Integer[toppings];

        for (int i = 0; i < toppings; i++) {
            kalTopping[i] = Integer.parseInt(br.readLine());

        }
        Arrays.sort(kalTopping, Collections.reverseOrder());


        int max = kalDow / priceDow; // 피자도우만 주문했을경우를 최고의 피자로 가정.

        int sumOfKal = kalDow; // 반복문 내에서 누적해나갈 칼로리 변수, 초기 도우의 칼로리만 존재.
        int sumOfPrice = priceDow; // 가격은 누적하여 합해나가는 것이 아닌, i번째 반복에 따라 새롭게 더해저야함

        for (int i = 0; i < kalTopping.length; i++) {

            int tempMax = 0;
            int tempPrice = 0;

            sumOfKal += kalTopping[i]; // 토핑과 도우의 누적합
            tempPrice = sumOfPrice + priceTopping * (i + 1); // 도우의 가격과, 들어간 토핑의 개수별 가격이 더해진 ㄱ밧
            tempMax = sumOfKal / tempPrice;

            max = max < tempMax ? tempMax : max; // 최고의 피자 갱신
        }
        System.out.println(max);
    }
}
