import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        
        int N = problems.length;
        
        // 최종적으로 이 값 이상이 되어야 함
        int max_alp = 0;
        int max_cop = 0;
        for(int[] problem : problems) {
            max_alp = Math.max(max_alp, problem[0]);
            max_cop = Math.max(max_cop, problem[1]);
        }
        
        // 초기 알고력과 코딩력이 둘 다 목표치보다 높은 경우 -> 0을 return
        if(alp >= max_alp && cop >= max_cop) return 0;
        
        // 초기 알고력이 목표치보다 높은 경우 
        if(alp >= max_alp) alp = max_alp;
        
        // 초기 코딩력이 목표치보다 높은 경우
        if(cop >= max_cop) cop = max_cop;
        
        int[][]dp = new int[max_alp+1][max_cop+1];
        for(int i=alp; i<=max_alp; i++) {
            for(int j=cop; j<=max_cop; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        dp[alp][cop] = 0; //시작점
        for(int i=alp; i<=max_alp; i++) {
            for(int j=cop; j<=max_cop; j++) {
                
                // 공부하기
                if((i+1) <= max_alp){
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + 1);
                }
                if((j+1) <= max_cop){
                    dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j] + 1);
                }
                
                
                for(int p=0; p<problems.length; p++) {
                    // 현재 알고력과 코딩력이 문제를 해결할 수 있는 경우
                    if(i>=problems[p][0] && j>=problems[p][1]) {
                        
                        // 문제를 풀었을때 둘 다 목표치가 넘는 경우
                        if(i+problems[p][2]>max_alp && j+problems[p][3]>max_cop) {
                            dp[max_alp][max_cop] = Math.min(dp[max_alp][max_cop], dp[i][j] + problems[p][4]);                        
                        } // 알고력이 목표치를 넘는 경우
                        else if(i+problems[p][2]>max_alp) {
                            dp[max_alp][j+problems[p][3]] = Math.min(dp[max_alp][j+problems[p][3]], 
                                                                     dp[i][j] + problems[p][4]);
                        } // 코딩력이 목표치를 넘는 경우
                        else if(j+problems[p][3]>max_cop) {
                            dp[i+problems[p][2]][max_cop] = Math.min(dp[i+problems[p][2]][max_cop], 
                                                                     dp[i][j] + problems[p][4]);
                        } // 둘다 목표치를 넘지 않는 경우 (일반)
                        else {
                            dp[i+problems[p][2]][j+problems[p][3]] = Math.min(
                                dp[i+problems[p][2]][j+problems[p][3]],
                                dp[i][j] + problems[p][4]);
                        }
                    }
                } 
            }
        }
        
        
        return dp[max_alp][max_cop];
    }
}