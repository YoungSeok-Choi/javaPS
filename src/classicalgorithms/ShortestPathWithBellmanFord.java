package classicalgorithms;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


// 뭔가 삐그덕거리는 벨만포드...
// 내일 다시 보기!
public class ShortestPathWithBellmanFord {
    static class Pair {
        int u;
        int v;
        int weight;

        public Pair(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    // 객체에 그래프 모든 정보를 넣으면 한개의 리스트로도 표현이 가능하다! 신기방기
    // 모든 경로에 대해 표현해야하니깐, 자료구조가 이렇게 된것 -> 한번만 가면 되는 다익스트라에선 리스트에 인덱스를 표현했는데,
    // 큐에 삽입하는게 아니라 반복문에서 꺼내 사용해야하기 때문임을 알 것.
    private static List<Pair> graph = new ArrayList<>();
    private static int[] distance;
    private static int N;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int V = Integer.parseInt(br.readLine());
        int start = Integer.parseInt(br.readLine());
        distance = new int[N + 1];

        // 그래프 초기화
        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.add(new Pair(from, to, w));
        }
        bellmanFord(start);
        for (int i = 1; i <= N; i++) {
            System.out.print(i + " is ");
            System.out.println(distance[i] == Integer.MAX_VALUE ? "INF" : distance[i]);
        }
    }

    // 벨만포드는 우선순위큐를 아예 사용하지 못하는가? yes!?
    private static void bellmanFord(int start) {

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < graph.size(); j++) {
                // j번째 그래프정보를 받아오기
                int u = graph.get(j).u;
                int v = graph.get(j).v;
                int w = graph.get(j).weight;

                // 초기 start정점을 콕 찝어서 돌리는게 아니라, 건너뛰면서 시작정점이 올 때까지 반복하는거였음...
                // 그럼 자기 차례가 오기 전까지의 정점 업데이트 정보는 모두 무시되는것 아닌가?
                if (distance[u] == Integer.MAX_VALUE) continue;

                if (distance[v] > distance[u] + w) {

                    if (i == N) return;
                    distance[v] = distance[u] + w;
                }
            }
        }
    }
}
