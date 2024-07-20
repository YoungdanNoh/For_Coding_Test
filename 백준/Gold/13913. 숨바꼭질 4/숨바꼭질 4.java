import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N; //수빈이 위치
    static int K; //동생 위치
    //static ArrayList<Integer> road = new ArrayList<>(); //탐색 경로 저장
    static int[] visited = new int[100001];
    static int[] road = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int result = bfs();
        System.out.println(result); //최소 시간 출력

        int[] printRoad = new int[result+1]; //경로 출력하기 위한 변수
        printRoad[result] = K;

        int temp = K;
        for(int i=result-1; i>=0; i--){
            //System.out.println(road[temp]);
            printRoad[i] = road[temp];
            temp = road[temp];
        }

        for(int i=0; i<result+1; i++){
            System.out.print(printRoad[i]+" ");
        }
    }

    public static int bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.offer(N);

        while (!q.isEmpty()){
            int cur = q.poll(); //탐색 시작 위치

            if(K == cur){
                //동생을 찾았다면 종료
                return visited[cur];
            }

            for(int i=0; i<3; i++){
                if(i==0){
                    //-1 이동
                    if((cur-1) >= 0 && (cur-1) <= 100000 && visited[cur-1] ==0){
                        q.offer(cur-1);
                        visited[cur-1] = visited[cur]+1;
                        road[cur-1] = cur; //이전 위치 저장
                    }
                }
                if(i==1){
                    //+1 이동
                    if((cur+1) >= 0 && (cur+1) <= 100000 && visited[cur+1] ==0){
                        q.offer(cur+1);
                        visited[cur+1] = visited[cur]+1;
                        road[cur+1] = cur; //이전 위치 저장
                    }
                }
                if(i==2){
                    //*2 이동
                    if((cur*2) >= 0 && (cur*2) <= 100000 && visited[cur*2] ==0){
                        q.offer(cur*2);
                        visited[cur*2] = visited[cur]+1;
                        road[cur*2] = cur; //이전 위치 저장
                    }
                }
            }
        }

        return -1;
    }
}