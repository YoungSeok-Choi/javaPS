import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 날짜 계산 문제
public class BoJ_1476_날짜계산 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int E, S, M, year = 0;
        E = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int cmpE = 0, cmpS = 0, cmpM = 0;
        while (!(E == cmpE && S == cmpS && M == cmpM)) {
            cmpE++;
            cmpS++;
            cmpM++;
            year++;
            if (cmpE == 16) cmpE = 1;
            if (cmpS == 29) cmpS = 1;
            if (cmpM == 20) cmpM = 1;
        }
        System.out.println(year);
    }
}
