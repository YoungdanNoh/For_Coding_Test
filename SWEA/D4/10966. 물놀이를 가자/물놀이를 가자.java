import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int N, M;
    static char[][] map;
    static int ans;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<int[]> q = new LinkedList<>();
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(bf.readLine().trim());
        for (int t = 1; t <= T ; t++) {
            ans = 0;

            st = new StringTokenizer(bf.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new char[N][M];
            visited = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                String s = bf.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = s.charAt(j);
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(map[i][j] == 'W'){
                        q.offer(new int[]{i, j, 0});
                        visited[i][j] = true;
                    }
                }
            }
            bfs();
            System.out.println("#"+t+" "+ans);
        }
    }

    private static void bfs() {
        while (!q.isEmpty()){
            int[] cur = q.poll();

            for (int k = 0; k < 4; k++) {
                int tx = cur[0] + dx[k];
                int ty = cur[1] + dy[k];

                if(tx<0 || tx>=N || ty<0 || ty>=M) continue;
                if(visited[tx][ty]) continue;
                if(map[tx][ty] == 'L'){
                    ans += cur[2]+1;
                }

                q.offer(new int[]{tx, ty, cur[2]+1});
                visited[tx][ty] = true;
            }
        }
    }
}