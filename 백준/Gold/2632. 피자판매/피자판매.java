import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int buy = Integer.parseInt(bf.readLine().trim());
        st = new StringTokenizer(bf.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int tmp1 = 0, tmp2 = 0;

        int[] A = new int[M*2]; //A 피자의 조각 크기들
        for (int i = 0; i < M; i++) {
            A[i] = Integer.parseInt(bf.readLine().trim());
            A[i+M] = A[i];
            tmp1 += A[i];
        }

        int[] B = new int[N*2]; //B 피자의 조각 크기들
        for (int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(bf.readLine().trim());
            B[i+N] = B[i];
            tmp2 += B[i];
        }

        int[] nc1 = scase(A, M, tmp1+1);
        int[] nc2 = scase(B, N, tmp2+1);

        //System.out.println(Arrays.toString(nc1));
        //System.out.println(Arrays.toString(nc2));

        int result = 0;
        if(nc1.length > buy){
            result += nc1[buy];
            //System.out.println("aa");
        }
        if(nc2.length > buy){
            result += nc2[buy];
            //System.out.println("bb");
        }

        for (int i = 1; i < nc1.length ; i++) {
            if(i >= buy) break;

            int bs = buy - i;
            //System.out.println("i: " + i + ", bs: " + bs);
            if(nc2.length > bs){
                result += nc1[i] * nc2[bs];
                //System.out.println("nc1[i]: " + nc1[i] + ", nc2[bs]: " + nc2[bs]);
            }
        }

        System.out.println(result);
    }

    static int[] scase(int[] arr, int n, int c){
        int asum = 0;
        int[] nc = new int[c];
        for (int i = 0; i < n; i++) {
            asum += arr[i];
            int tmp = 0;
            for (int j = i; j < (i+n)-1; j++) {
                tmp += arr[j];
                //System.out.println(tmp);
                nc[tmp] += 1;
            }
        }
        nc[asum] = 1;
        return nc;
    }
}