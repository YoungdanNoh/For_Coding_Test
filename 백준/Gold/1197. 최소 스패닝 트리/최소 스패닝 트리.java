import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge implements Comparable<Edge> {

    int end, weight;

    public Edge(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}

public class Main {

    static List<Edge>[] graph;
    static int total;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        int V = Integer.parseInt(st.nextToken()); // 정점의 개수
        int E = Integer.parseInt(st.nextToken()); // 간선의 개수

        graph = new ArrayList[V+1];

        for (int i = 0; i <= V; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            // 방향이 없으므로 두 방향으로 모두 추가해준다.
            graph[start].add(new Edge(end, weight));
            graph[end].add(new Edge(start, weight));
        }

        prim(1, V);
        System.out.println(total);
    }

    public static void prim(int start, int v) {

        boolean[] visited = new boolean[v+1];

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(v, 0)); //시작 노드 넣기

        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int next = e.end;
            int cost = e.weight;

            if (visited[e.end]) continue;

            visited[next] = true;
            total += cost;

            for (Edge edge : graph[next]) {
                if (!visited[edge.end]){
                    pq.add(new Edge(edge.end, edge.weight));
                }
            }
        }
    }
}
