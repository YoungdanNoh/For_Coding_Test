import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V;
    static int E;
    static ArrayList<Node>[] graph;

    static class Node implements Comparable<Node> {
        int node;
        int weight;
        
        public Node(int node, int weight){
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList[V+1];
        for(int i = 0; i <= V; i++){
            graph[i] = new ArrayList<Node>();
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        boolean[] visited = new boolean[V+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));
        
        int cost = 0;
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            
            if(visited[node.node]) continue;

            visited[node.node] = true;
            cost += node.weight;
            
            for(Node n : graph[node.node]) {
                // 나와 연결된 애들
                if(!visited[n.node]) {
                    pq.offer(new Node(n.node, n.weight));
                }
            }
        }

        System.out.println(cost);
    }
}