package classicalgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


// 백준 1753 문제
// List<List<Integer>> 보다 직관적이고 뭔가 효율적일거같음.. 암튼 이게 더 좋다!
// 가중치 까지 포함하기 위해 해당 자료구조 사용
//        Map<Integer, Map<Integer, Double>> graph = new HashMap<>();
//        List<List<Pair>> graph2 = new ArrayList<>();
// 이런 방법을 내가 떠올려서 풀어낸다고 생각하는건 상당히 오만한 생각인듯...
// 멍청함을 받아들이고 빠르게 이해해서 응용하는 유연함이 필요

public class ShortestPathWithDijkstra {
    // 우선순위 큐를 위한 객체 타입 선언
    static class Pair implements Comparable<Pair>{

        int vertex; // 도착 간선
        int weight; // 이동 가중치를 왜 더블형으로 선언할까?

        public Pair(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        // 우선순위 큐의 비교대상이 되기 위해서 객체는 비교함수를 선언해주어야하는듯
        @Override
        public int compareTo(Pair o) {
            return weight - o.weight;
        }
    }

    private static int V, E;
    static List<List<Pair>> graph = new ArrayList<>();
    static int[] distance; // 이렇게 기록하는걸 일종의 DP로 보는 시각도 있구만

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());
        distance = new int[V + 1];

        // 그래프 생성
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }
        // 그래프 초기화 (방향 그래프 )
        for (int i = 0; i < E; i++) {

            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // from
            int v = Integer.parseInt(st.nextToken()); // to
            int w = Integer.parseInt(st.nextToken()); // weight

            graph.get(u).add(new Pair(v, w));
        }

        dijkstra(start);
        for (int i = 1; i < distance.length; i++) {
            if (distance[i] != Integer.MAX_VALUE) System.out.println(distance[i]);
            else System.out.println("INF");
        }
    }

    public static void dijkstra(int start) {

        Arrays.fill(distance, Integer.MAX_VALUE);
        PriorityQueue<Pair> heap = new PriorityQueue<>();
        boolean[] isVisit = new boolean[V + 1];

        // 여기 부분이 핵심. 초기 시작지점은 모두 무한대 이고, 출발지점만 0으로 시작.
        // 여기서부터 모든 정점들의 가중치가 무한대에서 갱신되는 듯 하다
        distance[start] = 0;
        heap.add(new Pair(start, 0));

        while (!heap.isEmpty()) {
            Pair p = heap.poll();
            if (isVisit[p.vertex]) continue; // 방문한거면 패스
            isVisit[p.vertex] = true;

            for (Pair pair : graph.get(p.vertex)) {
                // 시작 정점에서 Pair 객체 프로퍼티인 목표 정점까지의 가중치 (초기 시작정점 가중치 0+초기화 된 목표정점가중치)
                // 와 무한대로 초기화 된 초기 정점 가중치와 비교하여, 작은 경우 갱신해나감.
                // 특정 정점은 간선이 있는 이전에서의 최소경로의 가중치가 업데이트 되어있는 상태
                // 특정 정점 a -> b로 갈때 이전 최소 가중치에서 다시 더해, 혹여나 다른 간선이 방문한다 할지라도 더 낮은 값으로 갱신
                // 다른 정점이 먼저 도착했더라도, 특정시점 T(i)에서는 해당 라운드에 큐에서 뽑힌 정점은 방문하지 않았더라면 갱신될 여지가 있다.
                // 간선을 한 번만 이동했을때의 코스트를 구하는게 다익스트라 알고리즘 특징
                // 반대로 벨만포드는 시작정점부터 특정시점 정점까지 매번 전부 첨부터 센다. 따라서 음수 사이클 디텍팅이 가능하다.
                if (distance[pair.vertex] > distance[p.vertex] + pair.weight) {
                    distance[pair.vertex] = distance[p.vertex] + pair.weight;
                    heap.add(new Pair(pair.vertex, distance[pair.vertex]));
                }
            }
        }
    }
}
