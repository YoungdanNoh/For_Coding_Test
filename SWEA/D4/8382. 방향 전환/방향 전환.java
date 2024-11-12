import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Solution {
	static int[][] map;
	static Point p1, p2;
	static int[] dx = {-1, 0, 1, 0}; //상, 좌, 하, 우
	static int[] dy = {0, -1, 0, 1};
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(bf.readLine().trim());
		for (int t = 1; t <= T; t++) {
			map = new int[201][201];
			st = new StringTokenizer(bf.readLine());
			p1 = new Point(Integer.parseInt(st.nextToken())+100, Integer.parseInt(st.nextToken())+100);
			p2 = new Point(Integer.parseInt(st.nextToken())+100, Integer.parseInt(st.nextToken())+100);
			
			if(p1.equals(p2)) {
				System.out.println("#"+t+" "+0);
			}else {
				ans = 0;
				bfs(p1.x, p1.y);
				System.out.println("#"+t+" "+ans);
			}
		}
	}

	private static void bfs(int x, int y) {
		boolean[][][] visited = new boolean[201][201][4];
		
		Queue<int[]> q = new LinkedList<>();
		for (int i = 0; i < 4; i++) {
			q.offer(new int[] {x, y, i, 0});
			visited[x][y][i] = true;
		}
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			//System.out.println(cur[2]);
			int tx = cur[0] + dx[cur[2]];
			int ty = cur[1] + dy[cur[2]];
			//System.out.println("tx: " + tx + ", ty:" + ty);
			
			if(tx<0 || tx>=201 || ty<0 || ty>=201) {
				continue;
			}
//			if(visited[tx][ty][cur[2]]) {
//				continue;
//			}
			if(tx == p2.x && ty == p2.y) {
				//System.out.println("bbb");
				ans = cur[3]+1;
				return;
			}
			
			if(cur[2]%2 == 0) {
				//좌우로 이동
				if(!visited[tx][ty][1]) {
					//System.out.println("aaa");
					q.offer(new int[] {tx, ty, 1, cur[3]+1});
					visited[tx][ty][1] = true;
				}
				if(!visited[tx][ty][3]) {
					q.offer(new int[] {tx, ty, 3, cur[3]+1});
					visited[tx][ty][3] = true;
				}
				//System.out.println("aaaa");
			}else {
				//상하로 이동
				if(!visited[tx][ty][0]) {
					q.offer(new int[] {tx, ty, 0, cur[3]+1});
					visited[tx][ty][0] = true;
				}
				if(!visited[tx][ty][2]) {
					q.offer(new int[] {tx, ty, 2, cur[3]+1});
					visited[tx][ty][2] = true;
				}
				//System.out.println("bbbb");
			}
		}
	}

}