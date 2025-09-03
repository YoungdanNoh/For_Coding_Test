import java.util.*;

class Robot{
    int x1, x2;
    int y1, y2;
    int time;
    
    public Robot(int x1, int y1, int x2, int y2, int time){
        
        if(x1 > x2 || (x1 == x2 && y1 > y2)){
            // x1, y1에 항상 더 작은 값이 오도록 만들어주기
            int tx = x1, ty = y1;
            x1 = x2;
            y1 = y2;
            x2 = tx;
            y2 = ty;
        }
        
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.time = time;
    }
}

class Solution {
    static int N;
    static int[][] Board;
    static boolean[][][][] visited;
    static Queue<Robot> q;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] board) {
        // 시계 방향 or 반시계 방향 회전 가능
        // 가로 or 세로 모양에서 회전
        int ans = 0;
        
        N = board.length - 1;
        Board = board;
        
        // x1, y1, x2, y2 & x2, y2, x1, y1
        visited = new boolean[N+1][N+1][N+1][N+1];
        q = new LinkedList<>();
        
        visited[0][0][0][1] = true;
        q.offer(new Robot(0, 0, 0, 1, 0));
        
        while(!q.isEmpty()){
            Robot cur = q.poll();
            
            if((cur.x1 == N && cur.y1 == N) ||
               (cur.x2 == N && cur.y2 == N)){
                // (N, N) 지점에 도착
                ans = cur.time;
                break;
            }
            
            // 상하좌우 이동
            for (int k=0; k<4; k++){
                int tx1 = cur.x1 + dx[k];
                int ty1 = cur.y1 + dy[k];
                int tx2 = cur.x2 + dx[k];
                int ty2 = cur.y2 + dy[k];
                
                putQ(tx1, ty1, tx2, ty2, cur.time + 1);
            }
            
            if(cur.x1 == cur.x2){
                // 가로방향으로 놓임
                
                // 위로 회전하거나
                if((cur.x1 - 1) >= 0 &&
                   board[cur.x1 - 1][cur.y1] == 0 &&
                   board[cur.x1 - 1][cur.y2] == 0){
                    
                    putQ(cur.x1-1, cur.y1+1, cur.x2, cur.y2, cur.time+1);
                    putQ(cur.x1, cur.y1, cur.x2-1, cur.y2-1, cur.time+1);
                }
                
                // 아래로 회전하거나
                if((cur.x1 + 1) <= N &&
                   board[cur.x1 + 1][cur.y1] == 0 &&
                   board[cur.x1 + 1][cur.y2] == 0){
                    
                    putQ(cur.x1+1, cur.y1+1, cur.x2, cur.y2, cur.time+1);
                    putQ(cur.x1, cur.y1, cur.x2+1, cur.y2-1, cur.time+1);
                }
                
            }else{
                // 세로방향으로 놓임
                
                // 왼쪽 방향으로 회전하거나
                if((cur.y1 - 1) >= 0 &&
                   board[cur.x1][cur.y1-1] == 0 &&
                   board[cur.x2][cur.y1-1] == 0){
                    
                    putQ(cur.x1+1, cur.y1-1, cur.x2, cur.y2, cur.time+1);
                    putQ(cur.x1, cur.y1, cur.x2-1, cur.y2-1, cur.time+1);
                }
                
                // 오른쪽 방향으로 회전하거나
                if((cur.y1 + 1) <= N &&
                   board[cur.x1][cur.y1+1] == 0 &&
                   board[cur.x2][cur.y1+1] == 0){
                    
                    putQ(cur.x1+1, cur.y1+1, cur.x2, cur.y2, cur.time+1);
                    putQ(cur.x1, cur.y1, cur.x2-1, cur.y2+1, cur.time+1);
                }
            }
            
        }
        
        return ans;
    }
    
    public void putQ(int x1, int y1, int x2, int y2, int time){
        
        if(x1 > x2 || (x1 == x2 && y1 > y2)){
            // x1, y1에 항상 더 작은 값이 오도록 만들어주기
            int tx = x1, ty = y1;
            x1 = x2;
            y1 = y2;
            x2 = tx;
            y2 = ty;
        }
        
        if (x1<0 || x1>N || y1<0 || y1>N) return;
        if (x2<0 || x2>N || y2<0 || y2>N) return;
        if (Board[x1][y1] != 0 || Board[x2][y2] != 0) return;
        
        if (!visited[x1][y1][x2][y2]) {
            visited[x1][y1][x2][y2] = true;
            q.offer(new Robot(x1, y1, x2, y2, time));
        }
    }
    
}