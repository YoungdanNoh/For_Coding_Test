import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        // 숙련도를 이분탐색
        int start = 1;
        int end = 100000;
        
        int result = Integer.MAX_VALUE;
        while(start <= end){
            int mid = (start + end) / 2; // 숙련도
            
            long time = 0;
            for(int i=0; i<diffs.length; i++){
                if(diffs[i] <= mid){
                    // 숙련도보다 낮은 문제
                    time += times[i];
                    
                }else{
                    //어려운 문제
                    int incorrect = diffs[i] - mid; // 틀리는 횟수
                    if(i < 1){
                        time = time + (times[i])*incorrect + times[i];
                        
                    }else{
                        time = time + (times[i-1] + times[i])*incorrect + times[i];
                        
                    }
                }
            }
            
            if(time <= limit){
                // 제한 시간 내에 풀 수 있음
                result = Math.min(result, mid);
                end = mid - 1;
                
            }else{
                start = mid + 1;
                
            }
        }
        
        return result;
    }
}