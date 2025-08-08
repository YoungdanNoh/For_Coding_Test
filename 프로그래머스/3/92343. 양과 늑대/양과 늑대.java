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
    
    public void dfs(int sheep, int wolf){
        if(sheep <= wolf){
            return;
        }else{
            ans = Math.max(ans, sheep);
        }
        
        for(int i=0; i<graph.length; i++){
            for(int j=0; j<graph[i].size(); j++){
                Node child = graph[i].get(j); // 자식 노드
                
                if(visited[i] && !visited[child.idx]){
                    // 부모 방문 && 자식 방문X -> 자식 방문 가능
                    if(child.sheep){
                        // 양이라면
                        visited[child.idx] = true;
                        dfs(sheep+1, wolf);
                        visited[child.idx] = false;
                        
                    }else{
                        // 늑대라면
                        visited[child.idx] = true;
                        dfs(sheep, wolf+1);
                        visited[child.idx] = false;
                        
                    }
                }
            }
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        
        graph = new ArrayList[info.length];
        for(int i=0; i<info.length; i++){
            graph[i] = new ArrayList<>();
        }
        
        Arrays.sort(edges, (a, b) -> {
            if(a[0] == b[0]){
                // 같은 레벨이라면
                return Integer.compare(a[1], b[1]);
            }else{
                return Integer.compare(a[0], b[0]);
            }
        });
        
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
        
        // for(int i=0; i<graph.length; i++){
        //     System.out.print(i + ": ");
        //     for(int j=0; j<graph[i].size(); j++){
        //         System.out.print(graph[i].get(j).idx + " ");
        //     }
        //     System.out.println();
        // }
        
        
        return ans;
    }
}