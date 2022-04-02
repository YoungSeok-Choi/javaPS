import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 영상처리 문제
// 입력때무에 조금 고생했었다.
// 한 줄의 길이를 모두 안받고, 부분부분 받아도 되는거에 신기했다. 많이 써먹을 수 있을듯
// 그냥 그래프 탐색 돌리면 됐었는데, 이상하게 생각해서 해멨던 문제.. 다음부터는 그렇지 않도록 주의하기.
public class BoJ_21938 {

    public static int cnt = 0;
    public static final int[] xArr = {-1, 0, 1, 0};
    public static final int[] yArr = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // CNN용 맵
        int[][] rgb = new int[N][M];
        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                // 입력 한줄 길이가 3 이상인데 이게 된다고?
                int temp = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
                temp /= 3;
                rgb[i][j] = temp;
            }
        }

        // 물체 픽셀일지 결정짓는 값.
        int stickYard = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (rgb[i][j] >= stickYard) map[i][j] = 1;
                else map[i][j] = 0;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    cnt++;
                    dfs(map, i, j);
                }
            }
        }
        System.out.println(cnt);
    }

    private static void dfs(int[][] map, int x, int y) {

        if (x < 0 || x >= map.length ||
                y < 0 || y >= map[0].length || map[x][y] == 0) return;

        map[x][y] = 0;
        dfs(map, x+1, y);
        dfs(map, x-1, y);
        dfs(map, x, y+1);
        dfs(map, x, y-1);
    }
}
