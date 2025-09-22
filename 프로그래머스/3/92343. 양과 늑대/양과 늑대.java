import java.util.*;

class Solution {
    
    class Node{
        int idx;
        boolean sheep;
        
        public Node(int idx, boolean sheep){
            this.idx = idx;
            this.sheep = sheep;
        }
    }
    
    static ArrayList<Node>[] graph;
    static boolean[] visited;
    static int ans = 0;
    
    public int solution(int[] info, int[][] edges) {
        
        graph = new ArrayList[info.length];
        for(int i=0; i<info.length; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0; i<edges.length; i++){
            int parent = edges[i][0];
            int child = edges[i][1];
            
            if(info[child] == 0){
                // 양
                graph[parent].add(new Node(child, true));
            }else{
                //늑대
                graph[parent].add(new Node(child, false));
            }
        }
        
        visited = new boolean[info.length];
        visited[0] = true;
        dfs(1, 0);
        
        return ans;
    }
    
    public void dfs(int sheep, int wolf){
        if(sheep <= wolf){
            //양의 수보다 늑대의 수가 같거나 더 많아지면 바로 모든 양이 잡아먹힘
            
            return;
        }
        
        ans = Math.max(ans, sheep);
        
        for(int i=0; i<graph.length; i++){
            for(int j=0; j<graph[i].size(); j++){
                // 이미 방문한 경로라면 continue
                if(visited[graph[i].get(j).idx]) continue;
                
                // 부모 노드를 방문했다면 현재 경로 탐색 가능
                if(visited[i]){
                    if(graph[i].get(j).sheep){
                        // 양
                        visited[graph[i].get(j).idx] = true; // 방문하거나
                        dfs(sheep+1, wolf);
                        visited[graph[i].get(j).idx] = false; // 안 하거나
                        
                    }else{
                        // 늑대
                        visited[graph[i].get(j).idx] = true;
                        dfs(sheep, wolf+1);
                        visited[graph[i].get(j).idx] = false;
                        
                    }
                }
            }
            
        }
        
    }
    
}