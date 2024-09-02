import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0}; //상하좌우
	static int[] dy = {0, 0, -1, 1};
	static ArrayList<Point> core;
	static int maxcore;
	static int minwire;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(bf.readLine().trim());
		for(int t=1; t<=T; t++) {
			maxcore = 0;
			minwire = Integer.MAX_VALUE;
			
			N = Integer.parseInt(bf.readLine().trim());
			map = new int[N][N];
			
			core = new ArrayList<>();
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(i!=0 && i!=N-1 && j!=0 && j!=N-1) {
						//가장자리 코어가 아니라면
						if(map[i][j] == 1) core.add(new Point(i, j));
					}
				}
			}
			
			dfs(0, 0, 0);
			System.out.println("#" + t + " " + minwire);
		}
	}

	private static void dfs(int idx, int core_cnt, int wire) {
		if(core.size() - idx + core_cnt < maxcore) {
			return;
		}
		
		if(idx == core.size()) {
			//모든 코어에 대해서 직선으로 전선 설치 완료
			if(core_cnt > maxcore) {
				maxcore = core_cnt;
				minwire = wire;
			}
			else if(core_cnt == maxcore) {
				if(wire < minwire) {
					minwire = wire;
				}
			}
			return;
		}
		
		//1. 상하좌우 이동
		//2. 각각 직선으로 이동해서 갈 수 있는 만큼 이동(map에 해당 정보 저장) 후 다음 코어 호출
		//3. map을 다시 원상태로 복구
		for(int i=0; i<4; i++) {
			int tx = core.get(idx).x; //idx: 선택한 코어의 인덱스
			int ty = core.get(idx).y;
			int cnt = 0;
			int w = 0; //설치한 전선 길이
			
			while(true) {
				tx += dx[i];
				ty += dy[i];
				
				if(map[tx][ty] == 1 || map[tx][ty] < 0) {
					//해당 위치에 코어나 전선이 존재한다.
					break;
				}
				
				if(tx==0 || tx==N-1 || ty==0 || ty==N-1) {
					//가장자리에 도착
					map[tx][ty] = -idx-1;
					cnt++; //core 전원 연결 성공
					w++;
					break;
				}
				
				map[tx][ty] = -idx-1; //해당 위치에 전선 놓기
				w++;
			}
			//직선으로 놓을 수 있는 모든 곳에 전선을 놓았으면 다음 core에서도 직선으로 연결해보기
			
			if(cnt == 0) {
				//map원상 복구하기
				for(int a=0; a<N; a++) {
					for (int b = 0; b < N; b++) {
						if(map[a][b] == -idx-1) {
							map[a][b] = 0;
						}
					}
				}
				
				dfs(idx+1, core_cnt, wire);
			}else {
				dfs(idx+1, core_cnt+cnt, wire+w);
				
				//map원상 복구하기
				for(int a=0; a<N; a++) {
					for (int b = 0; b < N; b++) {
						if(map[a][b] == -idx-1) {
							map[a][b] = 0;
						}
					}
				}
			}
		}
	}


}