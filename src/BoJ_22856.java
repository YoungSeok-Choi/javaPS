import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;


// 백준 트리순회 문제
// 기본 자료구조를 자바 버젼으로 공부했던거는 좋았다.
// 기본적인 자료구조를 얼마나 정확히 다루는가에 따라 기본기가 갈릴듯
// 입력순서가 1번 노드부터가 아닐 수 있다...????!!!!!! 헐
public class BoJ_22856 {

    static class Node {
        int num;
        Node lChild;
        Node rChild;

        public Node(int num) {
            this.num = num;
        }
    }

    private static Node root = null;
    private static int count = 0;
    private static List<Integer> inorders = new ArrayList<>();
    private static int compare;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());

        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int lC = Integer.parseInt(st.nextToken());
            int rC = Integer.parseInt(st.nextToken());
            create(id, lC, rC);
        }
        inorder(root);
        compare = inorders.get(inorders.size() - 1);
        similarInorder(root);
    }

    public static void create(int number, int lvalue, int rvalue) {
        if (root == null) {
            root = new Node(number);
            if (lvalue != -1) {
                root.lChild = new Node(lvalue);
            }
            if (rvalue != -1) {
                root.rChild = new Node(rvalue);
            }
        } else {
            searchNode(root, number, lvalue, rvalue);
        }
    }

    private static void searchNode(Node cur, int target, int lvalue, int rvalue) {
        if (cur == null) return; // 리프노드 도착시 종료
        if (cur.num == target && cur.lChild == null && cur.rChild == null) {
            if (lvalue != -1) {
                cur.lChild = new Node(lvalue);
            }
            if (rvalue != -1) {
                cur.rChild = new Node(rvalue);
            }
        } else {
            searchNode(cur.lChild, target, lvalue, rvalue);
            searchNode(cur.rChild, target, lvalue, rvalue);
        }
    }

    private static void inorder(Node node) {
        if (node != null) {
            if (node.lChild != null) inorder(node.lChild);
            inorders.add(node.num);
            if (node.rChild != null) inorder(node.rChild);
        }
    }

    private static void similarInorder(Node node) {
        if (node != null) {
            if (node.lChild != null) {
                similarInorder(node.lChild);
                ++count; // 왼쪽 탐색이 끝나고 돌아올 때 카운트 증가
            }
            if (node.num == compare) { // 중위순회의 마지막 값과 같은 숫자면 즉시종료
                System.out.println(count);
                return;
            }
            ++count; // 해당 시점에서의 방문 시 카운트 증가
            if (node.rChild != null) {
                similarInorder(node.rChild);
                ++count; //  오른쪽 탐색이 끝나고 돌아올 때 카운트 증가
            }
        }
    }
}
