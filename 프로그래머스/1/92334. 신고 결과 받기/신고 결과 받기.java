import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        
        Map<String, ArrayList<String>> m = new HashMap<>(); // 신고한 사람, 신고당한 사람
        Map<String, Integer> mCnt = new HashMap<>(); // 신고 당한 사람, 횟수
        
        for(int i=0; i<report.length; i++){
            String[] tmp = report[i].split(" ");
            
            if(m.get(tmp[0]) != null){
                // 신고인 목록에 있는 사람임
                boolean flag = false;
                for(int j=0; j<m.get(tmp[0]).size(); j++){
                    if(m.get(tmp[0]).get(j).equals(tmp[1])){
                        // 이미 tmp[0]이 tmp[1]을 신고한 기록이 있다면
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    // tmp[0]이 tmp[1]을 신고한 기록 없음
                    m.get(tmp[0]).add(tmp[1]);
                    mCnt.put(tmp[1], mCnt.getOrDefault(tmp[1], 0)+1);
                    
                }
            }else{
                // tmp[0]이 tmp[1]을 신고한 기록 없음
                ArrayList<String> arr = new ArrayList<>();
                arr.add(tmp[1]);
                m.put(tmp[0], arr);
                mCnt.put(tmp[1], mCnt.getOrDefault(tmp[1], 0)+1);
                
            }
        }
        
        int[] ans = new int[id_list.length];
        Map<String, Integer> cnt = new HashMap<>();
        for(int i=0; i<id_list.length; i++){
            cnt.put(id_list[i], 0);
        }
        
        for(String key: m.keySet()){
            // 신고한 사람, 신고당한 사람 순회하기
            for(int i=0; i<m.get(key).size(); i++){
                String tmp = m.get(key).get(i); // 신고당한 사람
                
                if(mCnt.get(tmp) >= k){
                    // 신고당한 사람 목록에 있고, 횟수가 k보다 크다면
                    cnt.put(key, cnt.getOrDefault(key, 0)+1);
                }
            }
        }
        
        for(int i=0; i<id_list.length; i++){
            ans[i] = cnt.get(id_list[i]);
        }
        
        return ans;
    }
}