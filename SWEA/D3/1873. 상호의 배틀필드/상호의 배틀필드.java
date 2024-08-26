import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int H;
	static int W;
	static char[][] map;
	static int N;
	static int sx; //시작 x위치
	static int sy; //시작 y위치
	static int sd; //바라보고 있는 방향
	static int[] dx = new int[]{-1, 1, 0, 0}; //상, 하, 좌, 우
	static int[] dy = new int[]{0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(bf.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(bf.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(bf.readLine());
				String tmp = st.nextToken();
				for(int j=0; j<W; j++) {
					map[i][j] = tmp.charAt(j);
					if(map[i][j] == '^') {
						sx = i; sy = j; sd = 0;
					}else if(map[i][j] == 'v') {
						sx = i; sy = j; sd = 1;
					}else if(map[i][j] == '<') {
						sx = i; sy = j; sd = 2;
					}else if(map[i][j] == '>') {
						sx = i; sy = j; sd = 3;
					}
				}
			}
			st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(bf.readLine());
			String move = st.nextToken();
			char cur;
			for(int i=0; i<N; i++) {
				cur = move.charAt(i); //현재 사용자가 입력한 입력 종류
				int idx = 0;
				if(cur == 'U') {
					idx = 0;
				}else if(cur == 'D') {
					idx = 1;
				}else if(cur == 'L') {
					idx = 2;
				}else if(cur == 'R') {
					idx = 3;
				}else {
					//S: shoot
					bfs(sx, sy);
				}
				
				if(cur != 'S') {
					int tx = sx + dx[idx];
					int ty = sy + dy[idx];
					
					//방향 바꾸기
					if(idx == 0) {
						sd = 0;
						map[sx][sy] = '^';
					}else if(idx == 1) {
						sd = 1;
						map[sx][sy] = 'v';
					}else if(idx == 2) {
						sd = 2;
						map[sx][sy] = '<';
					}else if(idx == 3) {
						sd = 3;
						map[sx][sy] = '>';
					}
					
					if(tx<0 || tx>=H || ty<0 || ty>=W) {
						continue;
					}
					
					if(map[tx][ty] == '.') {
						map[sx][sy] = '.';
						
						if(idx == 0) {
							map[tx][ty] = '^'; //전차 위치
						}else if(idx == 1) {
							map[tx][ty] = 'v'; //전차 위치
						}else if(idx == 2) {
							map[tx][ty] = '<'; //전차 위치
						}else if(idx == 3) {
							map[tx][ty] = '>'; //전차 위치
						}
						sx = tx;
						sy = ty;
					}
				}
			}
			
			System.out.print("#" + t + " ");
			for(char[] a : map) {
				for(char b : a) {
					System.out.print(b);
				}
				System.out.println();
			}
		}
	}

	private static void bfs(int sx, int sy) {
		//포탄 발사
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {sx, sy});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			int tx = cur[0] + dx[sd];
			int ty = cur[1] + dy[sd];
			
			if(tx<0 || tx>=H || ty<0 || ty>=W) {
				continue;
			}
			
			if(map[tx][ty] == '#') {
				continue;
			}
			
			if(map[tx][ty] == '*') {
				map[tx][ty] = '.';
				return;
			}
			
			q.offer(new int[] {tx, ty});
		}
	}

}