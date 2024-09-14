import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] map;
    static int dx[] = {0, -1, 0, 1}; //동, 북, 서, 남
    static int dy[] = {1, 0, -1, 0};
    static int x = 0, y = 0; //처음 위치는 (0, 0)
    static int[][] dice;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        dice = new int[][]{{0, 2, 0},
                           {4, 1, 3},
                           {0, 5, 0},
                           {0, 6, 0}};

        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int direc = 0; //맨 처음 방향은 동쪽
        for (int k = 0; k < K; k++) {
            //K번 이동한다.
            int tx = x + dx[direc];
            int ty = y + dy[direc];

            if(tx<0 || tx>=N || ty<0 || ty>=M){
                //해당 위치에 칸이 없다면 반대 방향으로 방향을 결정
                direc += 2;
                if(direc > 3){
                    direc -= 4;
                } // 이동방향 반대로 바꾸기
                tx = x + dx[direc];
                ty = y + dy[direc];
            }

            // 점수 획득
            ans += bfs(tx, ty);

            // 주사위 전개도 변경
            if(direc == 0){
                //동쪽이라면
                int tmp = dice[1][2];
                for (int i = 2; i > 0; i--) {
                    dice[1][i] = dice[1][i-1];
                }
                dice[1][0] = tmp;

                tmp = dice[3][1];
                dice[3][1] = dice[1][0];
                dice[1][0] = tmp;
            }else if (direc == 1) {
                //북쪽이라면
                int tmp = dice[0][1];
                for (int i = 0; i < 3; i++) {
                    dice[i][1] = dice[i+1][1];
                }
                dice[3][1] = tmp;
            }else if (direc == 2) {
                //서쪽이라면
                int tmp = dice[1][0];
                for (int i = 0; i < 2; i++) {
                    dice[1][i] = dice[1][i+1];
                }
                dice[1][2] = tmp;

                tmp = dice[3][1];
                dice[3][1] = dice[1][2];
                dice[1][2] = tmp;
            }else{
                //남쪽이라면
                int tmp = dice[3][1];
                for (int i = 3; i > 0; i--) {
                    dice[i][1] = dice[i-1][1];
                }
                dice[0][1] = tmp;
            }

            // 다음 이동 방향 결정
            if(dice[3][1] > map[tx][ty]){
                // 이동 방향을 90도 시계 방향으로 회전
                direc -= 1;
                if(direc < 0) direc = 3;
            }
            else if(dice[3][1] < map[tx][ty]){
                // 이동 방향을 90도 반시계 방향으로 회전
                direc += 1;
                if(direc > 3) direc = 0;
            }
            x = tx;
            y = ty;
        }
        System.out.println(ans);
    }

    private static int bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        int check = map[x][y]; // 이 점수와 같은 점수들만 더할 것임
        int result = check;

        visited[x][y] = true;
        q.offer(new int[]{x, y});

        while (!q.isEmpty()){
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int tx = cur[0] + dx[i];
                int ty = cur[1] + dy[i];

                if(tx<0 || tx>=N || ty<0 || ty>=M) continue;
                if(visited[tx][ty]) continue;
                if(map[tx][ty] != check) continue;

                visited[tx][ty] = true;
                result += map[tx][ty];
                q.offer(new int[]{tx, ty});
            }
        }
        return result;
    }
}