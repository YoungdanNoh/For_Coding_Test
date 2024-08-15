import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    //오, 아, 왼, 위 순서
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int arr[][];
    
	public static void main(String args[]) throws Exception
	{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(bf.readLine());
		int t = Integer.parseInt(st.nextToken());
		int num;
		
		for(int i=1; i<t+1; i++) {
			st = new StringTokenizer(bf.readLine());
			num = Integer.parseInt(st.nextToken());
			
			arr = new int[num][num];
			for(int x=0; x<num; x++) {
				for(int y=0; y<num; y++) {
					arr[x][y] = -1;
				}
			}
			arr[0][0] = 1;
			dfs(0, 2, num, 0, 0); //순서대로 배열, 방향, 배열 안에 넣을 값, 입력받은 숫자, 시작  x y 위치
			
			System.out.println("#"+i);
			for(int[] x: arr) {
				for(int y: x) {
					System.out.print(y + " ");
				}
				System.out.println();
			}
		}
	}
    private static void dfs(int direc, int cnt, int num, int x, int y) {
		int tx = x+dx[direc];
		int ty = y+dy[direc];
		
		if(cnt > Math.pow(num, 2)) {
			return;
		}
		
		if(tx >= num || tx<0 || ty>=num || ty<0) {
			//범위를 넘었으므로 방향 전환
			direc += 1;
			if(direc >= 4) {
				direc = 0;
			}
			dfs(direc, cnt, num, x, y);
		}else if(arr[tx][ty] != -1) {
			//해당 위치에 이미 값이 있으므로 방향 전환
			direc += 1;
			if(direc >= 4) {
				direc = 0;
			}
			dfs(direc, cnt, num, x, y);
		}else {
			arr[tx][ty] = cnt;
			dfs(direc, cnt+1, num, tx, ty);
		}
	}
}