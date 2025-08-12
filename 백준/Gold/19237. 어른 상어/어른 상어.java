import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static class Shark{
        int x;
        int y;
        int direction;

        public Shark(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }

    static class Smell{
        int s; // 상어 번호
        int remain; // 남은 잔향

        public Smell(int s, int remain) {
            this.s = s;
            this.remain = remain;
        }
    }

    static int N, M, K;
    static int[][] map;
    static int[][] map2;
    static Shark[] shark;
    static Smell[][] smell;
    static int[] dx = {0, -1, 1, 0, 0}; // 1번 idx부터 위, 아래, 왼쪽, 오른쪽
    static int[] dy = {0, 0, 0, -1, 1};
    static int cnt = 0; // 남아있는 상어의 수

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        map2 = new int[N][N];
        shark = new Shark[M+1];
        smell = new Smell[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < N; j++){
                int tmp = Integer.parseInt(st.nextToken());
                map[i][j] = tmp;
                if(tmp > 0){
                    // 상어가 있는 곳
                    shark[tmp] = new Shark(i, j, -1);
                    smell[i][j] = new Smell(tmp, K);
                    cnt++;
                }
            }
        }

        // 각 상어의 방향
        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < M; i++){
            shark[i+1].direction = Integer.parseInt(st.nextToken());
        }

        int[][] up = new int[M+1][4];
        int[][] down = new int[M+1][4];
        int[][] left = new int[M+1][4];
        int[][] right = new int[M+1][4];
        for(int i = 1; i <= M; i++){
            // 각 상어 당 방향이 4줄씩 주어진다.
            // 위, 아래, 왼쪽, 오른쪽
            for(int j = 0; j < 4; j++){
                st = new StringTokenizer(bf.readLine());
                for(int k = 0; k < 4; k++){
                    if(j==0){
                        up[i][k] = Integer.parseInt(st.nextToken());
                    }else if(j==1){
                        down[i][k] = Integer.parseInt(st.nextToken());
                    }else if(j==2){
                        left[i][k] = Integer.parseInt(st.nextToken());
                    }else{
                        right[i][k] = Integer.parseInt(st.nextToken());
                    }

                }

            }
        }

        int time = 0;
        while(time < 1000 && cnt > 1){
            // 1000초까지 실행하거나, 상어가 1마리 이상일 때까지만 실행하거나
            // 1. 각 상어 별 이동
                // 인접한 칸 중 아무 냄새가 없는 칸이 우선순위
                // 만일 모든 인접칸에 냄새가 있다면 자신의 냄새가 있는 칸으로 이동
                // 다른 상어와 겹친다면 가장 작은 번호의 상어만 남기기
                    // 쫓겨난 애는 Shark null처리, cnt--;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map2[i][j] = 0;
                }
            }

            for(int i=0; i<N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] > 0) {
                        // 상어가 있다면
                        int s = map[i][j]; // 상어 번호

                        if (shark[s] == null) continue; // 이미 제거된 상어면 패스

                        if (shark[s].direction == 1) {
                            // 위를 향함
                            move(i, j, up);

                        } else if (shark[s].direction == 2) {
                            // 아래를 향함
                            move(i, j, down);

                        } else if (shark[s].direction == 3) {
                            // 왼쪽을 향함
                            move(i, j, left);

                        } else {
                            // 오른쪽을 향함
                            move(i, j, right);

                        }
                    }
                }
            }
            
            // 2. map 다시 만들기
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    map[i][j] = map2[i][j];
                }
            }
            
            // 3. 냄새 감소 시키기
                // 만일 smell.remain가 0이 된다면 해당 배열 값은 null로
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(smell[i][j] != null){
                        smell[i][j].remain = smell[i][j].remain - 1;
                        if(smell[i][j].remain == 0){
                            smell[i][j] = null;
                        }
                    }
                }
            }

            // 4. 새로운 상어 위치에 냄새 넣기
            for(int i=1; i<=M; i++){
                if(shark[i] != null){
                    smell[shark[i].x][shark[i].y] = new Smell(i, K);
                }
            }

            time++;
        }
        if(cnt > 1){
            System.out.println(-1);
        }else{
            System.out.println(time);
        }

    }

    public static void move(int x, int y, int[][] direc){
        int s = map[x][y]; // 상어 번호

        boolean flag = false;
        for(int d=0; d<4; d++){
            int tx = x + dx[direc[s][d]];
            int ty = y + dy[direc[s][d]];

            if(tx<0 || ty<0 || tx>=N || ty>=N){
                continue;
            }
            if(smell[tx][ty] != null){
                // 상어의 냄새가 있는 곳
                continue;
            }

            if(map2[tx][ty] > 0) {
                // 해당 위치에 상어가 있다면 번호가 작은 애가 자리를 차지
                if (s < map2[tx][ty]) {
                    shark[map2[tx][ty]] = null;
                    map2[tx][ty] = s;

                    shark[s].x = tx;
                    shark[s].y = ty;
                    shark[s].direction = direc[s][d];

                    cnt--; // 상어 수 한마리 감소
                    flag = true;

                } else {
                    shark[s] = null; // 현재 선택된 상어 삭제
                    cnt--; // 상어 수 한마리 감소

                    flag = true;
                }
                break;
            }

            // 위 세 조건 통과 -> 해당 위치로 이동 가능
            map2[tx][ty] = s;

            shark[s].x = tx;
            shark[s].y = ty;
            shark[s].direction = direc[s][d];

            flag = true;
            break;
        }
        if(!flag){
            // 위에서 이동할 위치를 찾지 못했다면 내 냄새가 있는 곳으로 이동
            for(int d=0; d<4; d++) {
                int tx = x + dx[direc[s][d]];
                int ty = y + dy[direc[s][d]];

                if(tx<0 || ty<0 || tx>=N || ty>=N){
                    continue;
                }
                if(smell[tx][ty].s == s){
                    // 내 냄새인 칸
                    if (map2[tx][ty] > 0) {
                        // 같은 칸을 노린 상어 사이의 충돌 처리 (자기 냄새 칸이라도 동일)
                        if (s < map2[tx][ty]) {
                            shark[map2[tx][ty]] = null; // 기존 상어 제거
                            map2[tx][ty] = s;

                            shark[s].x = tx;
                            shark[s].y = ty;
                            shark[s].direction = direc[s][d];

                            cnt--; // 상어 한마리 감소
                        } else {
                            shark[s] = null; // 현재 선택된 상어 삭제
                            cnt--; // 상어 수 한마리 감소
                        }
                    } else {
                        // 빈 칸이면 정상 이동
                        map2[tx][ty] = s;

                        shark[s].x = tx;
                        shark[s].y = ty;
                        shark[s].direction = direc[s][d];
                    }
                    break;
                }
            }
        }
    }
}
