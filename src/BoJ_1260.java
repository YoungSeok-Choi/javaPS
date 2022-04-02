import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


// Bfs_Dfs문제.행렬 형태 그래프탐색이 아닌 node형식의 문제이다보니 적응하는데 조금 어려움? 이있었던 문제
// 인접 리스트 관련한 그래프 탐색을 좀더 정확하게 알고 다루어야 할 필요가 있다.
// 라이브러리 사용하지 않고 C스타일로 구현해보면 정말 정확하게 알 것같다.
public class BoJ_1260 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int vertex = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        boolean[] visitBfs = new boolean[vertex+1];
        boolean[] visitDfs = new boolean[vertex+1];

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        // 0번째 노드는 존재하지 않으므로, length 와 동일한 크기의 vertex생성
        for (int i = 0; i <= vertex; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edge; i++) {

            StringTokenizer temp = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(temp.nextToken());
            int v = Integer.parseInt(temp.nextToken());
            graph.get(u).add(v); // 간선 추가
            graph.get(v).add(u);

        }


        dfs(graph, visitDfs, start);
        System.out.println();
        bfs(graph, visitBfs, start);

    }


    public static void dfs(ArrayList<ArrayList<Integer>> graph, boolean[] visitDfs, int start) {

        LinkedList<Integer> stack = new LinkedList<Integer>();

        for (int i = 0; i < graph.size(); i++) {
            Collections.sort(graph.get(i), Collections.reverseOrder()); // 모든 포인터 배열 정렬
        }

        stack.push(start);

        // 정점이 존재하는동안
        while (!stack.isEmpty()) {

            start = stack.pop();
            Iterator<Integer> it = graph.get(start).iterator();

            // 방문하지 않은 정점이라면, 푸쉬하고 순회.
            if (visitDfs[start] == false) {
                visitDfs[start] = true;
                System.out.print(start + " ");

                while (it.hasNext()) {
                    int V = it.next();
                    stack.push(V);
                }
            }
        }
    }

    public static void bfs(ArrayList<ArrayList<Integer>> graph, boolean[] visitBfs, int start) {

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < graph.size(); i++) {
            Collections.sort(graph.get(i)); // 모든 포인터 배열 정렬
        }

        queue.add(start);

        while (!queue.isEmpty()) {

            start = queue.poll();
            Iterator<Integer> it = graph.get(start).iterator();

            if (visitBfs[start] == false) {

                visitBfs[start] = true;
                System.out.print(start + " ");

                while (it.hasNext()) {
                    int V = it.next();
                    queue.add(V);
                }
            }
        }
    }
}
