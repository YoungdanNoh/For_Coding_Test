import java.util.*;

class Solution {
    
    class Node{
        int x, y;
        
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static String[][] graph;
    static int N, M;
    
    public boolean bfs(int x, int y){
        boolean possible = false;
        
        Deque<Node> q = new ArrayDeque<>();
        q.offer(new Node(x, y));
        boolean[][] visited = new boolean[N+2][M+2];
        visited[x][y] = true;
        
        while(!q.isEmpty()){
            Node cur = q.poll();
            
            if(cur.x==0 || cur.x==N+1 || cur.y==0 || cur.y==M+1) return true;
            
            for(int i=0; i<4; i++){
                int tx = cur.x + dx[i];
                int ty = cur.y + dy[i];
                
                if(tx<0 || tx>=N+2 || ty<0 || ty>=M+2) continue;
                if(visited[tx][ty]) continue;
                
                if(graph[tx][ty].equals("-1") || graph[tx][ty].equals("0")){
                    visited[tx][ty] = true;
                    q.offer(new Node(tx, ty));
                }
            }
        }
        
        return false;
        
    }
    
    public int solution(String[] storage, String[] requests) {
        N = storage.length;
        M = storage[0].length();
        graph = new String[N+2][M+2];
        
        for(int i=0; i<N+2; i++){
            for(int j=0; j<M+2; j++){
                if(i>=1 && i<=N && j>=1 && j<=M){
                    graph[i][j] = "" + storage[i-1].charAt(j-1);
                    
                }else{
                    graph[i][j] = "0";
                }
            }
        }
        
        for(int i=0; i<requests.length; i++){
            boolean flag = false;
            if(requests[i].length() == 1){
                // "A" : 끝에 닿아있거나, 사방 중 한 곳이 비어있는 경우 출고 가능
                flag = true;
            }
            
            String container = "" + requests[i].charAt(0);
            
            ArrayList<Node> remove = new ArrayList<>();
            if(flag){
                // 가장자리에 있는 것만 출고 가능
                boolean possible = false;
                
                for(int x=0; x<N+2; x++){
                    for(int y=0; y<M+2; y++){
                        if(graph[x][y].equals(container)){
                            possible = bfs(x, y); //끝에 다다를 수 있는지 체크
                            
                            if(possible) remove.add(new Node(x, y));
                        }
                    }
                }
                
                for(int k=0; k<remove.size(); k++){
                    graph[remove.get(k).x][remove.get(k).y] = "-1";
                }
                
            } else{
                
                // 모든 컨테이너 지우기
                for(int x=0; x<N+2; x++){
                    for(int y=0; y<M+2; y++){
                        
                        if(graph[x][y].equals(container)){
                            remove.add(new Node(x, y));
                        }
                    }
                }
                
                for(int k=0; k<remove.size(); k++){
                    graph[remove.get(k).x][remove.get(k).y] = "-1";
                }
            }
            
        }
        
        int cnt = 0;
        for(int i=0; i<N+2; i++){
            for(int j=0; j<M+2; j++){
                if(graph[i][j].equals("-1") || graph[i][j].equals("0")){
                    continue;
                }
                cnt++;
            }
        }
        
        return cnt;
    }
}