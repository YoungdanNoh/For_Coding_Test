import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        
        Arrays.sort(rocks);
        
        int[] diff = new int[rocks.length + 1];
        diff[0] = rocks[0];
        
        int cur = distance;
        for(int i=rocks.length-1; i>=0; i--){
            diff[i+1] = cur - rocks[i];
            cur = rocks[i];
        }
        
        int start = 1;
        int end = distance;
        int result = 0;
        while(start <= end){
            int mid = (start+end)/2;
            int dist = 0;
            int rstone = 0;
            
            for(int i=0; i<diff.length; i++){
                dist += diff[i];
                
                if(dist < mid){
                    rstone += 1;
                    continue;
                }
                
                dist = 0;
            }
            
            if(rstone > n){
                end = mid - 1;
                
            }else{
                start = mid + 1;
                result = Math.max(result, mid);
                
            }
            
        }
        
        
        return result;
    }
}