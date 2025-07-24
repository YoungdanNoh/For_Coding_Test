import java.util.*;

class Solution {
    public int solution(String dartResult) {
        ArrayList<Integer> result = new ArrayList<>();
        
        char[] dr = dartResult.toCharArray();
        int idx = -1;
        String cur = "";
        for(int i=0; i<dr.length; i++){
            
            if(dr[i]=='S' || dr[i]=='D' || dr[i]=='T'){
                int num = Integer.parseInt(cur);
                
                idx++;
                if(dr[i]=='D'){
                    result.add(num*num);
                    
                }else if(dr[i]=='T'){
                    result.add(num*num*num);
                    
                }else{
                    result.add(num);
                    
                }
                
                cur = "";
                
            }else if(dr[i]=='*'){
                // 옵션이 있음
                // 이전에 얻은 점수가 없다면 현재 점수만 2배
                if(result.size() == 1){
                    result.set(idx, result.get(idx)*2);
                    
                }else{
                    result.set(idx-1, result.get(idx-1)*2);
                    result.set(idx, result.get(idx)*2);
                }
                
            }else if(dr[i]=='#'){
                result.set(idx, result.get(idx)*(-1));
                
            }else{
                cur += dr[i];
                
            }
        }
        
        int answer = 0;
        for(int i=0; i<result.size(); i++){
            answer += result.get(i);
        }
        
        return answer;
    }
}