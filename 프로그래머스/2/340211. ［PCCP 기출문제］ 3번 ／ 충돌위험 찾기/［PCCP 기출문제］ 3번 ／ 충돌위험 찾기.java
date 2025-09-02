import java.util.*;

class Node{
    int x;
    int y;
    
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Solution {
    static int R;
    static int[][] Points;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1}; // 상하좌우
    static ArrayList<Node>[] robot; // 각 루트의 최적 경로 탐색
    
    public int solution(int[][] points, int[][] routes) {
        int ans = 0;
        R = routes.length;
        Points = points;
        
        robot = new ArrayList[R];
        
        for(int i=0; i<R; i++){
            robot[i] = new ArrayList<>();
        }
        
        for(int i=0; i<R; i++){
            bfs(routes[i], i);
        }
        
        // for(int i=0; i<R; i++){
        //     for(int j=0; j<robot[i].size(); j++){
        //         System.out.print("[" + robot[i].get(j).x + ", " + robot[i].get(j).y + "]" + ", ");
        //     }
        //     System.out.println();
        // }
        
        int maxLength = 0;
        for (int i = 0; i < R; i++) maxLength = Math.max(maxLength, robot[i].size());
        
        for(int i=0; i<maxLength; i++){
            Map<String, Integer> occ = new HashMap<>();
            
            for(int j=0; j<R; j++){
                // 각 로봇에 대해서 체크
                if(robot[j].size() <= i) continue;
                
                Node p = robot[j].get(i);
                String key = p.x + ", " + p.y;
                occ.put(key, occ.getOrDefault(key, 0) + 1);
            }
            
            for (int cnt : occ.values()) if (cnt >= 2) ans++;
        }
        
        
        return ans;
    }
    
    // 탐색 경로, 로봇 인덱스
    public void bfs(int[] route, int r){
        // 시작점
        int sx = Points[route[0] - 1][0];
        int sy = Points[route[0] - 1][1];
        int curX = sx, curY = sy;

        // t=0 위치 포함
        robot[r].add(new Node(curX, curY));

        // 연속 구간을 모두 순회 (p0->p1->p2->...->pk)
        for (int i = 1; i < route.length; i++) {
            int ex = Points[route[i] - 1][0];
            int ey = Points[route[i] - 1][1];

            // 1) r(행) 먼저 이동
            while (curX != ex) {
                curX += (curX < ex) ? 1 : -1;
                robot[r].add(new Node(curX, curY));
            }
            // 2) 그다음 c(열) 이동
            while (curY != ey) {
                curY += (curY < ey) ? 1 : -1;
                robot[r].add(new Node(curX, curY));
            }
        }
    }
}