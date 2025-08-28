import java.util.*;

class Solution
{
    public int solution(int[][] board)
    {
        int ans = 0;
        
        int N = board.length;
        int M = board[0].length;
        
        for(int i=0; i<N; i++) ans = Math.max(ans, board[i][0]);
        for(int j=0; j<M; j++) ans = Math.max(ans, board[0][j]);
        
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (board[i][j] == 1) {
                    board[i][j] = Math.min(
                        Math.min(board[i-1][j], board[i][j-1]),
                        board[i-1][j-1]
                    ) + 1;
                    
                    ans = Math.max(ans, board[i][j] * board[i][j]);
                }
            }
        }

        return ans;
    }
}