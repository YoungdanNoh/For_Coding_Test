import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static ArrayList<Node>[] graph;

    static class Node implements Comparable<Node>{
        int node;
        int cost;

        public Node(int node, int cost){
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "node=" + node +
                    ", cost=" + cost +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph[A].add(new Node(B, C));
            graph[B].add(new Node(A, C));
        }

//        for (int i = 0; i < N+1; i++) {
//            System.out.println(graph[i].toString());
//        }

        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[N+1];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[1] = 0;
        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()){
            int cur = pq.poll().node;

            if(visited[cur]) continue;
            visited[cur] = true;

            for(Node next: graph[cur]){
                //System.out.println(dist[cur] + next.cost);
                if(dist[next.node] > dist[cur] + next.cost){
                    //System.out.println("aaa");
                    dist[next.node] = dist[cur] + next.cost;
                    pq.offer(new Node(next.node, dist[next.node]));
                }
            }
        }

        System.out.println(dist[N]);
    }
}