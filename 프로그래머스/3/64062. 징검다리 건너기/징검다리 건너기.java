class Solution {
    public int solution(int[] stones, int k) {
        
        // 이분탐색 mid=건널 수 있는 사람 수
        int start = 0;
        int end = 200000000;
        int result = 0;
        
        while(start <= end){
            int mid = (start+end)/2;
            
            boolean flag = true;
            int zero_cnt = 0;
            for(int i=0; i<stones.length; i++){
                if((stones[i]-mid) < 0){
                    zero_cnt++;
                    if(zero_cnt >= k){
                        flag = false;
                        break;
                    }
                    
                }else{
                    zero_cnt = 0;
                }
            }
            
            if(flag){
                // 사람 수 더 늘리기
                start = mid+1;
                result = mid;
                
            }else{
                end = mid-1;
                
            }
        }
        
        return result;
    }
}