import java.util.*;

class Solution {
    
    static int N;
    static int[] choice = new int[5];
    static int[][] Q;
    static int[] Ans;
    static int result = 0;
    
    public void subSet(int cnt, int last){
        if(cnt == 5){
            // 일치도 체크
            HashMap<Integer, Integer> m = new HashMap<>();
            for(int i=0; i<5; i++){
                m.put(choice[i], 1);
            }
            
            int[] match = new int[Ans.length];
            for(int i=0; i<Q.length; i++){
                int tmp = 0;
                
                for(int j=0; j<Q[i].length; j++){
                    if(m.get(Q[i][j]) != null){
                        tmp++;
                    }
                }
                match[i] = tmp;
            }
            
            boolean flag = true;
            for(int i=0; i<Ans.length; i++){
                if(match[i] != Ans[i]){
                    flag = false;
                    break;
                }
            }
            if(flag) result++;
            
            return;
        }
        
        for(int i=last+1; i<=N; i++){
            choice[cnt] = i;
            subSet(cnt+1, i);
        }
    }
    
    public int solution(int n, int[][] q, int[] ans) {
        N = n;
        Q = q;
        Ans = ans;
        subSet(0, 0);
        
        return result;
    }
}