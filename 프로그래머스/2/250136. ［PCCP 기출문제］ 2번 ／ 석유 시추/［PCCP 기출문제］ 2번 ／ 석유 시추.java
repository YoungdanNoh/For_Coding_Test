import java.util.*;

class Solution {
    
    class Node{
        int x;
        int y;
        
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    public void bfs(int x, int y, int[][] land){
        Set<Integer> s = new HashSet<>();
        s.add(y);
        int cnt = 1;
        
        visited[x][y] = true;
        Deque<Node> q = new ArrayDeque<>();
        q.offer(new Node(x, y));
        
        while(!q.isEmpty()){
            Node cur = q.poll();
            
            for(int i=0; i<4; i++){
                int tx = cur.x + dx[i];
                int ty = cur.y + dy[i];
                
                if(tx<0 || tx>=N || ty<0 || ty>=M) continue;
                if(visited[tx][ty]) continue;
                
                if(land[tx][ty] == 1){
                    visited[tx][ty] = true;
                    
                    s.add(ty);
                    
                    cnt++;
                    q.offer(new Node(tx, ty));
                }
            }
        }
        
        for(int col : s ){
            if(map.get(col) != null){
                // 해당 키 값에 +cnt
                map.put(col, map.get(col)+cnt);
                        
            }else{
                map.put(col, cnt);
            }
        }
    }
    
    static int N, M;
    static boolean[][] visited;
    static HashMap<Integer, Integer> map = new HashMap<>(); // 열 번호, 석유 수
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        visited = new boolean[N][M];
        
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(land[i][j] == 1 && !visited[i][j]){
                    // 아직 석유를 측정하지 않은 덩어리임
                    bfs(i, j, land);
                }
                
            }
        }
        
        int max = -1;
        
        for(int key: map.keySet()){
            if(max < map.get(key)){
                max = map.get(key);
            }
            
        }
        
        return max;
    }
}