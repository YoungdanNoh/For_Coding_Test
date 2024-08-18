import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int ans = 3000*12*31;
    static int[] arr;
    static int day;
    static int month;
    static int m3;
    static int year;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        int T = Integer.parseInt(st.nextToken());

        for(int t=1; t<=T; t++){
            st = new StringTokenizer(bf.readLine());
            day = Integer.parseInt(st.nextToken());
            month = Integer.parseInt(st.nextToken());
            m3 = Integer.parseInt(st.nextToken());
            year = Integer.parseInt(st.nextToken());;

            arr = new int[12];
            st = new StringTokenizer(bf.readLine());
            for(int i=0; i<12; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0);
            System.out.println("#"+t+" "+ans);
            ans = 3000*12*31;
        }
    }
    public static void dfs(int idx, int t_ans){
        if(idx >= 12){
            ans = Math.min(ans, t_ans);
        }

        if(idx < 12){
            dfs(idx+1, t_ans + arr[idx]*day); //하루 비용
            dfs(idx+1, t_ans + month); //한달 이용권
            dfs(idx+3, t_ans + m3); //3달 이용권
            dfs(idx+12, t_ans + year); //1년 이용권
        }
    }
}