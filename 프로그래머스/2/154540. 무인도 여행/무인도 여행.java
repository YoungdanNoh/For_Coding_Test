import java.util.*;

class Solution {
    
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    static ArrayList<Integer> result = new ArrayList<>();
    static int N;
    static int M;
    
    class Node{
        int x;
        int y;
        
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    public void bfs(int x, int y){
        Deque<Node> q = new ArrayDeque<>();
        q.offer(new Node(x, y));
        visited[x][y] = true;
        int cnt = 0;
        
        while(!q.isEmpty()){
            Node cur = q.poll();
            cnt += Integer.parseInt("" + map[cur.x][cur.y]);
            
            for(int i=0; i<4; i++){
                int tx = cur.x + dx[i];
                int ty = cur.y + dy[i];
                
                if(tx<0 || tx>=N || ty<0 || ty>=M) continue;
                
                if(visited[tx][ty]) continue;
                
                if(map[tx][ty] == 'X') continue;
                
                visited[tx][ty] = true;
                q.offer(new Node(tx, ty));
            }
            
        }
        result.add(cnt);
        
    }
    
    public int[] solution(String[] maps) {
        
        int[] answer = {-1};
        N = maps.length;
        M = maps[0].length();
        
        map = new char[N][M];
        
        for(int i=0; i<N; i++){
            char[] tmp = maps[i].toCharArray();
            
            for(int j=0; j<M; j++){
                map[i][j] = tmp[j];
            }
        }
        
        visited = new boolean[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(!visited[i][j] && map[i][j] != 'X'){
                    bfs(i, j);
                }       
            }
        }
        
        Collections.sort(result);
        if(result.size() > 0){
            answer = new int[result.size()];
            
            for(int i=0; i<result.size(); i++){
                answer[i] = result.get(i);
            }
        }
        
        return answer;
    }
}