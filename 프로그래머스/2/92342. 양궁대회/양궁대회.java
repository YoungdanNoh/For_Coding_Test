class Solution {
    
    static int max_diff = Integer.MIN_VALUE;
    static int[] result = new int[11]; 
    
    public int[] solution(int n, int[] info) {
        
        dfs(0, n, new int[11], info);
        
        if (max_diff == Integer.MIN_VALUE) return new int[]{-1};
        
        return result;
    }
    
    public void dfs(int idx, int left_arrows, int[] ryan, int[] peach){
        if(idx == 11){
            // 다 쏨
            if(left_arrows > 0){
                // 화살이 남음
                return;
            }
            
            int rs = 0;
            int ps = 0;
            
            for(int i=0; i<11; i++){
                
                if(ryan[i] > peach[i]){
                    rs += (10-i);
                    
                }else if (peach[i] > 0){
                    ps += (10-i);
                }
                
            }
            
            int diff = rs - ps;
            if(diff <= 0){
                // 어피치 승
                return;
            }
            
            if((diff > max_diff) || (diff == max_diff && better(ryan))){
                // result 값 갱신 여부 검사
                max_diff = diff;
                result = ryan.clone();
            }
            
            return;
        }
        
        // 화살을 쏘거나 안 쏘거나
        if (idx == 10) {
            // 마지막에는 남은 화살 모두 맞추기
            ryan[idx] = left_arrows;
            dfs(idx+1, 0, ryan, peach);
            ryan[idx] = 0;
            
        }else if(left_arrows >= (peach[idx] + 1)){
            // 쏠 수 있음
            ryan[idx] = peach[idx] + 1;
            dfs(idx+1, left_arrows - ryan[idx], ryan, peach);
            ryan[idx] = 0;
            
        }
        
        dfs(idx+1, left_arrows, ryan, peach);
    }
    
    public boolean better(int[] ryan){
        for(int i=10; i>=0; i--){
            if(ryan[i] > result[i]){
                return true;
                
            }else if (ryan[i] < result[i]) {
                return false;
            }
        }
        
        return false;
    }
}