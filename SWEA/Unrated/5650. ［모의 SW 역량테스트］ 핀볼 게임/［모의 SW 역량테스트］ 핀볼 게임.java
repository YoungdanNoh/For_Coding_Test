import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static int[] dx = {-1, 0, 1, 0}; //위 오 아 왼
    static int[] dy = {0, 1, 0, -1};
    static ArrayList<Point>[] worm;
    static int N;
    static int[][] map;
    static int direc;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(bf.readLine().trim());

        for (int t = 1; t <= T ; t++) {
            ans = 0;

            worm = new ArrayList[11];
            for (int i = 0; i < 11; i++) {
                worm[i] = new ArrayList<>();
            }

            N = Integer.parseInt(bf.readLine().trim());
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < N; j++) {
                    int tmp = Integer.parseInt(st.nextToken());
                    map[i][j] = tmp;
                    if(tmp>5){
                        //웜홀이라면
                        worm[tmp].add(new Point(i, j));
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(map[i][j] == 0){
                        //빈공간이라면 해당 위치에서 시작해 4방으로 탐색 시작
                        for (int k = 0; k < 4; k++) {
                            direc = k;
                            bfs(i, j);
                        }
                    }
                }
            }
            //bfs(2, 1);
            System.out.println("#" + t + " " + ans);
        }
    }

    private static void bfs(int sx, int sy) {
        int score = 0;

        int tx = sx + dx[direc];
        int ty = sy + dy[direc];

        while (true){

            if(tx<0 || tx>=N || ty<0 || ty>=N){
                //벽을 만나면 반대로 방향 전환
                score++;
                opposite();
                tx = tx + dx[direc];
                ty = ty + dy[direc];

            }else if(map[tx][ty] == -1 || ((tx == sx) && (ty == sy))){
                //핀볼이 출발 위치로 돌아오거나, 블랙홀에 빠질 때 끝
                ans = Math.max(ans, score);
                break;

            }else if (map[tx][ty] > 0 && map[tx][ty] < 6) {
                //블록이 있는 위치라면
                score++;

                switch (map[tx][ty]) {
                    case 1:
                        if(direc==2){
                            //현재 방향이 아래라면
                            direc-=1;
                        }else if(direc == 3){
                            direc-=3;
                        }else{
                            opposite();
                        }
                        tx = tx + dx[direc];
                        ty = ty + dy[direc];
                        break;
                    case 2:
                        if(direc==0){
                            direc+=1;
                        } else if (direc == 3) {
                            direc-=1;
                        }else{
                            opposite();
                        }
                        tx = tx + dx[direc];
                        ty = ty + dy[direc];
                        break;
                    case 3:
                        if(direc==1){
                            direc+=1;
                        } else if (direc==0) {
                            direc+=3;
                        }else {
                            opposite();
                        }
                        tx = tx + dx[direc];
                        ty = ty + dy[direc];
                        break;
                    case 4:
                        if(direc==1){
                            direc-=1;
                        } else if (direc==2) {
                            direc+=1;
                        }else {
                            opposite();
                        }
                        tx = tx + dx[direc];
                        ty = ty + dy[direc];
                        break;
                    case 5:
                        opposite();
                        tx = tx + dx[direc];
                        ty = ty + dy[direc];
                        break;
                }

            } else if (map[tx][ty] > 5) {
                //웜홀일 때
                int idx = map[tx][ty];

                for (int i = 0; i < 2; i++) {
                    if(worm[idx].get(i).x != tx || worm[idx].get(i).y != ty){
                        //같은 번호인 다른 웜홀로 이동
                        tx = worm[idx].get(i).x;
                        ty = worm[idx].get(i).y;
                        break;
                    }
                }
                tx = tx + dx[direc];
                ty = ty + dy[direc];

            } else {
                //그냥 바닥
                tx = tx + dx[direc];
                ty = ty + dy[direc];
            }
        }
    }

    static void opposite(){
        direc += 2;
        if(direc > 3){
            direc = direc - 4;
        }
    }
}