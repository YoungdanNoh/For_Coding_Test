import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    static int ans;
    static int N;
    static int[] P; //유명인의 인기도
    static int[] C; //유명인의 친화력

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //ArrayList<Integer> a = new ArrayList();
        N = Integer.parseInt(bf.readLine().trim());
        P = new int[N];
        C = new int[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(bf.readLine());
            P[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++){
            if(Math.abs(P[i] - ans) <= C[i]){
                ans++;
            }        
        }
        
        System.out.println(ans);
    }
}