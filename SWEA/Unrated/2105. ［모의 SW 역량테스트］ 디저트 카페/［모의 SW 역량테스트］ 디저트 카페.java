import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[][] map;
    static int[] dx = {1, 1, -1, -1, 1};
    static int[] dy = {1, -1, -1, 1, 1};
    static int sx, sy;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(bf.readLine().trim());
        for(int t=1; t<=T; t++){
            ans = -1;

            N = Integer.parseInt(bf.readLine().trim());
            map = new int[N][N];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(bf.readLine());
                for(int j=0; j<N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i=0; i<N-2; i++) {
                for (int j = 1; j < N-1; j++) {
                    sx = i; sy = j;
                    dfs(0, i, j, new ArrayList<>());
                }
            }
            System.out.println("#" + t + " " + ans);
        }
    }
    public static void dfs(int n, int cx, int cy, ArrayList<Integer> visited){
        if(n>3){
            return;
        }
        if(n==3 && sx == cx && sy == cy){
            ans = Math.max(ans, visited.size());
            return;
        }

        for(int i=n; i<n+2; i++){
            int nx = cx + dx[i];
            int ny = cy + dy[i];
            if(0<=nx && nx<N && 0<=ny && ny<N){
                boolean flag = true;
                for(int k=0; k<visited.size(); k++){
                    if(visited.contains(map[nx][ny])) {
                        flag = false;
                    }
                }
                if(flag){
                    visited.add(map[nx][ny]);
                    dfs(i, nx, ny, visited);
                    visited.remove(visited.size()-1);
                }
            }
        }
    }
}