import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    static int[][] arr;
    static int x2;
    static int y2;
    static int I;
    static boolean[][] visited;

    //이동할 수 있는 범위
    static int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dy = {-2, -1, 1, 2, -2, -1, 1, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(bf.readLine());

        for(int i=0; i<T; i++){
            I = Integer.parseInt(bf.readLine());
            arr = new int[I][I];
            visited = new boolean[I][I];

            //현재 칸
            st = new StringTokenizer(bf.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());

            //이동하고자 하는 칸
            st = new StringTokenizer(bf.readLine());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            if(x1 == x2 && y1 == y2){
                System.out.println(0);
            }else{
                bfs(arr, x1, y1);
                System.out.println(arr[x2][y2]);
            }
        }
    }
    public static void bfs(int[][] arr, int x, int y){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x,y});
        visited[x][y] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            if(cur[0] == x2 && cur[1] == y2){
                break;
            }

            for(int i=0; i<8; i++){
                int tx = cur[0] + dx[i];
                int ty = cur[1] + dy[i];


                if(tx<0 || tx>=I || ty<0 || ty>=I){
                    continue;
                }

                if(visited[tx][ty]){
                    continue;
                }

                visited[tx][ty] = true;
                arr[tx][ty] = arr[cur[0]][cur[1]] + 1;
                q.offer(new int[]{tx, ty});
            }

        }
    }
}
