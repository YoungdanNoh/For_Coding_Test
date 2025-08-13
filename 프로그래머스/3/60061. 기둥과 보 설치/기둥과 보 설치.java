import java.util.*;

class Solution {
    
    class Node implements Comparable<Node> {
        int x;
        int y;
        int pb; // 기둥 or 보 (0: 기둥, 1: 보)
        
        public Node(int x, int y, int pb){
            this.x = x;
            this.y = y;
            this.pb = pb;
        }
        
        @Override
        public int compareTo(Node o){
            if(this.x != o.x){
                return Integer.compare(this.x, o.x);
                
            }else if(this.y != o.y){
                return Integer.compare(this.y, o.y);
                
            }else{
                return Integer.compare(this.pb, o.pb);
            }
        }
    }
    
    public boolean pValidation(int x, int y){
        
        if(y == 0){
            // (x, 0) : 바닥 위에 설치
            return true;
        }
        
        if(x > 0){
            for(int i=0; i<map[x-1].size(); i++){
                if(map[x-1].get(i).y == y && map[x-1].get(i).pb == 1){
                    // (x-1, y)에 보가 있거나
                    return true;
                }
            }
        }
        
        for(int i=0; i<map[x].size(); i++){
            if(map[x].get(i).y == y && map[x].get(i).pb == 1){
                // (x, y)에 보가 있거나
                return true;
            }
        }
        
        if(y > 0){
            for(int i=0; i<map[x].size(); i++){
                if(map[x].get(i).y == y-1 && map[x].get(i).pb == 0){
                    // (x, y-1)에 기둥이 있거나
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean bValidation(int x, int y){
        
        if(x<N && y>0){
            for(int i=0; i<map[x+1].size(); i++){
                if(map[x+1].get(i).y == y-1 && map[x+1].get(i).pb == 0){
                    // (x+1, y-1)에 기둥이 있거나
                    return true;
                }
            }
        }
        
        
        if(y>0){
            for(int i=0; i<map[x].size(); i++){
                if(map[x].get(i).y == y-1 && map[x].get(i).pb == 0){
                    // (x, y-1)에 기둥이 있거나
                    return true;
                }
            }
        }
        
        if(x>0 && x<N){
            boolean flag = false;
                
            for(int i=0; i<map[x-1].size(); i++){
                if(map[x-1].get(i).y == y && map[x-1].get(i).pb == 1){
                    // (x-1, y)에 보 and
                    flag = true;
                    break;
                }
            }

            if(!flag) return false;

            for(int i=0; i<map[x+1].size(); i++){
                if(map[x+1].get(i).y == y && map[x+1].get(i).pb == 1){
                    // (x+1, y)에 보
                    return true;
                }
            }
        }
        
        return false;
        
    }
    
    static ArrayList<Node>[] map;
    static ArrayList<Node> result;
    static int N;
    
    public int[][] solution(int n, int[][] build_frame) {
        // 기둥 설치 시
            // (x, 0) : 바닥 위에 있거나
            // (x-1, y) or (x, y)에 보가 있거나
            // (x, y-1)에 기둥이 있거나
        
        // 보 설치 시 
            // (4, 2)에 설치한다면 (5, 1) or (4, 1)에 기둥 또는 (3, 2) or (5, 2)에 보
            // (x, y)에 설치한다면 (x+1, y-1) or (x, y-1)에 기둥 또는 (x-1, y) and (x+1, y)에 보
        
        N = n;
        
        map = new ArrayList[n+1];
        for(int i=0; i<=n; i++){
            map[i] = new ArrayList<>();
        }
        
        result = new ArrayList<>();
        
        for(int b=0; b<build_frame.length; b++){
            
            int x = build_frame[b][0];
            int y = build_frame[b][1];
            
            if(build_frame[b][3] == 1 && build_frame[b][2] == 0){
                // 기둥을 설치하는 경우
                
                boolean val = pValidation(x, y);
                if(val){
                    map[x].add(new Node(x, y, 0));  
                    result.add(new Node(x, y, 0));
                }
                
            }else if(build_frame[b][3] == 1 && build_frame[b][2] == 1){
                // 보를 설치하는 경우
                
                boolean val = bValidation(x, y);
                if(val){
                    map[x].add(new Node(x, y, 1));  
                    result.add(new Node(x, y, 1));
                }
                
            }else if(build_frame[b][3] == 0 && build_frame[b][2] == 0){
                // 기둥을 삭제하는 경우
                // 일단 해당 기둥 삭제해본 후 val 체크
                for(int i=0; i<map[x].size(); i++){
                    if(map[x].get(i).y == y && map[x].get(i).pb == 0){
                        map[x].remove(i);
                        break;
                    }
                }
                
                boolean flag = false;
                for(int i=0; i<=n; i++){
                    for(int j=0; j<map[i].size(); j++){
                        if(map[i].get(j).pb == 0){
                            // 기둥 검증
                            if(!pValidation(map[i].get(j).x, map[i].get(j).y)){
                                flag = true;
                                break;
                            }
                        }else{
                            // 보 검증
                            if(!bValidation(map[i].get(j).x, map[i].get(j).y)){
                                flag = true;
                                break;
                            }
                        }
                    }
                    
                    if(flag) break;
                }
                
                if(flag){
                    // 삭제 불가 -> map에 다시 넣기
                    map[x].add(new Node(x, y, 0));
                    
                }else{
                    // 삭제 가능 -> result에서도 해당 기둥 삭제
                    for(int i=0; i<result.size(); i++){
                        if(result.get(i).x == x && result.get(i).y == y && result.get(i).pb == 0){
                            result.remove(i);
                            break;
                        }
                    }
                }
                
            }else{
                // 보를 삭제하는 경우
                // 일단 해당 보 삭제해본 후 val 체크
                for(int i=0; i<map[x].size(); i++){
                    if(map[x].get(i).y == y && map[x].get(i).pb == 1){
                        map[x].remove(i);
                        break;
                    }
                }
                
                boolean flag = false;
                for(int i=0; i<=n; i++){
                    for(int j=0; j<map[i].size(); j++){
                        if(map[i].get(j).pb == 0){
                            // 기둥 검증
                            if(!pValidation(map[i].get(j).x, map[i].get(j).y)){
                                flag = true;
                                break;
                            }
                        }else{
                            // 보 검증
                            if(!bValidation(map[i].get(j).x, map[i].get(j).y)){
                                flag = true;
                                break;
                            }
                        }
                    }
                    
                    if(flag) break;
                }
                
                if(flag){
                    // 삭제 불가 -> map에 다시 넣기
                    map[x].add(new Node(x, y, 1));
                    
                }else{
                    // 삭제 가능 -> result에서도 해당 보 삭제
                    for(int i=0; i<result.size(); i++){
                        if(result.get(i).x == x && result.get(i).y == y && result.get(i).pb == 1){
                            result.remove(i);
                            break;
                        }
                    }
                }
                
            }
        }
        
        
        Collections.sort(result);
        int[][] ans = new int[result.size()][3];
        for(int i=0; i<result.size(); i++){
            ans[i][0] = result.get(i).x;
            ans[i][1] = result.get(i).y;
            ans[i][2] = result.get(i).pb;
        }
        
        return ans;
    }
}