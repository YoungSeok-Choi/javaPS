package classicalgorithms;


import javax.crypto.spec.OAEPParameterSpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 라이브러리 사용하지 않고 그래프 구현해보기.
public class PracticeVcatMatrixAndListGraph {

    // unweighted graph
    static class Vertex {
        int to;
        public Vertex(int to) {
            this.to = to;
        }
    }

    public static boolean[] visit;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        // 요게 약간 C wise한 느낌의 그래프 자료구조
        List<Vertex>[] graph = new ArrayList[6];
        visit = new boolean[6];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        // 단방향 그래프로만 자료구조를 만들면 특정 정점에서 기동할 수 있는 정점으로밖에 순회가 불가함. 따라서
        // 단방향일때, 특정 정점에서 모든 정점으로 가는 간선이 존재해야하거나, 양방향이여야함 (모든 정점에 대해서 연결하려면)
        // (다음날 피드백) -> 정점이 존재하지 않아도 됨. 거리가 무한대로 표기가 되어 갈 수 없다는 걸 표시할 수 있음
        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(new Vertex(v));
            graph[v].add(new Vertex(u));
        }

        int input;
        while (true) {
            System.out.println("몇 변정점부터 순회?: ");
            input = Integer.parseInt(br.readLine());
            if (input <= 0) break;
            bfs(graph, input);
            //dfs(graph, input);
        }

    }

    private static void bfs(List<Vertex>[] graph, int input) {

        Arrays.fill(visit, false);
        Queue<Vertex> q = new LinkedList<>();
        q.add(new Vertex(input));

        while (!q.isEmpty()) {

            Vertex v = q.poll();
            int to = v.to;

            if (!visit[to]) {
                visit[to] = true;
                // 방문하지 않았다면 출력하고 큐에 삽입.
                System.out.print(to + " ");
                // 실제 큐를 보니까 뭔가 중복된 값이 엄청 들어간다잉..
                for (Vertex vertex : graph[to]) {
                    q.add(vertex);
                    //System.out.print(to + " ");
                }
            }
        }
    }

    private static void dfs(List<Vertex>[] graph, int input) {

        if (!visit[input]) {
            System.out.println();

        }
    }
}
