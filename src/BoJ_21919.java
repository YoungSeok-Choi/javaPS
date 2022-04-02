

// 백준: 소수 최소공배수 문제

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 에라토네스어쩌구 채 쓰면 시간초과남.
// 소수 알고리즘 좋은 효율 기억하기. 왜 제곱근 값을 쓰는지
// 최소공배수, 최대공약수 구하는 알고리즘 알아두기.
// 중복값 경계값 예외값 항상 주의하기
public class BoJ_21919 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long answer = 1;

        int N = Integer.parseInt(st.nextToken());

        // 같은 소수값이 들어오는 경우 방지.
        Set<Long> result = new HashSet<>();
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            long temp = Long.parseLong(st.nextToken());
            if (temp == 2) {
                result.add(temp);
            } else if (isPrime(temp)) {
                result.add(temp);
            }
        }

        List<Long> result1 = new ArrayList<>(result);

        if (result1.size() <= 0) {
            System.out.println(-1);
        } else {
            for (int i = 0; i < result1.size(); i++) {
                answer *= result1.get(i);
            }
            System.out.println(answer);
        }
    }

    static boolean isPrime(long num) {

        if (num % 2 == 0) { // 짝수로 나누어지면 바로 끝내기
            return false;
        }

        // long n = (num / 2) + 1;
        int n = (int) Math.sqrt(num);
        boolean flag = true;

        for (int i = 3; i <= n; i+=2) {
            if (num % i == 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
