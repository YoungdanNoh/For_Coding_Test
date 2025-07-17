import java.util.*;

class Solution {
    
    class Node{
        int next;
        
        public Node(int next){
            this.next = next;
        }
    }
    
    public int visit(int start, int cut){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(start));
        int cnt = 0;
        
        while(!q.isEmpty()){
            Node cur = q.poll();
            
            if(visited[cur.next]) continue;
            
            visited[cur.next] = true;
            cnt++;
                
            for(Node nn: graph[cur.next]){
                if(!visited[nn.next] && nn.next!=cut){
                    q.offer(new Node(nn.next));
                }
            }
        }
        
        return cnt;
    }
    
    static ArrayList<Node>[] graph;
    static boolean[] visited;
    
    public int solution(int n, int[][] wires) {
        
        graph = new ArrayList[n+1];
        for(int i=0; i<=n; i++){
            graph[i] = new ArrayList<Node>();
        }
        for(int i=0; i<wires.length; i++){
            graph[wires[i][0]].add(new Node(wires[i][1]));
            graph[wires[i][1]].add(new Node(wires[i][0]));
        }
        
        int result = Integer.MAX_VALUE;
        
        for(int i=0; i<wires.length; i++){
            // i = 자를 wires idx 번호
            
            visited = new boolean[n+1];
            int n1 = wires[i][0];
            int n2 = wires[i][1];
            
            int diff = Math.abs(visit(n1, n2) - visit(n2, n1));
            
            if(result > diff){
                result = diff;
            }
        }
        
        return result;
    }
}