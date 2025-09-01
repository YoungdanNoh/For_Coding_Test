import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static int[] dx = {1, 1, 1}; // 좌하단, 하단, 우하단
    static int[] dy = {-1, 0, 1};
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<M; i++) {
            // 0행의 각 위치에서부터 시작해보기
            dfs(0, i, -1, map[0][i]);
        }

        System.out.println(ans);
    }

    // x, y 좌표, dir은 현재 방향, cost는 연료
    public static void dfs(int x, int y, int dir, int cost) {
        if(cost >= ans) return;

        if(x == N-1){
            ans = Math.min(ans, cost);
            return;
        }

        if(dir == -1){
            // 모든 방향으로 이동 가능
            for(int d=0; d<3; d++){
                int tx = x + dx[d];
                int ty = y + dy[d];

                if(tx >= 0 && tx < N && ty >= 0 && ty < M){
                    dfs(tx, ty, d, cost+map[tx][ty]);
                }
            }

        }else{
            // 해당 방향을 제외하고 이동 가능
            for(int d=0; d<3; d++){
                if(d == dir) continue;

                int tx = x + dx[d];
                int ty = y + dy[d];

                if(tx >= 0 && tx < N && ty >= 0 && ty < M){
                    dfs(tx, ty, d, cost+map[tx][ty]);
                }
            }
        }
    }
}
