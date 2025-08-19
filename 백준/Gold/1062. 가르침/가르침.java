import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int K;
    static String[] words;
    static char[] candi;
    static boolean[] selected = new boolean[26];
    static int ans = 0;

    public static void combi(int cnt, int start){
        if(cnt == K){
            int read = 0; // 읽을 수 있는 단어의 수

            for(int i=0; i<words.length; i++){
                char[] tmp = words[i].toCharArray();

                boolean flag = true;
                for(int j=0; j<tmp.length; j++){
                    if(!selected[tmp[j]-97]){
                        // 해당 단어는 읽을 수 없음
                        flag = false;
                        break;
                    }
                }

                if(flag) {
                    read++;
                }
            }

            ans = Math.max(ans, read);

            return;
        }

        for(int i = start; i < candi.length; i++){
            selected[candi[i] - 97] = true;
            combi(cnt+1, i+1);

            selected[candi[i] - 97] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 'a' = 97
        // 'a', 'n', 't', 'i', 'c' 는 무조건 알아야 함
        selected[(int)'a' - 97] = true;
        selected[(int)'n' - 97] = true;
        selected[(int)'t' - 97] = true;
        selected[(int)'i' - 97] = true;
        selected[(int)'c' - 97] = true;

        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if(K < 5){
            // 학생들이 읽을 수 있는 단어는 없음
            System.out.println(0);

        }else{
            K -= 5; // K만큼의 알파벳을 더 가르칠 수 있음

            words = new String[N];

            Set<Character> tmp = new HashSet<>();

            for(int i = 0; i < N; i++){
                words[i] = bf.readLine();
                char[] s = words[i].toCharArray();

                for(int j = 4; j < s.length-4; j++){
                    if(s[j] != 'a' && s[j] != 'n' && s[j] != 't'
                        && s[j] != 'i' && s[j] != 'c'){
                        tmp.add(s[j]);
                    }
                }
            }

            if(tmp.size() <= K){
                System.out.println(N);

            }else{
                candi = new char[tmp.size()];
                int idx = 0;
                for(char c : tmp){
                    candi[idx++] = c;
                }

                combi(0, 0);

                System.out.println(ans);
            }

        }
    }
}
