import java.util.*;

class Solution {
    
    static int N;
    static int[][] Q;
    static int[] Ans;
    static int result = 0;
    static int[] subset;
    
    public int solution(int n, int[][] q, int[] ans) {
        N = n;
        Q = q;
        Ans = ans;
        subset = new int[5];
        
        // 1 ~ n까지 5개 부분집합 구하기
        subSet(1, 0);
        
        return result;
    }
    
    public void subSet(int start, int cnt){
        if(cnt == 5){
            
            // 각 q에 대해 subset과 몇개가 일치하는지 매칭하기
            Map<Integer, Integer> m = new HashMap<>();
            for(int i=0; i<5; i++){
                m.put(subset[i], 1);
            }
            
            int[] tmp = new int[Q.length];
            for(int i=0; i<Q.length; i++){
                int check = 0;
                
                for(int j=0; j<Q[i].length; j++){
                    if(m.get(Q[i][j]) != null){
                        check++;
                    }
                }
                
                tmp[i] = check;
            }
            
            boolean flag = true;
            for(int i=0; i<Ans.length; i++){
                if(Ans[i] != tmp[i]){
                    flag = false;
                    break;
                }
            }
            
            if(flag) result++;
            
            return;
        }
        
        for(int i=start; i<=N; i++){
            subset[cnt] = i;
            subSet(i+1, cnt+1);
        }
        
    }
}