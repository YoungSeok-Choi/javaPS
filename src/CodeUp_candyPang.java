import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class CodeUp_candyPang {

    private static int sum = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] candies = new int[7][7];
        int area = 0;

        // 캔디팡 비열의 초기화
        for (int i = 0; i < candies.length; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < candies[0].length; j++) {
                candies[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dfs로 해결하기.
//        for (int i = 0; i < candies.length; i++) {
//            for (int j = 0; j < candies.length; j++) {
//                sum = 0;
//
//                if (candies[i][j] != 0) sum = dfs(candies, i, j, candies[i][j]);
//
//                if (sum >= 3) area++;
//            }
//        }

        // bfs로 해결하기.
        for (int i = 0; i < candies.length; i++) {
            for (int j = 0; j < candies.length; j++) {
                sum = 0;

                if (candies[i][j] != 0){
                    sum = bfs(candies, new Point(i, j), candies[i][j]);
                }

                if (sum >= 3) area++;
            }
        }
        System.out.println(area);
    }

    public static int dfs(int[][] candies,int x , int y, int value) {

        if (x >= candies.length || x < 0 ||
                y >= candies.length || y < 0 || value != candies[x][y]) return -1;

        // 방문체크
        candies[x][y] = 0;
        sum++;

        dfs(candies, x + 1, y, value);
        dfs(candies, x - 1, y, value);
        dfs(candies, x, y + 1, value);
        dfs(candies, x, y - 1, value);
        return sum; // 재귀함수에서의 값 리턴이 아직 미숙하다..
    }

    public static int bfs (int[][] candies, Point point, int value) {

        Queue<Point> queue = new LinkedList<>();
        queue.add(point);

        // 상하좌우 칸을 표현하는데 사용할 배열
        int[] xArr = {-1, 0, 1, 0};
        int[] yArr = {0, 1, 0, -1};

        while (!queue.isEmpty()) {

            Point temp = queue.poll();
            int x = temp.x;
            int y = temp.y;

            for (int i = 0; i < 4; i++) {

                int tempX = x + xArr[i];
                int tempY = y + yArr[i];

                // 동서남북 기준으로 행렬 밖이거나, 동일한 색의 캔디가 아닐경우에는 큐에 삽입하지 않음.
                if (tempX >= candies.length || tempX < 0 ||
                        tempY >= candies.length || tempY < 0 || value != candies[tempX][tempY]) continue;

                // 방문표시
                candies[tempX][tempY] = 0;
                queue.add(new Point(tempX, tempY));
                sum++;
            }
        }
        return sum;
    }

    static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
