import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


// 반성하고 또 반성해라...

// 음수일 경우 감염되지 않는다.
// 맵, 방문여부 배열, i번째 사이클 넘기기
// 값 여부에 따라 동작 (-1, 1, 2, 0)
// i번째 사이클에 따라 다른 동작.
public class BoJ_24513X {

    static class Info {
        int virus;
        int cur;
        int x;
        int y;
        public Info(int virus, int cur, int x, int y) {
            this.virus = virus;
            this.cur = cur;
            this.x = x;
            this.y = y;
        }
    }

    private static int[] xArr = {-1, 0, 1, 0};
    private static int[] yArr = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int virusOne = 0;
        int virusTwo = 0;
        int virusThree = 0;

        int[][] map = new int[N][M];
        int[][] cur = new int[N][M];

        for (int i = 0; i < N; i++) { // 지도 정보 초기화 및 초기 감염좌표 획득
            StringTokenizer tempSt = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int temp = Integer.parseInt(tempSt.nextToken());
                map[i][j] = temp;
                cur[i][j] = 0;
            }
        }

        int tempcnt = 0;
        while (tempcnt != 1000) { // map에 감염되지 않은 마을이 없을때 까지.checkZeroValue(map) != 0
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if ((map[i][j] == 1 && cur[i][j] == tempcnt) || (map[i][j] == 2 && cur[i][j] == tempcnt)) { //|| map[i][j].virus == 2
                        dfs(map, cur, tempcnt, i, j);
                    }
                }
            }
            tempcnt++;
        }

        // 바이러스별로 감염된 마을 개수 세기.
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 1) {
                    virusOne++;
                } else if (map[i][j] == 2) {
                    virusTwo++;
                } else if (map[i][j] == 3) {
                    virusThree++;
                }
            }
        }
        System.out.printf("%d %d %d", virusOne, virusTwo, virusThree);
    }

    public static void dfs(int[][] map, int[][] cur, int tempCur, int i, int j) {

        // Stack<Info> stack = new Stack<>();
        // stack.push(info);

        // while (!stack.isEmpty()) {

        // Info temp = info; // stack.pop();

        int x = i;
        int y = j;
        int tempvirus = map[i][j];

        for (int k = 0; k < 4; k++) {

            int tempX = x + xArr[k];
            int tempY = y + yArr[k];

            if (tempX < 0 || tempX >= map.length ||
                tempY < 0 || tempY >= map[0].length ||
                map[tempX][tempY] == 3 || map[tempX][tempY] == -1) {
                continue; // 변이바이러스거나, 치료제가 있거나, 마을 밖으로 나가는경우
            }

            if (map[tempX][tempY] == 0) { // 그래프 좌표상의 감염 사이클과, 반복문에서의 감염 사이클이 동일하면서 청정지역이면 감염 tempCur == nth &&
                map[tempX][tempY] = tempvirus;
                cur[tempX][tempY] = tempCur + 1; //다음 반복문에 감염이 퍼질 수 있도록
            } else if (map[tempX][tempY] != 0 && map[tempX][tempY] != tempvirus) { // 넘어온 바이러스의 종류가 서로 다르면 3번변이로 발전.
                map[tempX][tempY] = 3;
            }
        }
    }
}
