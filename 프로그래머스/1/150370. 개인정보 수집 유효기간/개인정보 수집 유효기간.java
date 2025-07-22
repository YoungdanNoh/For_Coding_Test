import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        // 약관종류, 유효기간
        Map<String, Integer> m = new HashMap<>();
        for(int i=0; i<terms.length; i++){
            String[] t = terms[i].split(" ");
            m.put(t[0], Integer.parseInt(t[1]));
        }
        
        ArrayList<Integer> r = new ArrayList<>(); 
        for(int i=0; i<privacies.length; i++){
            String[] p = privacies[i].split(" ");
            char[] date = p[0].toCharArray();
            int year = Integer.parseInt(date[0] + "" + date[1] + "" + date[2] + "" + date[3]);
            int month = Integer.parseInt(date[5] + "" + date[6]);
            int day = Integer.parseInt(date[8] + "" + date[9]);
            
            month += m.get(p[1]); // 유효기간 더하기
            if(month > 12){
                year += (month/12);
                month = month%12;
            }
            
            day -= 1;
            if(day == 0){
                day = 28;
                month -= 1;
            }
            
            if(month == 0){
                month = 12;
                year -= 1;
            }
            
            String tmp = "";
            if(month < 10 && day<10){
                tmp = year + ".0" + month + ".0" + day;
                
            }else if(month < 10){
                tmp = year + ".0" + month + "." + day;
            
            }else if(day < 10){
                tmp = year + "." + month + ".0" + day;
            }else{
                tmp = year + "." + month + "." + day;
            }
            
            if(today.compareTo(tmp) > 0){
                r.add(i+1);
            }
        }
        
        int[] result = new int[r.size()];
        for(int i=0; i<r.size(); i++){
            result[i] = r.get(i);
        }
        
        return result;
    }
}