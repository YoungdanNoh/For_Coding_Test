import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = bf.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j) - '0';
            }
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        boolean[][][] visited = new boolean[N][M][K+1]; // 0: 안 부숨, 1: 부순 개수
        Deque<int[]> q = new ArrayDeque<>();

        q.add(new int[]{0, 0, 0, 0}); // x, y, 부순 벽 개수, 시간
        visited[0][0][0] = true;

        int ans = -1;
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if((cur[0] == N-1) && (cur[1] == M-1)) {
                ans = cur[3];
                break;
            }

            for(int i = 0; i < 4; i++) {
                int tx = cur[0] + dx[i];
                int ty = cur[1] + dy[i];

                if(tx<0 || tx>=N || ty<0 || ty>=M) continue;

                if(board[tx][ty] == 1){
                    // 해당 벽을 부수고 이동
                    if((cur[2] < K) && !visited[tx][ty][cur[2]+1]) {
                        visited[tx][ty][cur[2]+1] = true;
                        q.add(new int[]{tx, ty, cur[2] + 1, cur[3] + 1});
                    }

                }else{
                    if(!visited[tx][ty][cur[2]]){
                        visited[tx][ty][cur[2]] = true;
                        q.offer(new int[]{tx, ty, cur[2], cur[3]+1});
                    }
                }
            }
        }

        ans = ans == -1 ? -1 : ans+1;
        System.out.println(ans);

    }
}
