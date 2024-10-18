import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine().trim());
        //Set<String> word = new HashSet<>();
        String[] word = new String[N];
        for (int i = 0; i < N; i++) {
            word[i] = bf.readLine().trim();
        }

        int cnt = 0;
        String first = "";
        String second = "";

        for (int i = 0; i < N-1; i++) {
            //word.add(bf.readLine().trim());
            //String next = bf.readLine().trim();
            //System.out.println("================" + next + "================");

            for (int j = i+1; j < N; j++) {
                int tmp_cnt = 0;

                int len = Math.min(word[i].length(), word[j].length());
                for (int k = 0; k < len; k++) {
                    if(word[i].charAt(k) == word[j].charAt(k)){
                        tmp_cnt++;

                    }else{
                        break;
                    }
                }

                if(cnt < tmp_cnt){
                    //현재 값보다 일치하는 문자의 수가 더 길다면
                    cnt = tmp_cnt;
                    first = word[i];
                    second = word[j];
                }
            }
        }

        System.out.println(first);
        System.out.println(second);
    }
}