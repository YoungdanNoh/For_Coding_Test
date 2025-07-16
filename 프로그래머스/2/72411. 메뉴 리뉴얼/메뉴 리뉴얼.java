import java.util.*;

class Solution {
    
    static Map<String, Integer> m;
    static char[] o;
    static char[] choice;
    
    // 시작 idx, 선택 수, 선택해야하는 것의 수
    public void combi(int start, int cnt, int r){
        if(cnt == r){
            String tmp = "";
            
            for(int i=0; i<choice.length; i++){
                tmp += choice[i];
            }
            
            if(m.get(tmp) != null){
                m.put(tmp, m.get(tmp)+1);
                
            }else{
                m.put(tmp, 1);
                
            }
            
            return;
        }
        
        for(int i=start; i<o.length; i++){
            choice[cnt] = o[i];
            
            combi(i+1, cnt+1, r);
        }
    }
    
    public String[] solution(String[] orders, int[] course) {
        
        ArrayList<String> result = new ArrayList<>();
        
        for(int i=0; i<course.length; i++){
            m = new HashMap<>();
            
            for(int j=0; j<orders.length; j++){
                o = orders[j].toCharArray();
                
                if(o.length < course[i]) continue;
                
                Arrays.sort(o);
                choice = new char[course[i]];
                combi(0, 0, course[i]);
            }
            
            int max = 0;
            for(String k: m.keySet()){
                if(max < m.get(k)){
                    max = m.get(k);
                }
                //System.out.println(k + " " + m.get(k));
            }
            //System.out.println(max);
            
            
            if(max < 2) continue;
            
            //System.out.println("===========ADD===========");
            for(String k: m.keySet()){
                if(max == m.get(k)){
                    //System.out.println(k);
                    result.add(k);
                }
            }
            //System.out.println("===============================");
        }
        
        String[] answer = new String[result.size()];
        
        for(int i=0; i<answer.length; i++){
            answer[i] = result.get(i);
        }
        Arrays.sort(answer);
            
        return answer;
    }
}