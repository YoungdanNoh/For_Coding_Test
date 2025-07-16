import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static class Node implements Comparable<Node>{
        int next;
        int cost;

        public Node(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int N, M, X;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i=0; i<=N; i++){
            graph[i] = new ArrayList<Node>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
        }

        int[] result = new int[N+1];
        for(int i=1; i<=N; i++){
            //i = i번째 학생의 마을번호. i -> X -> i로 가는 최단 경로의 cost들을 모두 구한다.
            int cost = 0;

            // 1. i->X
            int[] dist = new int[N+1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[i] = 0;
            boolean[] visited = new boolean[N+1];
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(i, 0));

            while (!pq.isEmpty()){
                Node cur = pq.poll();

                if(visited[cur.next]){
                    continue;
                }

                visited[cur.next] = true;

                for(Node n: graph[cur.next]){
                    if(dist[n.next] > dist[cur.next] + n.cost){
                        dist[n.next] = dist[cur.next] + n.cost;
                        pq.offer(new Node(n.next, dist[n.next]));
                    }
                }
            }

            cost += dist[X];

            // 2. X -> i
            dist = new int[N+1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[X] = 0;
            visited = new boolean[N+1];
            pq = new PriorityQueue<>();
            pq.offer(new Node(X, 0));

            while (!pq.isEmpty()){
                Node cur = pq.poll();

                if(visited[cur.next]){
                    continue;
                }

                visited[cur.next] = true;

                for(Node n: graph[cur.next]){
                    if(dist[n.next] > dist[cur.next] + n.cost){
                        dist[n.next] = dist[cur.next] + n.cost;
                        pq.offer(new Node(n.next, dist[n.next]));
                    }
                }
            }

            cost += dist[i];
            result[i] = cost;
        }

        int max = 0;
        for(int i=1; i<=N; i++){
            if(result[i] > max){
                max = result[i];
            }
        }

        System.out.println(max);
    }
}
