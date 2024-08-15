import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(bf.readLine());
        int tc = Integer.parseInt(st.nextToken());

        int[] dx = {0, -1, 1, 0, 0};
        int[] dy = {0, 0, 0, -1, 1}; //순서대로 상하좌우
        for(int t=1; t<=tc; t++){
            st = new StringTokenizer(bf.readLine());
            int N = Integer.parseInt(st.nextToken()); //한 변에 있는 셀의 개수
            int M = Integer.parseInt(st.nextToken()); //격리시간. 이 M 시간 후 남아있는 미생물 수의 총 합을 구해야 한다.
            int K = Integer.parseInt(st.nextToken()); //미생물 군집의 개수

            //int[][] micro = new int[K][4];
            ArrayList<ArrayList<Integer>> micro = new ArrayList<>();

            for(int k = 0; k<K; k++){
                st = new StringTokenizer(bf.readLine());
                int x = Integer.parseInt(st.nextToken()); //세로위치
                int y = Integer.parseInt(st.nextToken()); //가로위치
                int n = Integer.parseInt(st.nextToken()); //미생물 수
                int d = Integer.parseInt(st.nextToken()); //이동방향
                ArrayList<Integer> tmp = new ArrayList<>(Arrays.asList(x, y, n, d));
                micro.add(tmp);
                // 상:1, 하:2, 좌:3, 우:4
            }

            for(int m=0; m<M; m++){
                //M시간 동안 이동
                //1. 이동 후 위치 및 미생물 수, 방향을 배열(micro)에 저장. { {0,0,1} }
                for(int k = 0; k<micro.size(); k++){
                    micro.get(k).set(0, micro.get(k).get(0) + dx[micro.get(k).get(3)]);
                    micro.get(k).set(1, micro.get(k).get(1) + dy[micro.get(k).get(3)]);
                    
                    if(micro.get(k).get(0)==0 || micro.get(k).get(0)==(N-1) || micro.get(k).get(1)==0 || micro.get(k).get(1)==(N-1)){
                        //약품이 칠해진 셀에 도착하게 된다면 이동방향 반대로 + 미생물 수 반절
                        micro.get(k).set(2, micro.get(k).get(2)/2);

                        if(micro.get(k).get(3)==2 || micro.get(k).get(3)==4){
                            micro.get(k).set(3, micro.get(k).get(3)-1);
                        }else {
                            micro.get(k).set(3, micro.get(k).get(3)+1);
                        }
                    }
                }
                //2. 모든 미생물 이동 후 배열의 값들을 적절히 더하여 미생물 배열을 업데이트
                //Arrays.sort(micro, (a, b) -> (a[0] == b[0] && a[1] == b[1]) ? b[2] - a[2] : a[0] - b[0]);
                Collections.sort(micro, (a, b) -> {
                    if (a.get(0) != b.get(0)) {
                        return a.get(0) - b.get(0);
                    } else if (a.get(1) != b.get(1)) {
                        return a.get(1) - b.get(1);
                    } else {
                        return b.get(2) - a.get(2);
                    }
                });
                //미생물의 x, y 위치가 동일하다면 미생물 수로 내림차순 정렬한다.

                int i = 1;
                while(i < micro.size()){
                    if(micro.get(i).get(0) == micro.get(i-1).get(0) && micro.get(i).get(1) == micro.get(i-1).get(1)){
                        //x, y 위치가 동일하다면 업데이트
                        micro.get(i-1).set(2, micro.get(i-1).get(2) + micro.get(i).get(2));
                        micro.remove(i); //i번째 행 제거
                    }else{
                        i++;
                    }
                }
            }

            int ans = 0;
            for(int k = 0; k<micro.size(); k++){
                ans += micro.get(k).get(2);
            }
            System.out.println("#" + t + " " + ans);
        }
    }
}