import javax.swing.event.MenuDragMouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] parents;
    static int N;
    static int E;
    static int v1, v2;
    static Node[] node;

    static class Node{
        int end, weight;
        Node next;
        public Node(int end, int weight, Node next){
            this.end = end;
            this.weight = weight;
            this.next = next;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

       node = new Node[N+1];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(bf.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            node[start] = new Node(end, weight, node[start]);
            node[end] = new Node(start, weight, node[end]);
        }
        st = new StringTokenizer(bf.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        int INF = Integer.MAX_VALUE;
        //1 -> v1 -> v2 -> N
        int dis1 = dijk(1, v1) + dijk(v1, v2) + dijk(v2, N);
        if(dijk(1, v1)==INF || dijk(v1, v2)==INF || dijk(v2, N)==INF){
            dis1 = INF;
        }

        //1 -> v2 -> v1 -> N
        int dis2 = dijk(1, v2) + dijk(v2, v1) + dijk(v1, N);
        if(dijk(1, v2)==INF || dijk(v2, v1)==INF || dijk(v1, N)==INF){
            dis2 = INF;
        }

        int ans = Math.min(dis1, dis2);
        if(ans == INF) System.out.println(-1);
        else System.out.println(ans);
    }

    private static int dijk(int start, int end) {
        final int INF = Integer.MAX_VALUE;
        boolean[] visited = new boolean[N+1];
        int[] min = new int[N+1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        for (int i = 1; i < N+1; i++) {
            min[i] = INF;
        }

        min[start] = 0;
        pq.offer(new int[]{start, min[start]});

        while (!pq.isEmpty()){
            int[] stopOver = pq.poll();

            if(visited[stopOver[0]]) continue;
            visited[stopOver[0]] = true;

            if(stopOver[0] == end) return stopOver[1]; //원하는 도착지점에 도착했다면 그때의 시간을 return

            for(Node temp = node[stopOver[0]]; temp != null; temp = temp.next){
                if(min[temp.end] > min[stopOver[0]] + temp.weight){
                    min[temp.end] = min[stopOver[0]] + temp.weight;
                    pq.offer(new int[]{temp.end, min[temp.end]});
                }
            }
        }
        return min[end];
    }
}