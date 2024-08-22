import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int[][] arr;
	static int[] dx = new int[] {-1, 1, 0, 0}; //상하좌우
	static int[] dy = new int[] {0, 0, -1, 1};
	static boolean[][] visited;
	static int temp;
	static int result_j;
	static int room;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(bf.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=T; t++) {
			result_j = 10*6;
			room = -1;
			temp = 1;
			
			st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			
			arr = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(bf.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					//각각의 방에 대해 bfs 수행
					temp = 1;
					visited = new boolean[N][N];
					bfs(i, j);
					
					if(room == temp) {
						if(arr[i][j] < result_j) {
							//현재 출발 방 번호보다 작다면 변경
							result_j = arr[i][j];
						}
					}else if(room < temp) {
						//현재 방문한 방보다 더 많거나 같은 방을 방문할 수 있다면
						result_j = arr[i][j];
						room = temp;
					}
				}
			}
			System.out.println("#" + t + " " + result_j + " " + room);
		}
	}
	private static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x, y});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			visited[cur[0]][cur[1]] = true;
			
			for(int i=0; i<4; i++) {
				int tx = cur[0] + dx[i];
				int ty = cur[1] + dy[i];
				
				if(tx<0 || tx>=N || ty<0 || ty>=N ) {
					//범위를 벗어난다면
					continue;
				}
				if(visited[tx][ty]) {
					continue;
				}
				if(Math.abs(arr[cur[0]][cur[1]] - arr[tx][ty]) != 1) {
					//1 차이가 나는 것이 아니라면
					continue;
				}
				temp++;
				q.add(new int[] {tx, ty});
			}
		}
		
	}

}