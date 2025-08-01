import java.util.*;

class Solution {
    
    static int[][] b;
    static int N;
    static int M;
    static int ans = Integer.MAX_VALUE;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0 , -1, 1};
    static int[][][] minCost;
    
    // direc은 현재 차가 진행중인 방향. 상0, 하1, 좌2, 우3
    public void dfs(int x, int y, int cost, int direc, boolean[][] visited){
        
        if(cost >= ans) return;
        
        if(x == (N-1) && y == (M-1)){
            ans = Math.min(ans, cost);
            return;
        }
        
        for(int i=0; i<4; i++){
            int tx = x + dx[i];
            int ty = y + dy[i];
            
            if(tx<0 || tx>=N || ty<0 || ty>=M) continue;
            if(visited[tx][ty]) continue;
            if(b[tx][ty] == 1) continue; // 벽이라면..
            
            int newCost = (direc != i) ? (cost + 600) : (cost + 100);
            
            if(minCost[tx][ty][i] > newCost){
                if(direc != i){
                    // 현재 진행 방향과 다르게 꺽는 것이라면
                    visited[tx][ty] = true;
                    minCost[tx][ty][i] = cost+600;
                    
                    dfs(tx, ty, cost+600, i, visited);
                    visited[tx][ty] = false;

                }else{
                    visited[tx][ty] = true;
                    minCost[tx][ty][i] = cost+100;
                    
                    dfs(tx, ty, cost+100, i, visited);
                    visited[tx][ty] = false;

                }
                
            }

        }
        
    }
    
    public int solution(int[][] board) {
        
        b = board;
        N = board.length;
        M = board[0].length;
        minCost = new int[N][M][4];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                for(int k=0; k<4; k++){
                    minCost[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        
        if(b[0][1] == 0){
            // 초기 오른쪽으로 가는 것으로 시작
            
            boolean[][] visited = new boolean[N][M];
            visited[0][0] = true;
            visited[0][1] = true;
            
            minCost[0][1][3] = 100;
            dfs(0, 1, 100, 3, visited);
        }
        
        if(b[1][0] == 0){
            // 초기 아래쪽으로 가는 것으로 시작
            
            boolean[][] visited = new boolean[N][M];
            visited[0][0] = true;
            visited[1][0] = true;
            
            minCost[1][0][1] = 100;
            dfs(1, 0, 100, 1, visited);
        }
        
        return ans;
    }
}