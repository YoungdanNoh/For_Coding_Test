class Solution {
    static int N;
    static int M;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        
        N = board.length;
        M = board[0].length;
        
        // #1 a->b를 이기는 경우
        // #2 b->a를 이기는 경우
        int[][] b = new int[N][M];
        for(int i=0; i<N; i++){
            b[i] = board[i].clone();
        }
        
        int[] ans = dfs(b, aloc[0], aloc[1], bloc[0], bloc[1], true);
        
        return ans[1];
    }
    
    public int[] dfs(int[][] b, int ax, int ay, int bx, int by, boolean aTurn){
        
        // 현재 움직이려는 x, y 좌표 값
        int cx = aTurn ? ax : bx;
        int cy = aTurn ? ay : by;
        
        // 현재 칸이 이미 사라졌다면(밟을 수 없다면) 현재 차례 즉시 패배
        if (b[cx][cy] == 0) return new int[]{0, 0};
        
        boolean canWin = false; // 현재 플레이어가 이길 수 있는지 아닌지
        int bestWin = Integer.MAX_VALUE; // 승리할 때 최소 턴
        int bestLose = 0; // 패배할 때 최대 턴
        
        // 현재 칸 없앰
        b[cx][cy] = 0;
        
        for (int d=0; d<4; d++) {
            int nx = cx + dx[d];
            int ny = cy + dy[d];
            
            if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
            
            if (b[nx][ny] == 0) continue;

            int[] child; // 순서대로 승패 여부, turn 수
            if (aTurn) {
                // 다음 b를 움직여봄
                child = dfs(b, nx, ny, bx, by, false);
                
            } else {
                // 다음 a를 움직여봄
                child = dfs(b, ax, ay, nx, ny, true);
                
            }

            if (child[0] == 0) { // 상대가 지면 -> 나는 이김
                canWin = true;
                // 이길 땐 최소 turn 수를 만드는 것이 좋음
                bestWin = Math.min(bestWin, child[1] + 1);
                
            } else { // 상대가 이김 -> 나는 짐
                
                // 질 땐 최대 turn 수를 만드는 것이 좋음
                bestLose = Math.max(bestLose, child[1] + 1);
                
            }
        }
        
        // 현재 칸 복원
        b[cx][cy] = 1;

        if (canWin) return new int[]{1, bestWin};
        else return new int[]{0, bestLose};
        
    }
    
}