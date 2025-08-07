class Solution {
    public int solution(int[][] board, int[][] skill) {
        
        int[][] acc = new int[board.length+1][board[0].length+1];
        
        for(int i=0; i<skill.length; i++){
            if(skill[i][0] == 1){
                // 적의 공격
                acc[skill[i][1]][skill[i][2]] -= skill[i][5];
                acc[skill[i][1]][skill[i][4]+1] += skill[i][5];
                acc[skill[i][3]+1][skill[i][2]] += skill[i][5];
                acc[skill[i][3]+1][skill[i][4]+1] -= skill[i][5];
                
            }else{
                // 아군의 회복
                acc[skill[i][1]][skill[i][2]] += skill[i][5];
                acc[skill[i][1]][skill[i][4]+1] -= skill[i][5];
                acc[skill[i][3]+1][skill[i][2]] -= skill[i][5];
                acc[skill[i][3]+1][skill[i][4]+1] += skill[i][5];
                
            }
        }
        
        for(int i=0; i<acc.length; i++){
            for(int j=1; j<acc[i].length; j++){
                acc[i][j] += acc[i][j-1]; // 행 누적합
            }
        }
        
        for(int j=0; j<acc[0].length; j++){
            for(int i=1; i<acc.length; i++){
                acc[i][j] += acc[i-1][j]; // 열 누적합
            }
        }
        
        int ans = 0;
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                if((board[i][j] + acc[i][j]) > 0){
                    ans++;
                }
            }
        }
        
        return ans;
    }
}