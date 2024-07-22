import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int M;
	static int N;
	static int[][] arr;
	static boolean[][][] visited; //방문한 곳
	
	//동서남북
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static int sx; //시작 x
	static int sy; //시작 y
	static int sd; //시작 direction
	
	static int ex; //목표 x
	static int ey; //목표 y
	static int ed; //목표 direction
	static int result = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(bf.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		arr = new int[M][N];
		visited = new boolean[M][N][4];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(bf.readLine());
		sx = Integer.parseInt(st.nextToken()) - 1;
		sy = Integer.parseInt(st.nextToken()) - 1;
		sd = Integer.parseInt(st.nextToken()) - 1;
		
		st = new StringTokenizer(bf.readLine());
		ex = Integer.parseInt(st.nextToken()) - 1;
		ey = Integer.parseInt(st.nextToken()) - 1;
		ed = Integer.parseInt(st.nextToken()) - 1;
		
		bfs();
		System.out.println(result);
	}

	public static void bfs() {
		//visited[sx][sy][sd] = true; //방문함
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[]{sx, sy, sd, 0});
		int tx = 0;
		int ty = 0;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			visited[cur[0]][cur[1]][cur[2]] = true; //방문함
			
			//System.out.println("aa");
			//System.out.println(cur[3]);
			
//			if(visited[cur[0]][cur[1]][cur[2]]) {
//				continue;
//			}
			
			if(cur[0] == ex && cur[1] == ey && cur[2] == ed) {
				result = cur[3];
				return;
			}
			
			//왼쪽 회전
			//visited[cur[0]][cur[1]][left(cur[2])] = true;
			if(!visited[cur[0]][cur[1]][left(cur[2])]) {
				visited[cur[0]][cur[1]][left(cur[2])] = true;
				q.offer(new int[] {cur[0], cur[1], left(cur[2]), cur[3]+1});
			}
			//오른쪽 회전
			if(!visited[cur[0]][cur[1]][right(cur[2])]) {
				visited[cur[0]][cur[1]][right(cur[2])] = true;
				q.offer(new int[] {cur[0], cur[1], right(cur[2]), cur[3]+1});
			}
			//System.out.println(left(cur[2]));
			
			//오른쪽 회전
			//visited[cur[0]][cur[1]][right(cur[2])] = true;
			
			
			for(int i=1; i<=3; i++) {
				//직진일 때
				tx = cur[0] + dx[cur[2]]*i;
				ty = cur[1] + dy[cur[2]]*i;
				
				if(tx<0 || tx>=M || ty<0 || ty>=N) {
					break; //범위를 넘는다면 더 이상 진행할 필요x
				}
				if(arr[tx][ty] == 1) {
					break; //이동할 수 없는 지점이 나온다면 더 이상 진행할 필요x
				}
				if(visited[tx][ty][cur[2]]) {
					continue;
				}
				
				visited[tx][ty][cur[2]] = true;
				q.offer(new int[] {tx, ty, cur[2], cur[3]+1});
			}
		}
	}
	public static int left(int d) {
		if(d == 0) {
			return 3;
		}else if(d == 3) {
			return 1;
		}else if(d == 1) {
			return 2;
		}else {
			return 0;
		}
	}
	
	public static int right(int d) {
		if(d == 0) {
			return 2;
		}else if(d == 2) {
			return 1;
		}else if(d == 1) {
			return 3;
		}else {
			return 0;
		}
	}
}
