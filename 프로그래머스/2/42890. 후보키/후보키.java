import java.util.*;

class Solution {
    
    static int N;
    static int M;
    static boolean[] isSelected2;
    static boolean[] isSelected;
    static String[][] table;
    static int result = 0;
    static boolean flag;
    static ArrayList<String> candi = new ArrayList<>();
    
    public void check(int cnt){
        if(cnt == M){
            
            String tmp = "";
            for(int i=0; i<M; i++){
                
                if(isSelected2[i]){
                        tmp += i;
                }
            }
            
            for(int i=0; i<candi.size(); i++){
                if(candi.get(i).equals(tmp)){
                    flag = true;
                }
            }
            
            return;
        }
        
        // 현재 선택된 후보 집합이라면..
        if(isSelected[cnt]){
            isSelected2[cnt] = false;
            check(cnt+1);
            isSelected2[cnt] = true;
            check(cnt+1);
            
        }else{
            check(cnt + 1);
        }
        
    }
    
    public void subSet(int cnt){
        if(cnt == M){
            Set<String> s = new HashSet<>();
            
            for(int i=0; i<N; i++){
                String tmp = "";
                for(int j=0; j<M; j++){
                    if(isSelected[j]){
                        tmp += table[i][j];
                    }
                }
                if(!tmp.equals("")){
                    s.add(tmp);
                }
                
            }
            
            if(s.size() == N){
                // 현재 선택된 애들로 부분집합 구성 시 후보키가 되는지 체크
                isSelected2 = new boolean[M];
                check(0);
                
                if(flag){
                    // 최소성 만족 X
                    flag = false;
                    return;
                }
                
                String tmp2 = "";
                for(int i=0; i<M; i++){
                    if(isSelected[i]){
                        tmp2 += i;
                    }
                }
                candi.add(tmp2);
                result++;
            }
            
            return;
        }
        
        isSelected[cnt] = false;
        subSet(cnt+1);
        isSelected[cnt] = true;
        subSet(cnt+1);
        
    }
    
    public int solution(String[][] relation) {
        N = relation.length;
        M = relation[0].length;
        isSelected = new boolean[M];
        
        table = relation;
        
        subSet(0);
        
        return result;
    }
}