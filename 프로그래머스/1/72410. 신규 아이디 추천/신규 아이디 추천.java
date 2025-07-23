import java.util.*;

class Solution {
    public String solution(String new_id) {
        // a - A = 32
        // a = 97 / z = 122 / A = 65 / Z = 90
        // '-' = 45 / '_' = 95 / '.' = 46
        
        char[] rid = new_id.toCharArray();
        ArrayList<Character> nrid = new ArrayList<>();
        
        for(int i=0; i<rid.length; i++){
            // 1단계
            if(65<=rid[i] && rid[i]<=90){
                //대문자 -> 소문자
                rid[i] = (char) (rid[i] + 32);
                
                nrid.add(rid[i]);
                continue;
            }
            
            if(97<=rid[i] && rid[i]<=122){
                nrid.add(rid[i]);
                continue;
            }
            
            // 2단계
            if(!Character.isDigit(rid[i]) && rid[i]!=45 && rid[i]!=95 && rid[i]!=46){
                continue;
            }
            
            nrid.add(rid[i]);
            
        }
        
        // 3단계
        int tmp = 0;
        for(int i=nrid.size()-1; i>=0; i--){
            if(nrid.get(i)==46){
                // '.' 이면 하나만 남기기
                tmp++;
                
            }else{
                tmp = 0;
            }
            
            if(tmp>1){
                nrid.remove(i);
            }
            
        }
        
        // 4단계
        if((nrid.size() > 0) && (nrid.get(0) == 46)){
            nrid.remove(0);
        }
        if((nrid.size() > 0) && (nrid.get(nrid.size()-1) == 46)){
            nrid.remove(nrid.size()-1);
        }
        
        // 5단계
        if(nrid.size() == 0){
            nrid.add('a');
        }
        
        // 6단계
        if(nrid.size() >= 16){
            for(int i=nrid.size()-1; i>=15; i--){
                nrid.remove(i);
            }
            
            if(nrid.get(nrid.size()-1) == 46){
                nrid.remove(nrid.size()-1);
            }
        }
        
        // 7단계
        if(nrid.size() <= 2){
            char c = nrid.get(nrid.size()-1);
            
            while(nrid.size() != 3){
                nrid.add(c);
            }
        }
        
        
        String result = "";
        for(int i=0; i<nrid.size(); i++){
            result += nrid.get(i);
        }
        
        
        return result;
    }
}