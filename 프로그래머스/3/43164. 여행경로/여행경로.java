import java.util.*;

class Solution {
    
    static int N;
    static HashMap<String, ArrayList<String>> map;
    static ArrayList<String> allroute = new ArrayList<>();
    
    public void dfs(String start, String routes, int cnt){
        
        if(cnt == N){
            allroute.add(routes);
            return;
        }
        
        ArrayList<String> val = map.get(start);
        if (val == null) return;
        
        for(int i=0; i<val.size()-1; i+=2){
            if(val.get(i+1).equals("0")){
                // 방문하지 않았다면
                val.set(i+1, "1");
                dfs(val.get(i), routes + " " + val.get(i), cnt+1);
                val.set(i+1, "0");
            }
        }
        
    }
    
    public String[] solution(String[][] tickets) {
        
        N = tickets.length;
        map = new HashMap<>();
        
        for(int i=0; i<N; i++){
            String key = tickets[i][0];
            
            map.putIfAbsent(key, new ArrayList<>());
                    
            map.get(key).add(tickets[i][1]);
            map.get(key).add("0");
        }
        
        dfs("ICN", "ICN", 0);
        
        String[] result = {};
        Collections.sort(allroute);
        result = allroute.get(0).split(" ");
        
        return result;
    }
}