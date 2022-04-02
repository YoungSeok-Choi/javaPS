import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



// 21919 시험 감독
// 자료형 잘 선택하기.
// 가장 최악의 경우일 때 시간복잡도, 가장 큰/작은 데이터가 들어올 때의 프로그램의 안정성.
public class BoJ_13458 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] testJang = new int[N];
        long sum = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            testJang[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());


        for (int i = 0; i < testJang.length; i++) {
            sum += resolve(testJang[i], B, C);
        }
        System.out.println(sum);
    }

    public static long resolve(int curNum, int B, int C) {

        long tempSum = 0;

        // 한 명의 총감독관은 존재하여야 한다.
        curNum -= B;
        tempSum++;

        if (curNum <= 0) {
            return tempSum;
        } else { // 여러 명의 부감독관은 존재할 수 있다
            tempSum = tempSum + (curNum / C);
            curNum = curNum % C;
        }

        if (curNum > 0) tempSum++;

        return tempSum;
    }
}
