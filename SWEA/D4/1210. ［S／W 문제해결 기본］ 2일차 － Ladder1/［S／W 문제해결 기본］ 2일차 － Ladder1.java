import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[][] arr = new int[100][100];
	//위, 오, 왼, 아 순서
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, 1, -1, 0};
		
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int t=1; t<=10; t++) {
			//총 10번의 테케
			st = new StringTokenizer(bf.readLine());
			int tc = Integer.parseInt(st.nextToken());
			for(int i=0; i<100; i++) {
				st = new StringTokenizer(bf.readLine());
				for(int j=0; j<100; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<100; i++) {
				//1. 0행에서 1인 값 찾기
				if(arr[99][i] == 2) {
					System.out.print("#" + tc + " ");
					dfs(0, 99, i);//순서대로 방향 x, y값
				}
			}
		}
		
	}

	private static void dfs(int direc, int x, int y) {
		//2. 1이 있는 지점에서 오른쪽이나 왼쪽에 1이 있다면 그 방향으로 방향을 전환한다.
		int rx = x + dx[1];
		int ry = y + dy[1]; //오른쪽으로 이동
		if(rx >= 0 && rx < 100 && ry >= 0 && ry < 100) {
			if(arr[rx][ry] != 0 && direc != 2) {
				//왼쪽 방향으로 가던 중이 아니고,
				//오른쪽에 사다리가 있다면 그 방향으로 이동
				dfs(1, rx, ry);
				return;
			}
		}
		
		int lx = x + dx[2];
		int ly = y + dy[2]; //왼쪽으로 이동
		if(lx >= 0 && lx < 100 && ly >= 0 && ly < 100) {
			if(arr[lx][ly] != 0 && direc != 1) {
				//오른쪽 방향으로 가던 중이 아니고,
				//왼쪽에 사다리가 있다면 그 방향으로 이동
				dfs(2, lx, ly);
				return;
			}
		}
		
		//왼쪽, 오른쪽 둘 다 이동이 불가하다면
		//위로 이동
		int tx = x + dx[0];
		int ty = y + dy[0];
		if(tx >= 0 && tx < 100 && ty >= 0 && ty < 100) {
			if(arr[tx][ty] != 0) {
				//해당 위치에 사다리가 있다면 위로 이동
				dfs(0, tx, ty);
			}
		}else {
			//끝부분에 도달했다면 해당 y값 출력
			System.out.println(y);
		}
	}
}