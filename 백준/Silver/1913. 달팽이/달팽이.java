import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        int num = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][N];
        int x = N / 2; //시작좌표 구하기
        int y = N / 2;
        int tx = 0, ty = 0; //이동 전 좌표를 저장하는 변수

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int idx = 0;
        int tidx = 0; //이동 전 idx를 저장하는 변수

        int cnt = 1;
        arr[x][y] = cnt++;

        for(int i=1; i<(N*N); i++){
            x = x + dx[idx]; //상우하왼 순으로 좌표를 변경한다.
            y = y + dy[idx];

            if(arr[x][y] != 0){
                //해당 위치에 값을 넣은 적이 있다면 (이전의 이동 방식대로) 한번 더 이동한다.
                while (arr[x][y] != 0){
                    x = tx;
                    y = ty;
                    idx = tidx;
                    x = x + dx[idx];
                    y = y + dy[idx];
                }
            }

            if(x >= 0 && x < N && y >= 0 && y < N){
                arr[x][y] = cnt++; // 범위 내라면 값을 넣어준다.
            }

            tidx = idx;
            idx++;
            tx = x;
            ty = y;
            if(idx > 3){
                idx = 0;
            }
        }

        int rx = 0;
        int ry = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(arr[i][j] == num){
                    rx = i;
                    ry = j;
                }
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.print((rx+1) + " ");
        System.out.println(ry+1);
    }
}