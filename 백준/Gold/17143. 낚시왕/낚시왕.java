import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class jaws implements Comparable<jaws>{
    int x, y;
    int s; //속력
    int d; //방향
    int z; //크기

    public jaws(int x, int y, int s, int d, int z) {
        this.x = x;
        this.y = y;
        this.s = s;
        this.d = d;
        this.z = z;


    }

    @Override
    public int compareTo(jaws o) {
        if(this.x != o.x){
            return this.x - o.x;
        }else{
            return this.y - o.y;
        }
    }
}

public class Main {
    static int R;
    static int C;
    static int M;
    //static jaws[] jaws;
    static ArrayList<jaws> jaws = new ArrayList<>();
    static int[] dx = {0, -1, 1, 0, 0}; //위 아 오 왼
    static int[] dy = {0, 0, 0, 1, -1};
    static int person = 0; //사람의 위치
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine().trim());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //jaws = new jaws[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine().trim());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            jaws.add(new jaws(x, y, s, d, z));
        }

        for (int i = 0; i < C; i++) {
            //사람은 c번 이동한다.
            person++;
            Collections.sort(jaws);

            for (int j = 0; j < jaws.size(); j++) {
                if(jaws.get(j).y == person) {
                    ans += jaws.get(j).z;
                    jaws.remove(j);
                    break;
                }
            }

            for(jaws j: jaws) {

                int d = j.d;
                int p = 0; //현재 위치
                int max = 0;
                int s = j.s; //현재 이동해야 하는 칸 수

                if(d == 1 || d == 2) {
                    //위아래 이동
                    p = j.x;
                    max = R;
                }else {
                    p = j.y;
                    max = C;
                }

                while(s > 0) {
                    //System.out.println(s);
                    if(d == 1) {
                        //위
                        int tmp = p - 1; //끝으로 이동 가능한 칸 수
                        if(s > tmp) {
                            s -= tmp;
                            d = 2;
                            p = 1;

                        }else {
                            p -= s;
                            s = 0;
                        }

                    }else if(d == 2) {
                        //아
                        int tmp = max - p; //끝으로 이동 가능한 칸 수
                        if(s > tmp) {
                            s -= tmp;
                            d = 1;
                            p = max;

                        }else {
                            p += s;
                            s = 0;
                        }


                    }else if(d == 3) {
                        //오
                        int tmp = max - p; //끝으로 이동 가능한 칸 수
                        if(s > tmp) {
                            s -= tmp;
                            d = 4;
                            p = max;

                        }else {
                            p += s;
                            s = 0;
                        }

                    }else {
                        //왼
                        int tmp = p - 1; //끝으로 이동 가능한 칸 수
                        if(s > tmp) {
                            s -= tmp;
                            d = 3;
                            p = 1;

                        }else {
                            p -= s;
                            s = 0;
                        }
                    }
                }

                j.d = d;
                if(d == 1 || d == 2) {
                    j.x = p;
                }else {
                    j.y = p;
                }

            }

            Collections.sort(jaws);

            for (int j = jaws.size()-1; j > 0; j--) {
                if(jaws.get(j).x == jaws.get(j-1).x && jaws.get(j).y == jaws.get(j-1).y){
                    //같은 위치라면
                    if(jaws.get(j).z > jaws.get(j-1).z){
                        jaws.remove(j-1);
                    }else{
                        jaws.remove(j);
                    }
                }
            }

        }
        System.out.println(ans);

    }

}