import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static char[][] map;
    static int[][] visited;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        //sz = new int[N][M];
        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = bf.readLine().trim();

            map[i] = line.toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if(visited[i][j] == 0) {
                    dfs(i, j);
                }

            }
        }

        System.out.println(result);
    }

    public static void dfs(int x, int y) {

        if(visited[x][y] == 1) {
            result++;
            return;

        }else if(visited[x][y] == 2) {
            return;
        }

        visited[x][y] = 1;

        if(map[x][y] == 'U') {
            dfs(x-1, y);

        }else if(map[x][y] == 'D') {
            dfs(x+1, y);

        }else if(map[x][y] == 'L') {
            dfs(x, y-1);

        }else{
            dfs(x, y+1);
        }

        visited[x][y] = 2;
    }
}
