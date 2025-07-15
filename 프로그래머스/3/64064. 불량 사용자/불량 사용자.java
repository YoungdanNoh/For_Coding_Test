import java.util.*;

class Solution {
    
    public static boolean[] visited;
    public static String[] choice;
    public static Set<String> s = new HashSet<>();
    public static int N;
    public static int R;
    public static String[] copy_user;
    public static String[] copy_banned;
    
    public void perm(int cnt){
        
        if(cnt == R){
            
            String nic = "";
            
            // 앞의 R개만 보기
            for(int i=0; i<R; i++){
                
                // 닉네임 길이가 다르면 return;
                if(copy_banned[i].length() != choice[i].length()){
                    return;
                }
                
                // 닉네임이 매칭이 안 되면 return;
                char[] tmp = choice[i].toCharArray();
                for(int j=0; j<tmp.length; j++){
                    
                    if(copy_banned[i].charAt(j) == '*'){
                        continue;
                    }
                    
                    if(copy_banned[i].charAt(j) != tmp[j]){
                        return;
                    }
                    
                }
                
                nic = nic + choice[i] + " ";
                
            }
            
            Iterator iter = s.iterator();
            while(iter.hasNext()){
                String[] cur = ((String) iter.next()).split(" ");
                int test = 0;
                
                for(int i=0; i<R; i++){
                    for(int j=0; j<R; j++){
                        if(cur[i].equals(choice[j])){
                            test++;
                        }
                    
                    }
                }
                
                if(test >= R) return;
            }
            
            s.add(nic);
            return;
        }
        
        for(int i=0; i<N; i++){
            if(visited[i]) continue;
            
            choice[cnt] = copy_user[i];
            visited[i] = true;
            perm(cnt+1);
            
            visited[i] = false;
            
        }
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        
        N = user_id.length;
        R = banned_id.length;
        copy_user = new String[N];
        copy_banned = new String[R];
        
        for(int i=0; i<N; i++){
            copy_user[i] = user_id[i];
            
            if(i < R){
                copy_banned[i] = banned_id[i];
            }
        }
        
        visited = new boolean[N];
        choice = new String[R];
        
        perm(0);
        
        return s.size();
    }
}