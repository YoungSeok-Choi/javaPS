import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 13023_ABCDE
// 그래프의 깊이 구하기 문제
// 시간초과 or 틀렸습니다...
public class BoJ_13023_ABCDE {

    public static class Node {
        int to;
        int cnt; // 시작정점부터 방문 횟수 (친구의 친구를 확인하기 위한 변수) --> 그래프의 깊이 저장을 위한 변수

        public Node(int to, int cnt) {
            this.to = to;
            this.cnt = cnt;
        }
    }

    public static boolean[] visit;
    public static boolean flag;
    public static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int vertex = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());
        visit = new boolean[vertex];

        // 0번 정점을 사용하고, N - 1번째 정점을 사용하지 않음
        for (int i = 0; i < vertex; i++) {
            graph.add(new ArrayList<>());
        }

        // 양방향 그래프로 초기화
        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        flag = false;
        // 각 정점별로 순회 하다 친구관계 찾으면 출력 후 종료
        for (int i = 0; i < vertex; i++) {
            if (checkFriendShip(new Node(i, 0))) {
                System.out.println(1);
                flag = false;
                break;
            }
        }

//        for (int i = 0; i < vertex; i++) {
//            checkFriendDfs(1, i);
//            if (flag) {
//                System.out.println(1);
//                return;
//            }
//            // 필요없는 연산이었다.
//            // Arrays.fill(visit, false);
//        }
        System.out.println(0);
    }

    private static boolean checkFriendShip(Node node) {

        Queue<Node> q = new LinkedList<>();
        Arrays.fill(visit, false);
        q.add(node);
        visit[node.to] = true;

        while (!q.isEmpty()) {

            Node cur = q.poll();
            int now = cur.to;
            int cnt = cur.cnt;
            Iterator<Integer> it = graph.get(now).iterator();

            // A -> B -> C -> D -> E
            // 깊이 4이상 발견하면 큐 비우고 바로 종료
            if (cnt == 4) {
                q.clear();
                return true;
            }

            if (!visit[now] == false) { // 방문하지 않았다면, 해당 정점과 연결된 정점을 큐에 추가
                while (it.hasNext()) {
                    int temp = it.next();
                    visit[temp] = true; // 방문표시
                    q.add(new Node(temp, cnt + 1));
                }
            }
        }
        // 반복문이 종료될 때까지 참을 반환하지 않고 계속 돌다 해당 구문에서 종료된다면 건너건너 친구 관계가 없는것
        return false;
    }

    private static void checkFriendDfs(int cnt, int start) {

        if (cnt == 5) {
            flag = true;
            return;
        }

        visit[start] = true;
        Iterator<Integer> it = graph.get(start).iterator();

        while (it.hasNext()) {
            int temp = it.next();
            if (!visit[temp]) {
                checkFriendDfs(cnt + 1, temp);
            }
        }

        // 경로의 끝을 찍고 존재하는 분기에 대해서 다시 탐색하는거같은데..?
        // 이런 방식으로 하면 브루트포스라고 함. 그래프 탐색인데 브루트포스. 단순 탐색은 한 정점을 한번만 방문하는거
        // 끝나고 나왔을때 다시 방문안한 처리를 해주니까 반복문에서 fill안해도 되는거가?
        // dfs가 끝나면 visit배열은 모두 false값으로 다시 복구가 된다.
        visit[start] = false;
    }
}
