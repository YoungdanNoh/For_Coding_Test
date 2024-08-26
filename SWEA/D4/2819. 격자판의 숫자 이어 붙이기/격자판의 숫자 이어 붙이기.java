import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0}; //상하좌우
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Integer> num_list;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(bf.readLine().trim());
        for(int t=1; t<=T; t++){
            ans = 0;
            num_list = new ArrayList<>();

            map = new int[4][4];
            for(int i=0; i<4; i++){
                st = new StringTokenizer(bf.readLine());
                for(int j=0; j<4; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i=0; i<4; i++) {
                for (int j = 0; j < 4; j++) {
                    dfs(i, j, 1, map[i][j]);
                }
            }

            System.out.println("#" + t + " " + ans);
        }
    }

    private static void dfs(int x, int y, int cnt, int num) {
        if(cnt == 7){
            if(num_list.contains(num)){
                return;
            }

            num_list.add(num);
            ans++;
            return;
        }

        for(int i=0; i<4; i++){
            int tx = x + dx[i];
            int ty = y + dy[i];

            if(tx<0 || tx>=4 || ty<0 || ty>=4) continue;

            dfs(tx, ty, cnt+1, num*10+map[tx][ty]);
        }
    }
}