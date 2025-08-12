class Solution {
    static int MOD = 20170805;
    
    public int solution(int m, int n, int[][] cityMap) {
        int[][] right = new int[m][n];
        int[][] down = new int[m][n];
        right[0][0] = 1;
        down[0][0] = 0;
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                
                if (i == 0 && j == 0) continue;
                
                if(cityMap[i][j] == 1){
                    // 통행 금지
                    right[i][j] = 0;
                    down[i][j] = 0;
                
                }else{
                    // 0또는 2인 경우(자유통행 또는 터널)
                    
                    //왼쪽에서 들어오는 값
                    if (j > 0) { 
                        right[i][j] = (cityMap[i][j-1] == 2)
                            ? (right[i][j-1] % MOD) // 직진만 가능
                            : ((right[i][j-1] + down[i][j-1]) % MOD); // 자유 통행이면 합
                    }
                    
                    // 위쪽에서 들어오는 값
                    if (i > 0) {
                        down[i][j] = (cityMap[i-1][j] == 2)
                            ? (down[i-1][j] % MOD) // 직진만 가능
                            : ((right[i-1][j] + down[i-1][j]) % MOD);
                    }
                    
                }
            }
        }
        
        return (right[m-1][n-1] + down[m-1][n-1]) % MOD;
    }
}