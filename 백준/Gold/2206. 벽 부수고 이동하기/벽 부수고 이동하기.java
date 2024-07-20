import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] arr;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][][] visited;

    static int test = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for(int i=0; i<N; i++){
            String in = bf.readLine();
            for(int j=0; j<M; j++){
                arr[i][j] = in.charAt(j) - '0';
            }
        }

        visited = new boolean[N][M][2];
        bfs(0, 0, 0);
    }
    
    public static void bfs(int x, int y, int dest){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y, 1, 0}); // (0, 0)에서부터 시작 -> (N-1, M-1)로 이동
        //3, 4번째 값: 이동횟수, 벽 파괴(1) 또는 파괴x(0)
        int cnt = 1;
        int tx = 0;
        int ty = 0;

        while (!q.isEmpty()){
            int[] cur = q.poll();
            cnt = cur[2];
            if(cur[0] == N-1 && cur[1] == M-1){
                System.out.println(cnt);
                return;
            }

            for(int i=0; i<4; i++){
                tx = cur[0] + dx[i]; //칸 이동
                ty = cur[1] + dy[i];

                if(tx<0 || tx>=N || ty<0 || ty>=M){
                    continue;
                }

                if(arr[tx][ty]==0){ //벽이 아니라면
                    if(cur[3]==0 && !visited[tx][ty][0]){ //부신 벽이 없고, 방문한 적x
                        q.add(new int[]{tx, ty, cnt+1, 0});
                        visited[tx][ty][0] = true; //방문처리
                    }else if(cur[3]==1 && !visited[tx][ty][1]){ //부신 벽이 있고, 방문한 적x
                        q.add(new int[]{tx, ty, cnt+1, 1});
                        visited[tx][ty][1] = true; //방문처리
                    }
                }else{//벽이라면
                    if(cur[3] == 0){ //한번도 벽을 부순 적이 없다면
                        q.add(new int[]{tx, ty, cnt+1, 1});
                        visited[tx][ty][1] = true;
                    }
                }
            }
        }
        System.out.println(-1);
    }
}