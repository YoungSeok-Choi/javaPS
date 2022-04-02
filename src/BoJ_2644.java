import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// dfs, bfs 둘다 풀어보기.
// 촌수계산
public class BoJ_2644 {

    static class Thing {
        int cur;
        int chon;
        public Thing(int cur, int chon) {
            this.cur = cur;
            this.chon = chon;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int vertex = Integer.parseInt(br.readLine());

        // 토크나이저의 두 번쨰 매개변수가 없는경우, 기본값으로 공백기준 토큰화
        StringTokenizer st = new StringTokenizer(br.readLine());

        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(br.readLine());
        List<List<Integer>> graph = new ArrayList<>();

        boolean[] visitBfs = new boolean[vertex + 1];
        boolean[] visitDfs = new boolean[vertex + 1];

        // 0 번 정점은 사용하지 않음
        for (int i = 0; i <= vertex; i++) {
            graph.add(new ArrayList<>());
        }

        // 양방향으로 해야 순회가능 --> 그래프 초기화
        for (int i = 0; i < edge; i++) {
            StringTokenizer tempSt = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(tempSt.nextToken());
            int v = Integer.parseInt(tempSt.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        bfs(graph, visitBfs, new Thing(from,0), to);
        // dfs(graph, visitDfs, new Thing(from, 0), to);
        //dfsRecur(graph, visitDfs, new Thing(from, 0), to);
    }

    private static void bfs(List<List<Integer>> graph, boolean[] visitBfs, Thing thing, int target) {

        Queue<Thing> q = new LinkedList<>();
        q.add(thing);
        boolean flag = true; // 목표값을 찾지 못했을 경우 처리를 위한 플래그 변수

        // x --> y 으로 순회
        while (!q.isEmpty()) {

            Thing temp = q.poll();
            int cur = temp.cur;
            int chon = temp.chon;

            if (cur == target) { // 큐에서 뽑은 정점이 목표 정점이라면, 큐를 비우고 출력후 종료
                q.clear();
                System.out.println(chon);
                flag = false;  // 목표 촌수를 찾았다면, 거짓으로 바꾸기
                break;
            }

            if (!visitBfs[cur]) {
                visitBfs[cur] = true; // 방문처리에서 실수를 함. ㅜㅜ
                Iterator<Integer> it = graph.get(cur).iterator();
                while (it.hasNext()) {
                    q.add(new Thing(it.next(), chon + 1));
                }
            }
        }

        if (flag) {
            System.out.println(-1);
        }
    }

    // 재귀 + 반복으로 풀어보기
    private static void dfs(List<List<Integer>> graph, boolean[] visitDfs, Thing thing, int target) {

        Stack<Thing> stack = new Stack<>();
        stack.push(thing);
        boolean flag = true;

        while (!stack.isEmpty()) {

            Thing temp = stack.pop();
            int tempCur = temp.cur;
            int count = temp.chon;

            if (tempCur == target) {
                stack.clear();
                System.out.println(count);
                flag = false;
                break;
            }

            if (!visitDfs[tempCur]) {

                visitDfs[tempCur] = true;
                Iterator<Integer> it = graph.get(tempCur).iterator();

                while (it.hasNext()) {
                    stack.push(new Thing(it.next(), count + 1));
                }
            }
        }

        if (flag) {
            System.out.println(-1);
        }
    }

    // 재귀로는 촌수계산이 불가능하다 판단.  --> 가능함.
    private static void dfsRecur(List<List<Integer>> graph, boolean[] visitDfs, Thing thing, int target) {

    }
}
