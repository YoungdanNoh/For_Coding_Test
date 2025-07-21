import java.util.*;

class Solution {
    
    class Node{
        int x;
        int y;
        int cnt;
        
        public Node(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] maps) {
        
        int N = maps.length;
        int M = maps[0].length;
        
        boolean[][] visited = new boolean[N][M];
        Deque<Node> q = new ArrayDeque<>();
        int x = 0;
        int y = 0;
        
        visited[x][y] = true;
        q.offer(new Node(x, y, 1));
        
        while(!q.isEmpty()){
            Node cur = q.poll();
            
            if((cur.x == N-1) && (cur.y == M-1)){
                // 도착
                return cur.cnt;
            }
            
            for(int i=0; i<4; i++){
                int tx = cur.x + dx[i];
                int ty = cur.y + dy[i];
                
                if(tx<0 || tx>=N || ty<0 || ty>=M) continue;
                if(visited[tx][ty]) continue;
                if(maps[tx][ty] == 0) continue;
                
                visited[tx][ty] = true;
                q.offer(new Node(tx, ty, cur.cnt+1));
                
            }
        }
        
        return -1;
        
    }
}