import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        
        int N = 0;
        for(int[] e: edges){
            N = Math.max(N, e[0]);
            N = Math.max(N, e[1]);
        }
        
        ArrayList<Integer>[] graph = new ArrayList[N+1];
        for(int i=0; i<=N; i++){
            graph[i] = new ArrayList<>();
        }
        
        int[] indegree = new int[N+1];
        for(int[] e: edges){
            graph[e[0]].add(e[1]);
            indegree[e[1]] += 1;
        }
        
        int[] ans = new int[4];
        for(int i=1; i<=N; i++){
            // 진입 차수가 0이면서 진출 차수가 2 이상인 정점이 생성한 정점이다.
            if((indegree[i] == 0) && (graph[i].size() >= 2)){
                ans[0] = i;
            }
        }
                
        for(int n: graph[ans[0]]){
            // 각 노드에서부터 그래프 탐색 시작
            
            ArrayDeque<Integer> q = new ArrayDeque<>();
            boolean[] visited = new boolean[N+1];
            
            visited[n] = true;
            q.offer(n); // 노드 번호
            
            int node = 1;
            int edge = 0;
            
            while(!q.isEmpty()){
                int cur = q.poll();
                
                for(int nn: graph[cur]){
                    if(!visited[nn]){
                        node++;
                        edge++;
                        
                        visited[nn] = true;
                        q.offer(nn);
                        
                    }else{
                        edge++;
                        
                    }
                }
            }
            
            if(node == edge){
                ans[1]++;
                
            }else if(node == (edge+1)){
                ans[2]++;
                
            }else{
                ans[3]++;
                
            }
            
        }
        
        return ans;
    }
}