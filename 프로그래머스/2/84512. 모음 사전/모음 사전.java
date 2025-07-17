import java.util.*;

class Solution {
    public int solution(String word) {
        
        Map<Character, Integer> alpha = new HashMap<>();
        alpha.put('A', 1);
        alpha.put('E', 2);
        alpha.put('I', 3);
        alpha.put('O', 4);
        alpha.put('U', 5);
        
        String target = "";
        char[] w = word.toCharArray();
        for(int i=0; i<w.length; i++){
            target += alpha.get(w[i]);
        }
        
        String cur = "1";
        int result = 1;
        
        while(!cur.equals("55555")){
            
            if(target.equals(cur)){
                break;
            }
            
            result++;
            
            char[] tmp = cur.toCharArray();
            
            if(tmp.length < 5){
                cur += "1";
                continue;
            }
            
            // 5로 뒤가 채워져있다면..
            int five = 6;
            for(int i=tmp.length-1; i>=0; i--){
                if(tmp[i] == '5'){
                    five = i;
                    
                }else{
                    break;
                }
            }
            
            if(five == 6){
                // 마지막 숫자를 한개 올리기
                tmp[4] = (char) ((int)tmp[4] + 1);
                cur = "";
                for(int i=0; i<5; i++){
                    cur += tmp[i];
                }
                
            }else{
                // 해당 자리수까지 숫자 삭제, 바로 앞 숫자 한개 올리기
                if(five == 0) break;
                
                tmp[five-1] = (char) ((int)tmp[five-1] + 1);
                cur = "";
                for(int i=0; i<five; i++){
                    cur += tmp[i];
                }
            }
        }
        
        
        return result;
    }
}