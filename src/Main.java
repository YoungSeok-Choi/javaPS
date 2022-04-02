import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준_나이트의 이동
public class Main {

    public static boolean[][] chessMap;
    public static final int[] xArr = {-1, -2, -2, -1, 1, 2, 2, 1};
    public static final int[] yArr = {-2, -1, 1, 2, -2, -1, 1, 2};
    public static int ex, ey;

    public static class Node {
        int x;
        int y;
        int cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int tCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < tCase; i++) {

            int I = Integer.parseInt(br.readLine());
            chessMap = new boolean[I][I];

            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            ex = Integer.parseInt(st.nextToken());
            ey = Integer.parseInt(st.nextToken());

            // 출발좌표, 목적좌표 같다면 종료
            if (sx == ex && ex == ey) System.out.println(0);
            else solve(new Node(sx, sy, 0));
            //dfs(sx, sy);
        }
    }

    // bfs
    private static void solve(Node node) {

        Queue<Node> q = new LinkedList<>();
        q.add(node);
        chessMap[node.x][node.y] = true;

        while (!q.isEmpty()) {

            Node temp = q.poll();

            int x = temp.x;
            int y = temp.y;
            int cnt = temp.cnt;

            if (x == ex && y == ey) {
                System.out.println(cnt);
                return;
            }

            for (int i = 0; i < 8; i++) {

                int nx = x + xArr[i];
                int ny = y + yArr[i];

                if (0 <= nx && nx < chessMap.length && 0 <= ny && ny < chessMap.length) {
                    if (chessMap[nx][ny] == false) {
                        chessMap[nx][ny] = true;
                        q.add(new Node(nx, ny, cnt + 1));
                    }
                }
            }
        }
    }

    private static void dfs(int x, int y) {

        if (x < 0 || x >= chessMap.length ||
            y < 0 || y >= chessMap.length || chessMap[x][y] == true) return;

    }

}

