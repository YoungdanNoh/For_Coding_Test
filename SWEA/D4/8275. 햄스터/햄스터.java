import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int N; //햄스터 우리 개수
    static int X; //한 우리에 X마리 이하의 햄스터 가능
    static int[] input; //각 우리에 넣을 햄스터 수
    static int[][] condition; //조건
    static int[] result;
    static int max;
    static int[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(bf.readLine().trim());
        for (int t = 1; t <= T ; t++) {
            max = -1;

            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            result = new int[N];
            ans = new int[N];

            X = Integer.parseInt(st.nextToken());

            int M = Integer.parseInt(st.nextToken());
            condition = new int[M][3];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(bf.readLine());
                int I = Integer.parseInt(st.nextToken()) - 1;
                int R = Integer.parseInt(st.nextToken()) - 1;
                int S = Integer.parseInt(st.nextToken());
                condition[i] = new int[]{I, R, S};
            }

            //중복 순열
            perm(0, 0);
            if(max == -1){
                System.out.println("#"+t+" -1");
            }else{
                System.out.print("#"+t + " ");
                for (int i = 0; i < ans.length; i++) {
                    System.out.print(ans[i] + " ");
                }
                System.out.println();
            }
        }
    }

    private static void perm(int cnt, int sum) {
        if(cnt == N){
            //System.out.println(Arrays.toString(result) + " sum: " + sum);
            boolean flag = true;
            for (int i = 0; i < condition.length; i++) {
                int I = condition[i][0];
                int R = condition[i][1];
                int S = condition[i][2];
                int tmp = 0;

                for (int j = I; j <= R ; j++) {
                    tmp += result[j];
                }

                if(tmp != S){
                    //System.out.println("aaa");
                    flag = false;
                }
            }

            if(flag){
                if(max < sum){
                    max = sum;
                    ans = result.clone();
                }
            }
            return;
        }
        for (int i = 0; i <= X ; i++) {
            result[cnt] = i;
            perm(cnt+1, sum+i);
        }
    }

}