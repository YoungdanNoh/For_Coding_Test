import java.util.regex.*;
import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        
        int playtime = 0; // 재생된 시간
        String result = "(None)";
        
        for(int i=0; i<musicinfos.length; i++){
            String[] music = musicinfos[i].split(",");
            String regex = "([ABCDEFG][#]?)";
            
            Pattern pattern = Pattern.compile(regex); // 1. 정규식 컴파일
            Matcher matcher = pattern.matcher(music[3]); // 2. 입력 문자열에 적용
            
            ArrayList<String> code = new ArrayList<>();
            
            int t1 = Integer.parseInt("" + music[0].charAt(0) + music[0].charAt(1))*60
                    + Integer.parseInt("" + music[0].charAt(3) + music[0].charAt(4));
            int t2 = Integer.parseInt("" + music[1].charAt(0) + music[1].charAt(1))*60
                    + Integer.parseInt("" + music[1].charAt(3) + music[1].charAt(4));
            
            int codecnt = 0;
            while(matcher.find()){
                if(codecnt < (t2-t1)){
                    codecnt++;
                    code.add(matcher.group(1));
                    
                }else{
                    break;
                }
            }
            
            int idx = 0;
            while(code.size() < (t2-t1)){
                code.add(code.get(idx++));
            }
            
            // for(int c=0; c<code.size(); c++){
            //     System.out.print(code.get(c) + ", ");
            // }
            // System.out.println();
            
            matcher = pattern.matcher(m);
            int cnt = 0;
            while(matcher.find()){
                cnt++;
            }
            
            int start = 0;
            int end = cnt - 1;
            while(code.size() >= cnt && end < code.size()){
                String allcode = "";
                
                for(int c=start; c<=end; c++){
                    allcode += code.get(c);
                }
                
                if(allcode.equals(m)){
                    if(result.equals("(None)")){
                        result = music[2];
                        
                    }else if(playtime < code.size()){
                        playtime = code.size();
                        result = music[2];
                        
                    }
                    
                }
                
                start += 1;
                end += 1;
            }
            
        }
        
        return result;
    }
}