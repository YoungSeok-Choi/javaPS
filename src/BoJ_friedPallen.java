import java.util.*;
import java.io.*;


public class BoJ_friedPallen {

    private static int result = 0;
    //  
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int vertex = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());

        boolean visited[] = new boolean[vertex + 1];

        ArrayList<ArrayList<Integer>> friends = new ArrayList<>();

        // 정점 생성
        for (int i = 0; i <= vertex; i++)
            friends.add(new ArrayList<Integer>());

        // 간선 생성
        for (int i = 0; i < edge; i++) {
            StringTokenizer tempSt = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(tempSt.nextToken());
            int v = Integer.parseInt(tempSt.nextToken());

            // A -> B 가 친구이면, B -> A 도 친구
            friends.get(u).add(v);

        }

        for (int i = 0; i < friends.size(); i++) {
            Collections.sort(friends.get(i), Collections.reverseOrder()); // 모든 포인터 배열 정렬
        }

        for (int i = 1; i <= vertex; i++) {
            if (visited[i] == false) {
                dfs(friends, visited, i);
            }
        }

        if (result < vertex) result++;

        System.out.println(result);
        br.close();
    }

    public static void dfs(ArrayList<ArrayList<Integer>> friends, boolean visited[], int i) {

        LinkedList<Integer> stack = new LinkedList<>();

        stack.push(i);

        while (!stack.isEmpty()) {

            int cur = stack.pop();
            Iterator<Integer> it = friends.get(cur).iterator();


            if (visited[cur] == false) {
                visited[cur] = true;

                while (it.hasNext()) {
                    result++;
                    int V = it.next();
                    stack.push(V);
                }
            }
        }
    }
}