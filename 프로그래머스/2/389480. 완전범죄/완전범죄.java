import java.util.*;

class Solution {
  
    public int solution(int[][] info, int n, int m) {
        
        // dp[a][b] == true : 모든 물건을 훔친 뒤
        // A가 a 흔적, B가 b 흔적을 남겼을 때 경찰에게 붙잡히지 않는다면 true
        boolean[][] dp = new boolean[n][m];
        dp[0][0] = true; // A의 흔적 0, B의 흔적 0 일땐 true
        
        for(int[] item: info){
            int af = item[0]; // A가 현재 물건을 훔쳤을 때 남기는 흔적
            int bf = item[1];
            boolean[][] tmp = new boolean[n][m]; // 원본 dp값을 사용하기 위해 새로 생성
            
            for(int a=0; a<n; a++){
                for(int b=0; b<m; b++){
                    if(!dp[a][b]) continue; // 현재 물건을 훔치기도 전에 붙잡힘
                    if((a+af) < n) tmp[a+af][b] = true; // A가 현재 물건을 훔침
                    if((b+bf) < m) tmp[a][b+bf] = true; // B가 현재 물건을 훔침
                }
            }
            
            dp = tmp;
        }
        
        for(int a=0; a<n; a++){
            for(int b=0; b<m; b++){
                if(dp[a][b]){
                    // 경찰에게 붙잡히지 않고 모두 훔칠 수 있는 경우라면
                    // 그때의 값인 a를 바로 return (a는 그때가 가장 최소 값임)
                    return a;
                }
            }
        }
        
        
        return -1;
    }
}