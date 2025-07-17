import java.util.*;

class Solution {
    
    class Node{
        int next;
        
        public Node(int next){
            this.next = next;
        }
    }
    
    static ArrayList<Node>[] graph;
    static ArrayList<Node>[] graph2;
    
    public int solution(int n, int[][] results) {
        
        graph = new ArrayList[n+1];
        graph2 = new ArrayList[n+1];
        for(int i=0; i<=n; i++){
            graph[i] = new ArrayList<Node>();
            graph2[i] = new ArrayList<Node>();
        }
        
        for(int i=0; i<results.length; i++){
            graph[results[i][0]].add(new Node(results[i][1]));
            graph2[results[i][1]].add(new Node(results[i][0]));
        }
        
        // 내가 도달할 수 있는 노드 수 + 나에게 도달할 수 있는 노드 수
        int[] reach = new int[n+1];
        for(int i=1; i<=n; i++){
            int tmp = 0;
            boolean[] visited  = new boolean[n+1];
            Queue<Node> q = new LinkedList<>();
            q.offer(new Node(i));
                
            while(!q.isEmpty()){
                Node cur = q.poll();
                    
                if(visited[cur.next]) continue;
                
                tmp++;
                visited[cur.next] = true;
                    
                for(Node nn : graph[cur.next]){
                    if(!visited[nn.next]){
                        q.offer(new Node(nn.next));
                    }
                }
            }
            
            tmp--;
            //System.out.println(i + " tmp1: " + tmp);
            reach[i] += tmp;
        }
        
        for(int i=1; i<=n; i++){
            int tmp = 0;
            boolean[] visited  = new boolean[n+1];
            Queue<Node> q = new LinkedList<>();
            q.offer(new Node(i));
                
            while(!q.isEmpty()){
                Node cur = q.poll();
                    
                if(visited[cur.next]) continue;
                
                tmp++;
                visited[cur.next] = true;
                    
                for(Node nn : graph2[cur.next]){
                    if(!visited[nn.next]){
                        q.offer(new Node(nn.next));
                    }
                }
            }
            
            tmp--;
            //System.out.println(i + " tmp2: " + tmp);
            reach[i] += tmp;
        }
        
        int result = 0;
        for(int i=0; i<=n; i++){
            if(reach[i] == (n-1)){
                result++;
            }
        }
        
        return result;
    }
}