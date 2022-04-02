import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;


// 기본적인 스택 사용하는 문제
// 나중에 라이브러리 구현하지 않고 내가 구현해보는것도 연습이 될듯? 해보긴 했지만
public class BoJ_10828_Stack {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        Stack<Integer> stack = new Stack<>();
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());
            String temp = st.nextToken();

            if (temp.equals("push")) {

                stack.push(Integer.parseInt(st.nextToken()));

            } else if (temp.equals("pop")) {

                if (stack.isEmpty()) bw.write("-1\n");
                else bw.write(stack.pop() + "\n");

            } else if (temp.equals("top")) {

                if (stack.isEmpty()) bw.write("-1\n");
                else bw.write(stack.peek() + "\n");

            } else if (temp.equals("size")) {

                bw.write(stack.size() + "\n");

            } else if (temp.equals("empty")) {

                if (stack.isEmpty()) bw.write("1\n");
                else bw.write("0\n");
            }
        }
        bw.flush();

        br.close();
        bw.close();
    }
}
