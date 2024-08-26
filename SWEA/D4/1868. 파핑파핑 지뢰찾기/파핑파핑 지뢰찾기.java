import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static char[][] map;
	static boolean[][] visited;
	static int ans;
	static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1}; //위왼, 위, 위오, 왼, 오, 아왼, 아, 아오
	static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(bf.readLine().trim());
		
		for(int t=1; t<=T; t++) {
			ans = 0;
			
			N = Integer.parseInt(bf.readLine().trim());
			map = new char[N][N];
			visited = new boolean[N][N];
			
			for(int i=0; i<N; i++) {
				String tmp = bf.readLine().trim();
				for(int j=0; j<N; j++) {
					map[i][j] = tmp.charAt(j);
				}
			}
			
			//주변 폭탄의 갯수가 0인 곳부터 먼저 클릭
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(count(i, j) == 0 && map[i][j] != '*' && visited[i][j] == false) {
						//System.out.println("aa");
						ans++;
						bfs(i, j);
					}
				}
			}
			
			
			//위에서 클릭하여 탐색한 후에도 클릭이 안 된 칸 탐색
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == '.') {
						map[i][j] = (char) (count(i, j) + '0');
						visited[i][j] = true;
						ans++;
					}
				}
			}
			System.out.println("#"+t+" "+ans);
		}
	}

	private static int count(int x, int y) {
		int cnt = 0;
		for(int i=0; i<8; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			
			if(tx<0 || tx>=N || ty<0 || ty>=N) continue;
			
			if(map[tx][ty] == '*') cnt++;
		}
		
		return cnt;
	}
	
	private static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x, y});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cnt = count(cur[0], cur[1]);
			map[cur[0]][cur[1]] = (char) (cnt + '0');
			if(cnt > 0) continue;
			
			for(int i=0; i<8; i++) {
				int tx = cur[0] + dx[i];
				int ty = cur[1] + dy[i];
				
				if(tx<0 || tx>=N || ty<0 || ty>=N) continue;
				
				if(visited[tx][ty]) continue;
				
				if(map[tx][ty] != '*') {
					visited[tx][ty] = true;
					q.offer(new int[] {tx, ty});
				}
			}
		}
	}
}