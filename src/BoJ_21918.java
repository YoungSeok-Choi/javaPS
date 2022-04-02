import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// 참 거짓으로 접근하는 방식은 좋았으니 입력을 적절하게 받아들이지 못해 시간을 많이 낭비한 문제
// 문자열을 그대로 부울형에 대입하면 0으로 대입되니 미리 확인해보고 정수형으로 변환 이후에 대입하는 과정 거칠것
// 백준 버튼문제
public class BoJ_21918 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Boolean[] bulbs = new Boolean[N];


        // 문자열을 바로 부울 형에 때려박으면 안된다...
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int tempInt = Integer.parseInt(st1.nextToken());
            if (tempInt == 1) bulbs[i] = true;
            else bulbs[i] = false;
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer temp = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(temp.nextToken());
            int arg1st = Integer.parseInt(temp.nextToken());
            int arg2nd = Integer.parseInt(temp.nextToken());

            switch (num) {
                case 1:
                    funcOne(bulbs, arg1st, arg2nd);
                    continue;
                case 2:
                    funcTwo(bulbs, arg1st, arg2nd);
                    continue;
                case 3:
                    funcThree(bulbs, arg1st, arg2nd);
                    continue;
                case 4:
                    funcFour(bulbs, arg1st, arg2nd);

                    continue;
                default:
                    System.out.println("wrong number");
            }
        }
        printArr(bulbs);
    }

    public static void printArr(Boolean[] bulbs) {
        for (Boolean bulb : bulbs) {
            if (bulb) {
                System.out.print(1 + " ");
            } else {
                System.out.print(0 + " ");
            }
        }
    }

    public static void funcOne(Boolean[] bulbs, int i, int x) {
        bulbs[--i] = x == 1;
    }

    public static void funcTwo(Boolean[] bulbs, int l, int r) {
        for (int i = l - 1; i <= r - 1; i++) {
            bulbs[i] = !bulbs[i];
        }
    }

    public static void funcThree(Boolean[] bulbs, int l, int r) {
        for (int i = l - 1; i <= r - 1; i++) {
            bulbs[i] = false;
        }
    }

    public static void funcFour(Boolean[]bulbs, int l, int r) {
        for (int i = l - 1; i <= r - 1; i++) {
            bulbs[i] = true;
        }
    }
}
