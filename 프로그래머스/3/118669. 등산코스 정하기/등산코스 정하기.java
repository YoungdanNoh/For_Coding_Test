import java.util.*;

class Node{
    int next;
    int cost;
    
    public Node(int next, int cost){
        this.next = next;
        this.cost = cost;
    }
}

class Solution {
    
    static ArrayList<Node>[] graph;
        
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // 등산코스에서 출입구는 처음과 끝에 한 번씩, 산봉우리는 한 번만 포함
        // 산봉우리 중 한 곳만 방문한 뒤 다시 원래의 출입구로 돌아옴
        
        graph = new ArrayList[n+1];
        for(int i=0; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0; i<paths.length; i++){
            int a = paths[i][0];
            int b = paths[i][1];
            int c = paths[i][2];
            
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }
        
        boolean[] isGate = new boolean[n+1];
        boolean[] isSummit = new boolean[n+1];
        for(int g: gates) isGate[g] = true;
        for(int s: summits) isSummit[s] = true;
        
        int[] dist = new int[n+1];
        boolean[] visited = new boolean[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // 노드 번호, intensity
        
        // 모든 gate에서부터 시작
        for(int g: gates){
            dist[g] = 0;
            pq.offer(new int[]{g, 0});
        }
        
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            
            if(visited[cur[0]]) continue;
            visited[cur[0]] = true;
            
            if(isSummit[cur[0]]) continue; // 산봉우리까지만 이동함
            
            for(Node next: graph[cur[0]]){
                
                // 게이트로 이동하는 경우는 무시
                if(isGate[next.next]) continue;
                
                // 다음 노드까지 가는 전체 경로의 intensity를 더 큰 값으로 갱신
                int newIntensity = Math.max(cur[1], next.cost);
                if(dist[next.next] > newIntensity){
                    // 현재 기록된 next 노드까지의 intensity보다 더 작은 값으로 경로를 만들 수 있다면
                    dist[next.next] = newIntensity;
                    pq.offer(new int[]{next.next, dist[next.next]});
                }
            }
        }
        
        Arrays.sort(summits);
        int bestSummit = -1;
        int bestIntensity = Integer.MAX_VALUE;
        for (int s : summits) {
            if (dist[s] < bestIntensity) {
                bestIntensity = dist[s];
                bestSummit = s;
            }
        }
        
        
        return new int[]{bestSummit, bestIntensity};
    }
}