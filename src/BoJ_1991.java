import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 문자열 비교 정확히 몰라 조금 헤맸던 문제.. IDE없었으면 캐치 못했을수도
// 문자열은 동일성이 아닌 동등성으로 비교해야함 (not ==, equals()...ok!)
// 나중에 재귀를 반복문으로 바꾸는 작업해도 괜찮을듯
// && cur.lChild == null && cur.rChild == null 이거 없어도 잘 동작하더라.. Tlqkf
public class BoJ_1991 {

    static class Node {
        String num;
        Node lChild;
        Node rChild;
        public Node(String num) {
            this.num = num;
        }
    }

    private static Node root = null;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());

        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String id = st.nextToken();
            String lC = st.nextToken();
            String rC = st.nextToken();
            create(id, lC, rC);
        }
        preorder(root);
        System.out.println();
        inorder(root);
        System.out.println();
        postorder(root);
    }

    public static void create(String number, String lvalue, String rvalue) {
        if (root == null) {
            root = new Node(number);
            if (!lvalue.equals(".")) { // 자식노드의 정보가 있는 경우에만 삽입.
                root.lChild = new Node(lvalue);
            }
            if (!rvalue.equals(".")) {
                root.rChild = new Node(rvalue);
            }
        } else {
            searchNode(root, number, lvalue, rvalue);
        }
    }

    // 문자열은 == 이 아니라  equals()로 비교하여야한다!!!!!!!!!!!!!!!!!!
    private static void searchNode(Node cur, String target, String lvalue, String rvalue) {
        if (cur == null) return; // 리프노드 도착시 종료
        if (cur.num.equals(target) && cur.lChild == null && cur.rChild == null) {  //  더러운 데드코드 냄새
            if (!lvalue.equals(".")) {
                cur.lChild = new Node(lvalue);
            }
            if (!rvalue.equals(".")) {
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
            System.out.print(node.num);
            if (node.rChild != null) inorder(node.rChild);
        }
    }

    private static void preorder(Node node) {
        if (node != null) {
            System.out.print(node.num);
            if (node.lChild != null) preorder(node.lChild);
            if (node.rChild != null) preorder(node.rChild);
        }
    }

    private static void postorder(Node node) {
        if (node != null) {
            if (node.lChild != null) postorder(node.lChild);
            if (node.rChild != null) postorder(node.rChild);
            System.out.print(node.num);
        }
    }
}
