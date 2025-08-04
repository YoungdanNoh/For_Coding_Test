import java.util.*;

class Solution {
    
    static ArrayList<Node>[] graph;
    
    class Node implements Comparable<Node>{
        int next;
        int cost;
        
        public Node(int next, int cost){
            this.next = next;
            this.cost = cost;
            
        }
        
        @Override
        public int compareTo(Node n){
            return this.cost - n.cost;
        }
    }
    
    public int[] dijk(int start, int n){
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        boolean[] visited = new boolean[n+1];
        
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(visited[cur.next]) continue;
            visited[cur.next] = true;
            
            for(Node nn: graph[cur.next]){
                // 현재 노드와 연결된 다른 노드
                if(!visited[nn.next] && (dist[nn.next] > nn.cost + dist[cur.next])){
                    dist[nn.next] = nn.cost + dist[cur.next];
                    pq.offer(new Node(nn.next, dist[nn.next]));
                }
            }
        }
        
        return dist;
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        graph = new ArrayList[n+1];
        
        for(int i=0; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<fares.length; i++){
            int u = fares[i][0];
            int v = fares[i][1];
            int c = fares[i][2];
            
            graph[u].add(new Node(v, c));
            graph[v].add(new Node(u, c));
        }
        
        int ans = Integer.MAX_VALUE;
        
        int[] distS = dijk(s, n); // start에서 다른 노드까지의 최소 거리 계산
        int[] distA = dijk(a, n); // a에서 다른 노드까지의 최소 거리 계산
        int[] distB = dijk(b, n); // b에서 다른 노드까지의 최소 거리 계산
        
        for(int i=1; i<=n; i++){
            // 최소 비용 찾기
            // 1. (s -> a) + (s -> b) i==s 일 때 수행됨.
            // 2. (s -> x) + (x -> a) + (x -> b)
            
            int tmp = distS[i] + distA[i] + distB[i];
            
            ans = Math.min(ans, tmp);
        }
        
        return ans;
    }
}