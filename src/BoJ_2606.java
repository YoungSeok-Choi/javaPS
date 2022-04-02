import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// line.charAt(j)-'0' 문자입력 0 ~ 9를 숫자 0 ~ 9로 변환하는 처리
// line.charAt(j)-'A' 문자입력 A ~ Z(대문자 구분)을 숫자 인덱스 0 ~ 25로 변환하는 처리
// 심심찮게 나오니깐 공부해놔라
public class BoJ_2606 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int computers = Integer.parseInt(br.readLine());
        int edges = Integer.parseInt(br.readLine());
        List<List<Integer>> graph = new ArrayList<>();
        boolean[] visited = new boolean[computers + 1];

        // 0번 정점은 사용하지 않는다.
        for (int i = 0; i <= computers; i++) {
            graph.add(new ArrayList<>());
        }

        // 그래프 초기화, 단방향 그래프 --> 단방향으로 돌리면 문제가 틀림. 양방향으로 해야 모든 연결 그래프를 탐색할 수 있다.
        for (int i = 0; i < edges; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int result = solution(graph, visited, 1, 0);
        --result; // 1번 정점은 감염 숫자에 포함하지 않으므로, 감소
        System.out.println(result);
        br.close();

    }

    public static int solution(List<List<Integer>> graph, boolean[] visited, int start, int count) {

        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {

            int cur = q.poll();
            Iterator<Integer> it = graph.get(cur).iterator();

            if (visited[cur] == false) {

                visited[cur] = true;
                count++;

                while (it.hasNext()) {
                    q.add(it.next());
                }
            }
        }
        return count;
    }

}
