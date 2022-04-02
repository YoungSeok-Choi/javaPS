import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


// 미로탐색 문제
// 라운드 별 경로를 노드객체에 저장해서 풀었던 문제, 반복 DfS에도 사용할 수 있을듯
public class BoJ_2178 {

    static class Node {
        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
        int x;
        int y;
        int count;
    }

    private static final int[] xArr = {-1, 0, 1, 0};
    private static final int[] yArr = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        List<Integer> results = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            // 스트링빌더는 공백없는 문자열은 못쪼개?는듯
            String[] temp = br.readLine().split("");

            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(temp[j]);
        }

        bfs(results, map, new Node(0, 0, 1));
        Collections.sort(results);
        System.out.println(results.get(0));
    }

    private static void bfs(List<Integer> results, int[][] map, Node node) {

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {

            Node temp = queue.poll();
            if ((temp.x + 1) == map.length && (temp.y + 1) == map[0].length) {
                results.add(temp.count);
            }

            int x = temp.x;
            int y = temp.y;
            int cnt = temp.count;

            for (int i = 0; i < 4; i++) {
                int tempX = x + xArr[i];
                int tempY = y + yArr[i];

                if (tempX < 0 || tempX >= map.length ||
                    tempY < 0 || tempY >= map[0].length || map[tempX][tempY] == 0) continue;

                // 방문마킹
                map[tempX][tempY] = 0;
                // ++cnt 로 하면 특정 좌표에서 분기가 이루어질 때 변수가 바뀌어버림..  따라서 배개변수로 카피해서 념겨야 함.
                queue.add(new Node(tempX, tempY, cnt + 1));
            }
        }
    }
}
