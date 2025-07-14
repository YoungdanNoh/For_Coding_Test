import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        
        // user 딕셔너리에 uid1234: Muzi
        // result에는 "uid1234 님이 들어왔습니다"
        // 최종적으로 uid1234 위치에 해당되는 딕셔너리 value값 넣기
        
        Map<String, String> m = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();
        
        for(int i=0; i<record.length; i++){
            String[] tmp = record[i].split(" ");
            
            if(tmp[0].equals("Enter")){
                list.add(tmp[1] + " " + "님이 들어왔습니다.");
                    
                m.put(tmp[1], tmp[2]);
                
            }else if(tmp[0].equals("Leave")){
                list.add(tmp[1] + " " + "님이 나갔습니다.");
                
            }else{
                m.put(tmp[1], tmp[2]);
                
            }
        }
        
        // for(String k: m.keySet()){
        //     System.out.print(k + ": ");
        //     System.out.println(m.get(k));
        // }
        
        String[] result = new String[list.size()];
        
        for(int i=0; i<list.size(); i++){
            //System.out.println(list.get(i));
            String[] tmp = list.get(i).split(" ");
            String nick = m.get(tmp[0]);
            
            result[i] = nick + tmp[1] + " " + tmp[2];
        }
            
        return result;
    }
}