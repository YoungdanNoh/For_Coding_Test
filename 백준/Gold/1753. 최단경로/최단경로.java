import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V;
    static int E;
    static int K;
    static ArrayList<Node>[] graph;

    static class Node implements Comparable<Node> {
        int node; // 내 노드 번호
        int weight; // 가중치

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V+1];
        for(int i = 0; i <= V; i++){
            graph[i] = new ArrayList<Node>();
        }

        for(int i=0; i<E; i++){
            st = new StringTokenizer(bf.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, w));
        }

        int[] dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[V+1];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(K, 0));
        dist[K] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(visited[cur.node]){
                continue;
            }

            visited[cur.node] = true;

            for(Node n: graph[cur.node]){
                // 현재 노드와 연결된 노드 탐색
                if(dist[n.node] > dist[cur.node] + n.weight){
                    dist[n.node] = dist[cur.node] + n.weight;
                    pq.offer(new Node(n.node, dist[n.node]));
                }
            }
        }

        for(int i = 1; i <= V; i++){
            if(dist[i] == Integer.MAX_VALUE){
                System.out.println("INF");

            } else{
                System.out.println(dist[i]);
            }
        }
    }
}