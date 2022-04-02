import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


// 해결하지 못함.
public class CodeUp_ColorFill {

    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();
        boolean[][] arr = new boolean[10][10];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < arr.length; i++) {

            String[] temp = new String[10];
            temp = br.readLine().split("");

            for (int j = 0; j < arr.length; j++) {
                arr[i][j] = temp[i].equals("_");
            }
        }

        StringTokenizer numSt = new StringTokenizer(br.readLine(), " ");

        int y = Integer.parseInt(numSt.nextToken());
        int x = Integer.parseInt(numSt.nextToken());

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i == x && j == y) {
                    System.out.println("j = " + j);
                    System.out.println("i = " + i);
                    dfs(arr, i, j);
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == true) sb.append("_");
                else sb.append("*");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(boolean[][] gRim, int x, int y) {

        if (gRim[x][y] == false) return;

        // "* " 로 바꾸기
        gRim[x][y] = false;

        dfs(gRim, x + 1, y);
        dfs(gRim, x - 1, y);
        dfs(gRim, x, y - 1);
        dfs(gRim, x, y + 1);
    }


    private static void bfs(boolean[][] gRim, Code point) {

        Queue<Code> queue = new LinkedList<>();
        queue.add(point);

        int[] xArr = {-1, 0, 1, 0};
        int[] yArr = {0, 1, 0, -1};

        while (!queue.isEmpty()) {

            Code code = queue.poll();
            int x = code.x;
            int y = code.y;

            for (int i = 0; i < 4; i++) {

                int tempX = x + xArr[i];
                int tempY = y + yArr[i];

                if (point.x >= gRim.length || point.x < 0 ||
                        point.y >= gRim.length || point.y < 0 ||
                        gRim[tempX][tempY] != true) {
                    continue;
                }
                gRim[tempX][tempY] = true;
                queue.add(new Code(tempX, tempY));

            }
        }
    }

    static class Code {

        int x;
        int y;

        public Code(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
