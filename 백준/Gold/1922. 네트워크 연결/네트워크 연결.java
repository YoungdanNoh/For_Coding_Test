import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {

    int next;
    int weight;

    public Edge(int next, int weight) {
        this.next = next;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight; //오름차순
    }
}

public class Main {

    static List<Edge>[] graph;
    static int cost = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(bf.readLine().trim());
        int M = Integer.parseInt(bf.readLine().trim());

        graph = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(bf.readLine().trim());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            //방향이 없으므로 양방향으로 넣어준다.
            graph[u].add(new Edge(v, weight));
            graph[v].add(new Edge(u, weight));
        }

        prim(1, N); // 시작 노드, 전체 노드 개수
        System.out.println(cost);
    }

    public static void prim(int u, int N) {
        boolean[] visited = new boolean[N + 1];

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(u, 0)); //시작 노드의 비용은 0

        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int next = e.next;
            int weight = e.weight;

            if(visited[next]) {
                continue; // 방문한 노드라면 pass
            }

            visited[next] = true;
            cost += weight;
            //System.out.println(weight);
            //System.out.println(cost);

            for(Edge edge: graph[next]) {
                // 다음 노드와 연결된 노드들을 탐색
                if(!visited[edge.next]) {
                    // 방문하지 않은 노드라면 pq에 삽입
                    //System.out.println(weight);
                    pq.add(new Edge(edge.next, edge.weight));
                }
            }
        }
    }
}
