import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringWriter sw = new StringWriter();

        int N = Integer.parseInt(bf.readLine());

        int n = (int) Math.round((Math.log(N) / Math.log(3)));
//        System.out.println(n);
//        System.out.println(Math.log(N) / Math.log(3));
        int[][] ans = new int[N][N];

        char[][] pattern = new char[][] {{'*', '*', '*'},
                                         {'*', ' ', '*'},
                                         {'*', '*', '*'}};

        int p = 3;

        for (int i = 1; i < n; i++) {
            int idx = p;
            p *= 3;
            char[][] p_tmp = new char[p][p];

            for (int j = 0; j < p; j++) {
                for (int k = 0; k < p; k++) {
                    if(j>=idx && j<idx*2 && k>=idx && k<idx*2){
                        p_tmp[j][k] = ' ';
                    }else{
                        p_tmp[j][k] = pattern[j%idx][k%idx];
                    }

                }
            }

            pattern = new char[p][p];
            for (int j = 0; j < p; j++) {
                for (int k = 0; k < p; k++) {
                    pattern[j][k] = p_tmp[j][k];
                }
            }

        }

        for (int i = 0; i < p; i++) {
            for (int j = 0; j < p; j++) {
                sw.append(pattern[i][j]);
                if(j==p-1) sw.append('\n');
            }
        }

        System.out.println(sw);
    }

}