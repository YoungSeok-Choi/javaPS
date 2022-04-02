import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;


// DFS재귀로 풀었으니, BFS, 반복DFS풀어보기
// 뭐 그냥 전형적인 그래프 탐색문제
// 여기서 골드수준으로는 어떻게 넘어갈 것인가? 고민하기.
// 스택과 큐의 방문처리 시점이 다름.... 경우에따라 무한루프가 발생 -> 큐의 삽입 로직을 생각해보면 이해가 간다.. 수준이 높아질수록 정확히 알아야 한다
public class BoJ_1012 {

    private static int insect = 0;

    // 상 -> 우 -> 하 -> 좌 (시계방향)
    private static final int[] xArr = {-1, 0, 1, 0};
    private static final int[] yArr = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCase; i++) { // 밭 입력받아 초기화

            StringTokenizer st = new StringTokenizer(br.readLine(), " "); // 입력 안하면 자동으로 디폴트 값.
            int w =Integer.parseInt(st.nextToken());
            int h =Integer.parseInt(st.nextToken());
            int baeChoo = Integer.parseInt(st.nextToken());

            int[][] bat = new int[w][h];

            for (int j = 0; j < baeChoo; j++) {
                StringTokenizer st_n = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st_n.nextToken());
                int y = Integer.parseInt(st_n.nextToken());
                bat[x][y] = 1;
            }

            // 초기화된 밭을 매개변수로 그래프 탐색 시작
            resolve(bat);
        }

        br.close();
        bw.close();
    }

    public static void resolve(int[][] bat) {

        // 테스트케이스 별 0으로 다시 초기화
        insect = 0;

        for (int x = 0; x < bat.length; x++) {
            for (int y = 0; y < bat[0].length; y++) {
                if (bat[x][y] == 1) {
                    insect++; // 벌레 수 증가
                    //graph(bat, x, y);
                    bfs(bat, new Node(x, y));
                    //dfs(bat, new Node(x, y));
                }
            }
        }
        System.out.println(insect);
    }

    // 재귀 DFS
    public static void graph(int[][] bat, int x, int y) {

        if (x >= bat.length || x < 0 ||
                y >= bat[0].length || y < 0 || bat[x][y] == 0) return;

        bat[x][y] = 0; // 방문 표시

        graph(bat, x + 1, y);
        graph(bat, x - 1, y);
        graph(bat, x, y + 1);
        graph(bat, x, y - 1);
    }

    // bfs
    public static void bfs(int[][] bat, Node node) {

        Queue<Node> q = new LinkedList<>();
        bat[node.x][node.y] = 0; // 첫 번째로입력된 좌표 방문처리
        q.add(node);

        while (!q.isEmpty()) {

            Node temp = q.poll();
            int x = temp.x;
            int y = temp.y;

            for (int i = 0; i < 4; i++) {

                int nx = x + xArr[i];
                int ny = y + yArr[i];

                if (nx >= 0 && nx < bat.length &&
                    ny >= 0 && ny < bat[0].length && bat[nx][ny] == 1) { // 이런식으로 해야 early stopping이 가능함.
                    bat[nx][ny] = 0; // 방문처리
                    q.add(new Node(nx, ny));
                }
            }
        }
    }

    // 반복문 dfs
    public static void dfs(int[][] bat, Node node) {

        Stack<Node> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {

            Node temp = stack.pop();
            int x = temp.x;
            int y = temp.y;
            bat[x][y] = 0;

            for (int i = 0; i < 4; i++) {

                int nx = x + xArr[i];
                int ny = y + yArr[i];

                if (nx >= 0 && nx < bat.length &&
                    ny >= 0 && ny < bat[0].length && bat[nx][ny] == 1) { // 이런식으로 해야 early stopping이 가능함.

                    stack.push(new Node(nx, ny));
                 }
            }
        }
    }


    static class Node {
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
