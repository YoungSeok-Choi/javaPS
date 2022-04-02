import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// 리모컨 문제...
// 접근방법 아예 안떠오름.. 그냥 떠먹인 수준 ㅠㅠㅠ 혼자서 어떻게 생각해낼 수 있을지 고민해보자아..
// 백준 강의를 보고야 문제를 풀수 있었다ㅜ(코드 말고 접근방법)
// 새로운 문제를 맞닥뜨렸을때 어떻게 이런 생각을 혼자서 해날 수 있을지 고민하기..
public class BoJ_1107_리모컨 {

    public static boolean[] isBroken = new boolean[10];
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int target = Integer.parseInt(br.readLine());
        int start = 100;
        int cnt;
        int min = Math.abs(target - start); // 초기 100번에서 + / - 버튼만 누르고 갈 수 있는 횟수를 최소값으로
        int numOfBroken = Integer.parseInt(br.readLine());
        int[] buttons = new int[numOfBroken];

        if (target == start) { // 현재 채널과 바꿀 채널이 동일한 경우 즉시 종료
            System.out.println(0);
            System.exit(0);
        }

        if (numOfBroken > 0) { // 버튼 고장인게 하나도 없으면, 입력에서 오류가 발생함
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < numOfBroken; i++) {
                int temp = Integer.parseInt(st.nextToken());
                buttons[i] = temp;
                isBroken[temp] = true;
            }
        }

        for (int i = 0; i < 1000000; i++) {

            Integer temp = i;
            String tempStr = temp.toString();
            cnt = 0;
            boolean flag = true;

            for (int j = 0; j < tempStr.length(); j++) {

                int button = tempStr.charAt(j) - '0'; //문자열 하나를 숫자로 바꾸기
                if (isBroken[button]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                cnt = cnt + tempStr.length(); // 숫자 버튼 누르는 횟수
                cnt = cnt + Math.abs(target - temp); // + or - 버튼 누르는 횟수
                min = Math.min(min, cnt); // 최솟값 갱신
            }
        }
        System.out.println(min);
    }
}