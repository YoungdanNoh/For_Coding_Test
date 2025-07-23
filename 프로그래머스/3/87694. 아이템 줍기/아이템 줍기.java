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
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        int[][] graph = new int[102][102];
        
        for(int i=0; i<rectangle.length; i++){
            int x1 = rectangle[i][0]*2;
            int y1 = rectangle[i][1]*2;
            int x2 = rectangle[i][2]*2;
            int y2 = rectangle[i][3]*2;
            
            for(int j=x1; j<=x2; j++){
                graph[y1][j] = 1;
                graph[y2][j] = 1;
            }
            
            for(int j=y1; j<=y2; j++){
                graph[j][x1] = 1;
                graph[j][x2] = 1;
            }
        }
        
        for(int i=0; i<rectangle.length; i++){
            int x1 = rectangle[i][0]*2;
            int y1 = rectangle[i][1]*2;
            int x2 = rectangle[i][2]*2;
            int y2 = rectangle[i][3]*2;
            
            for(int x=y1+1; x<y2; x++){
                for(int y=x1+1; y<x2; y++){
                    graph[x][y] = 0;
                }
            }
        }
        
        
        boolean[][] visited = new boolean[102][102];
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(new Node(characterY*2, characterX*2, 0));
        visited[characterY*2][characterX*2] = true;
        
        int result = 0;
        while(!q.isEmpty()){
            Node cur = q.poll();
            
            if((cur.x == itemY*2) && (cur.y == itemX*2)){
                result = cur.cnt;
                break;
            }
            
            for(int i=0; i<4; i++){
                int tx = cur.x + dx[i];
                int ty = cur.y + dy[i];
                
                if(tx<0 || tx>101 || ty<0 || ty>101){
                    continue;
                }
                
                if(visited[tx][ty]){
                    continue;
                }
                
                if(graph[tx][ty] == 0){
                    continue;
                }
                
                visited[tx][ty] = true;
                q.offer(new Node(tx, ty, cur.cnt+1));
            }
        }
        
        
        return result/2;
    }
}