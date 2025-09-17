import java.util.*;

class Point{
    int x;
    int y;
    
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Solution {
    
    static int ans = 0;
    static int N = 0;
    static boolean[] cnum = new boolean[7];
    static boolean[] isSelected = new boolean[7];
    static int[] result;
    static ArrayList<Point>[] cardPoint;
    static int[][] Board;
    static int R;
    static int C;
    
    public int solution(int[][] board, int r, int c) {
        
        Board = board;
        R = r;
        C = c;
        
        cardPoint = new ArrayList[7];
        for(int i=0; i<7; i++){
            cardPoint[i] = new ArrayList<>();
        }
        
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                int card = board[i][j];
                if(card == 0) continue;
                
                cardPoint[card].add(new Point(i, j));
                if(!cnum[card]){
                    N++;
                    cnum[card] = true;
                }
            }
        }
        
        result = new int[N];
        perm(0);
        
        return ans;
    }
    
    public void perm(int cnt){
        if(cnt == N){
            
            int[][] copy = new int[4][4];
            for (int i=0; i<4; i++) copy[i] = Board[i].clone(); // 보드 복사
            
            int cr = R, cc = C; // 현재 커서 위치
            int total = 0; // 현재 순열 순서로 이동했을 때의 이동횟수 결과 값
            
            for (int i=0; i<N; i++) {
                int card = result[i]; // 현재 카드 번호
                Point p1 = cardPoint[card].get(0); // 현재 카드 번호 위치1
                Point p2 = cardPoint[card].get(1); // 현재 카드 번호 위치2
                
                // 두 장 뒤집는 경우는 2가지: p1 -> p2, p2 -> p1
                int d1 = bfs(copy, cr, cc, p1.x, p1.y) + 1   // 이동 후 Enter(해당 카드 선택) = +1
                       + bfs(copy, p1.x, p1.y, p2.x, p2.y) + 1;
                int d2 = bfs(copy, cr, cc, p2.x, p2.y) + 1
                       + bfs(copy, p2.x, p2.y, p1.x, p1.y) + 1;
                
                int dist = Math.min(d1, d2); // 더 짧은 경로 선택
                total += dist;

                // 카드 제거
                copy[p1.x][p1.y] = 0;
                copy[p2.x][p2.y] = 0;

                // 커서 위치 갱신 (더 짧게 끝난 쪽 좌표로)
                if (d1 <= d2) { cr = p2.x; cc = p2.y; }
                else { cr = p1.x; cc = p1.y; }
                
            }
            
            if(ans == 0){
                // 뒤집을 카드가 없는 경우(board의 모든 원소가 0인 경우)는 입력으로 주어지지 않음
                ans = total;
                
            }else{
                ans = Math.min(ans, total);
            }
            
            return;
        }
        
        for(int i=1; i<=6; i++){
			if(isSelected[i]) continue;
            if(!cnum[i]) continue;
			
			result[cnt] = i;
			isSelected[i] = true;
			
			perm(cnt+1);
			isSelected[i] = false;
		}
        
    }
    
    public int bfs(int[][] bd, int sx, int sy, int tx, int ty){

        // 방문 체크 배열
        boolean[][] visited = new boolean[4][4];

        // q: {x좌표, y좌표, 현재까지 누른 키 횟수}
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sx, sy, 0});
        visited[sx][sy] = true;

        // 상, 하, 좌, 우
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], d = cur[2];

            // 1) 한 칸 이동
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                
                if(0 > nx || nx >= 4 || 0 > ny || ny >= 4) continue;
                if(visited[nx][ny]) continue;
                
                // 보드 범위 안이고 아직 방문 안 했으면
                if (nx == tx && ny == ty) return d + 1;
                
                // 그게 아니라면 해당 위치도 큐에 넣어주고 다시 탐색
                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny, d + 1});
            }

            // 2) Ctrl 이동
            for (int k = 0; k < 4; k++) {
                int nx = x, ny = y;
                
                // 해당 방향으로 쭉 계속 이동
                while (true) {
                    
                    int tx2 = nx + dx[k], ty2 = ny + dy[k];
                    
                    // 보드 끝을 벗어나면 중단 (끝에 멈춤)
                    if (!(0 <= tx2 && tx2 < 4 && 0 <= ty2 && ty2 < 4)) break;
                    nx = tx2; ny = ty2;
                    
                    // 카드(값>0)를 만나면 거기서 정지
                    if (bd[nx][ny] != 0) break;
                }
                
                if (!visited[nx][ny]) {
                    
                    // 목표 도착했으면 바로 반환
                    if (nx == tx && ny == ty) return d + 1;
                    
                    // 그게 아니라면 해당 위치도 큐에 넣어주고 다시 탐색
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny, d + 1});
                }
            }
        }
        
        // 도달 불가능(짝 맞추기 불가능)
        return 0;
    }
}