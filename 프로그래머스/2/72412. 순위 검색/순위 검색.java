import java.util.*;

class Solution {
    
    static Map<String, ArrayList<Integer>> all = new HashMap<>();
    
    class Node{
        String lang;
        String job;
        String ca;
        String food;
        int score;
        
        public Node(String lang, String job, String ca, String food, int score){
            this.lang = lang;
            this.job = job;
            this.ca = ca;
            this.food = food;
            this.score = score;
        }
    }
    
    static int[] result;
    static boolean[] visited;
    
    public void combi(int start, int cnt, String[] tmp){
        
        if(cnt == 4){
            String key = "";
            
            for(int i=0; i<4; i++){
                if(visited[i]){
                    // 해당 위치에 "-"
                    key += "-";
                    
                }else{
                    key += tmp[i];
                }
            }
            
            if(all.get(key) == null){
                all.put(key, new ArrayList<>());
                all.get(key).add(Integer.parseInt(tmp[4]));
                
            }else{
                all.get(key).add(Integer.parseInt(tmp[4]));
                
            }
            return;
        }
        
        for(int i=start; i<4; i++){
            visited[i] = true;
            combi(i+1, cnt+1, tmp);
            visited[i] = false;
            combi(i+1, cnt+1, tmp);
        }
        
    }
    
    public int[] solution(String[] info, String[] query) {
        
        int[] ans = new int[query.length];
        
        for(int i=0; i<info.length; i++){
            // 지원자들 한번에 모으기
            String[] tmp = info[i].split(" ");
            
            result = new int[4];
            visited = new boolean[4];
            combi(0, 0, tmp);
            
        }
        
        for (ArrayList<Integer> list : all.values()) {
            Collections.sort(list); // 오름차순
        }
        
        for(int i=0; i<query.length; i++){
            
            String[] tmp = query[i].split(" ");
            String key = tmp[0] + tmp[2] + tmp[4] + tmp[6];
            int score = Integer.parseInt(tmp[7]);
            
            if(all.get(key) == null){
                // 해당되는 지원자 없음
                ans[i] = 0;
            }else{
                // 이분 탐색
                int start = 0;
                int end = all.get(key).size()-1;
                int result = -1;
                
                while(start <= end){
                    int mid = (start + end) / 2;
                    
                    if(all.get(key).get(mid) >= score){
                        result = mid;
                        end = mid - 1;
                    }else{
                        start = mid + 1;
                    }
                }
                
                if(result == -1){
                    ans[i] = 0;
                    
                }else{
                    ans[i] = all.get(key).size() - result;
                }
                
            }
        }
        
        return ans;
    }
}