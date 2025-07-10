import java.util.*;

class Solution {
    public int solution(int[] money) {
        
        // 첫번째 집을 터는 경우
        int[] dp1 = new int[money.length];
        dp1[0] = money[0];
        dp1[1] = Math.max(dp1[0], money[1]);
        
        for(int i=2; i<money.length-1; i++){
            dp1[i] = Math.max(dp1[i-1], money[i]+dp1[i-2]);
        }
        
        int max1 = 0;
        for(int i=0; i<dp1.length; i++){
            max1 = Math.max(max1, dp1[i]);
        }
        
        // 마지막 집을 터는 경우
        int[] dp2 = new int[money.length];
        dp2[0] = 0;
        dp2[1] = Math.max(dp2[0], money[1]);
        
        for(int i=2; i<money.length; i++){
            dp2[i] = Math.max(dp2[i-1], money[i] + dp2[i-2]);
        }
        
        int max2 = 0;
        for(int i=0; i<dp2.length; i++){
            max2 = Math.max(max2, dp2[i]);
        }
        
        return Math.max(max1, max2);
    }
}