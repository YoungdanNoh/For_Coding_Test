import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        
        boolean[] visited = new boolean[n+1];
        
        for(int i=0; i<lost.length; i++){
            for(int j=0; j<reserve.length; j++){
                
                if(lost[i] == reserve[j]){
                    visited[lost[i]] = true;
                }
            }
        }
        
        Arrays.sort(reserve);
        Arrays.sort(lost);
        
        for(int i=0; i<reserve.length; i++){
            if(visited[reserve[i]]){
                continue;
            }
            
            int sb = reserve[i] - 1;
            int sa = reserve[i] + 1;
            
            for(int j=0; j<lost.length; j++){
                
                if(visited[lost[j]]){
                    continue;
                }
                
                if(lost[j] == sb || lost[j] == sa){
                    // 빌려줄 수 있다면
                    visited[lost[j]] = true;
                    break;
                }
            }
            
        }
        
        int cnt = 0;
        for(int i=0; i<lost.length; i++){
            if(!visited[lost[i]]){
                cnt++;
            }
        }
        
        return n-cnt;
    }
}