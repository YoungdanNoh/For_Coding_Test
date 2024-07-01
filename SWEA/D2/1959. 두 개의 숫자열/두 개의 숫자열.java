import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int i = 0; i<T; i++){
            //각각의 케이스마다 실행
            int N = sc.nextInt();
            int M = sc.nextInt();

            int[] A = new int[N];
            int[] B = new int[M];
            for(int n=0; n<N; n++){
                A[n] = sc.nextInt();
            }
            for(int m=0; m<M; m++){
                B[m] = sc.nextInt();
            }

            int result = 0;
            if(N <= M){
                int C = M-N+1;
                for(int c=0; c<C; c++){
                    int temp = 0;
                    for(int idx=0; idx<N; idx++){
                        temp = temp + A[idx] * B[idx+c];
                    }

                    if(c==0){
                        result = temp;
                    }else{
                        if(result < temp){
                            result = temp;
                        }
                    }
                }

            }else{
                int C = N-M+1;
                for(int c=0; c<C; c++){
                    int temp = 0;
                    for(int idx=0; idx<M; idx++){
                        temp = temp + B[idx] * A[idx+c];
                    }

                    if(c==0){
                        result = temp;
                    }else{
                        if(result < temp){
                            result = temp;
                        }
                    }
                }
            }
            System.out.println("#" + (i+1) + " " + result);
        }

        sc.close();
	}
}