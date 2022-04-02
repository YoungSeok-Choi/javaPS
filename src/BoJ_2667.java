import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 단지 수 붙이기 문제
public class BoJ_2667 {

    private static int numOfHouse = 0;
    private static int[] xArr = {-1, 0, 1, 0};
    private static int[] yArr = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Integer> results = new ArrayList<>();
        int[][] danji = new int[N][N];
        int danjinum = 0;

        for (int i = 0; i < N; i++) {
            String[] row = br.readLine().split("");
            for (int j = 0; j < row.length; j++) {
                danji[i][j] = Integer.parseInt(row[j]);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (danji[i][j] == 1) {
                    numOfHouse = 0;
                    danjinum++;
                    dfs(danji, i, j);
                    results.add(numOfHouse);
                }
            }
        }

        Collections.sort(results);
        System.out.println(danjinum);

        for (Integer result : results) {
            System.out.println(result);
        }
    }

    private static void dfs(int[][] danji, int x, int y) {

        if (x < 0 || x >= danji.length ||
                y < 0 || y >= danji.length || danji[x][y] == 0) return;

        numOfHouse++;
        danji[x][y] = 0; // 방문체크

        for (int i = 0; i < 4; i++) {
            int tempX = x + xArr[i];
            int tempY = y + yArr[i];
            dfs(danji, tempX, tempY);
        }
    }
}
