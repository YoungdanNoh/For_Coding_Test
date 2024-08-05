import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        int[] block = new int[W];

        for(int i=0; i<W; i++){
            block[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        for(int i=1; i<W; i++){
            int[] left = Arrays.copyOfRange(block, 0, i);
            int[] right = Arrays.copyOfRange(block, i, W);

            int lm = Arrays.stream(left).max().getAsInt();
            int rm = Arrays.stream(right).max().getAsInt();

            int min = Math.min(lm, rm);

            if(min > block[i]){
                result += (min - block[i]);
            }
        }
        System.out.println(result);
    }
}