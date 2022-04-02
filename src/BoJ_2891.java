import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 그리디_카약빌려주기겨
// 예비카약을 가져온 팀에서 카약파손이 있는 경우를 고민하지 않고 초반에 코딩함
// 에전 프로그래머스에서 비슷한 문제가 있었어
public class BoJ_2891 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        boolean[] isBroken = new boolean[n];// 카약이 부서진 팀을 표기
        boolean[] available = new boolean[n]; // 카약을 빌려줄 수 있는 팀을 표기
        int count = 0;

        StringTokenizer st1 = new StringTokenizer(br.readLine());

        //s개의 팀의 카약이 부서짐
        for (int i = 0; i < s; i++) {
            int idx = Integer.parseInt(st1.nextToken());
            idx--; //배열은0부터 시작
            isBroken[idx] = true;
        }

        // 예비 카약을 가져온 팀 초기화
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < r; i++) {
            int idx = Integer.parseInt(st2.nextToken());
            idx--; //배열은0부터 시작
            available[idx] = true;
        }

        // 예비 카약을 가져온 팀 중에서 손상이 발생했을경우, 여분의 카약으로 출전해야하므로, 빌려줄수 없는 조건을 처리
        for (int i = 0; i < available.length; i++) {
            if (isBroken[i] && available[i]) {
                isBroken[i] = false;
                available[i] = false;
            }
        }

        for (int i = 0; i < available.length; i++) {
            // 첫 번째 팀이 빌려줄 수 있을 때 예외처리 (첫번째 이전의 팀은 존재하지 않는다.)
            // 두 번째 팀의 카약이 부서진 경우 첫 번째 팀에서 빌려줌
            if (i == 0 && available[i]) {
                if (isBroken[i + 1]) {
                    available[i] = false;
                    isBroken[i + 1] = false;
                }
            } else if (i == available.length - 1 && available[i]) {
                // 마지막 팀이 빌려줄 수 있을때 예외처리 (마지막 팀 이후의 팀은 존재하지 않는다.)
                // 마지막 팀 앞의 카약이 부서진 경우 마지막팀에서 빌려줌
                if (isBroken[i - 1]) {
                    available[i] = false;
                    isBroken[i - 1] = false;
                }
            } else if (available[i]){
                // 첫 번째, 마지막 순서도 아닌 팀이 빌려줄 수 있을때
                // i번째의 앞 혹은 뒤 팀에게 빌려줄 수 있다고 했는데 먼저 이전팀에게 빌려주고 아니라면 뒷 팀에게 빌려준다.
                if (isBroken[i - 1]) {
                    available[i] = false;
                    isBroken[i - 1] = false;
                } else if (isBroken[i + 1]) {
                    available[i] = false;
                    isBroken[i + 1] = false;
                }
            }
        }

        for (int i = 0; i < isBroken.length; i++) {
            if (isBroken[i]) ++count;
        }
        System.out.println(count);
    }
}
