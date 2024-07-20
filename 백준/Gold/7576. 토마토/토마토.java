import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int arr[][];
	static boolean visited[][];
	static ArrayList<int[]> tomato = new ArrayList<>(); //익은 토마토
	static int[] dx = {-1, 1, 0, 0}; //상하좌우
	static int[] dy = {0, 0, -1, 1};
	static int M;
	static int N;
	static Queue<int[]> q = new LinkedList<>();
	
	public static void main(String[] args) throws IOException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        
        arr = new int[N][M];
        visited = new boolean[N][M];        
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(bf.readLine());
        	for(int j=0; j<M; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        for(int i=0; i<N; i++) {
        	for(int j=0; j<M; j++) {
        		if(arr[i][j] == 1) {
        			tomato.add(new int[]{i,j});
        		}
        	}
        }
        
        for(int i=0; i<tomato.size(); i++) {
        	q.offer(new int[]{tomato.get(i)[0],tomato.get(i)[1],0});
        }
        bfs();
	}
	
	public static void bfs() {
        int day = 0;
        
        while(!q.isEmpty()) {
        	int[] cur = q.poll();
        	visited[cur[0]][cur[1]] = true;
        	day = cur[2];
        	
        	for(int i=0; i<4; i++) {
        		int tx = cur[0] + dx[i];
        		int ty = cur[1] + dy[i];
        		
        		if(tx<0 || tx>=N || ty<0 || ty>=M) {
        			//범위를 넘는다면
        			continue;
        		}
        		if(arr[tx][ty] == 1 || arr[tx][ty] == -1) {
        			//익은 토마토거나 토마토가 없다면
        			continue;
        		}
        		if(visited[tx][ty]) {
        			continue;
        		}
        		
        		arr[tx][ty] = 1;
        		visited[tx][ty] = true;
        		q.add(new int[]{tx, ty, day+1});
        	}
        }
        
        boolean c = check();
        if(c) {
        	System.out.println(day);
        }else {
        	System.out.println(-1+"");
        }
        
	}
	
	public static boolean check() {
		for(int i=0; i<N; i++) {
        	for(int j=0; j<M; j++) {
        		if(arr[i][j] == 0) {
        			//익지 않은 토마토가 있다면
        			return false;
        		}
        	}
		}
		//전부 익었다면
		return true;
	}
}