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
    
    public int bfs(int x, int y, int sx, int sy){
        boolean[][] visited = new boolean[4][3];
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(new Node(sx, sy, 0));
        visited[sx][sy] = true;
        
        while(!q.isEmpty()){
            Node cur = q.poll();
            
            if(cur.x == x && cur.y == y){
                return cur.cnt;
            }
            
            for(int i=0; i<4; i++){
                int tx = cur.x + dx[i];
                int ty = cur.y + dy[i];
                
                if(tx<0 || tx>=4 || ty<0 || ty>=3){
                    continue;
                }
                
                if(visited[tx][ty]){
                    continue;
                }
                
                visited[tx][ty] = true;
                q.offer(new Node(tx, ty, cur.cnt+1));
            }
        }
        
        return -1;
    }
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public String solution(int[] numbers, String hand) {
        int lx = 3; int ly = 0;
        int rx = 3; int ry = 2;
        
        String result = "";
        for(int i=0; i<numbers.length; i++){
            if(numbers[i] == 1){
                result += "L";
                lx = 0; ly = 0;
            }
            if(numbers[i] == 4){
                result += "L";
                lx = 1; ly = 0;
            }
            if(numbers[i] == 7){
                result += "L";
                lx = 2; ly = 0;
            }
            
            if(numbers[i] == 3){
                result += "R";
                rx = 0; ry = 2;
            }
            if(numbers[i] == 6){
                result += "R";
                rx = 1; ry = 2;
            }
            if(numbers[i] == 9){
                result += "R";
                rx = 2; ry = 2;
            }
            
            
            int lcnt = -1;
            int rcnt = -1;
            int tmpx = 0; int tmpy = 0;
            if(numbers[i] == 2){
                tmpx = 0; tmpy = 1;
                lcnt = bfs(0, 1, lx, ly);
                rcnt = bfs(0, 1, rx, ry);
                    
            }else if(numbers[i] == 5){
                tmpx = 1; tmpy = 1;
                lcnt = bfs(1, 1, lx, ly);
                rcnt = bfs(1, 1, rx, ry);
                    
            }else if(numbers[i] == 8){
                tmpx = 2; tmpy = 1;
                lcnt = bfs(2, 1, lx, ly);
                rcnt = bfs(2, 1, rx, ry);
                
            }else if(numbers[i] == 0){
                tmpx = 3; tmpy = 1;
                lcnt = bfs(3, 1, lx, ly);
                rcnt = bfs(3, 1, rx, ry);
                
            }
            
            if(lcnt>=0 && rcnt>=0){
                if(lcnt > rcnt){
                    result += "R";
                    rx = tmpx; ry = tmpy;
                    
                }else if(lcnt < rcnt){
                    result += "L";
                    lx = tmpx; ly = tmpy;
                    
                }
                
                if(lcnt == rcnt){
                    if(hand.equals("right")){
                        result += "R";
                        rx = tmpx; ry = tmpy;
                        
                    }else{
                        result += "L";
                        lx = tmpx; ly = tmpy;
                        
                    }
                }
            }
        }
        
        
        return result;
    }
}