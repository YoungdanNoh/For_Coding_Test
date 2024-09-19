import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static int N;
	static int M;
	static int D;
	static int[][] map;
	static int[] select;
	static int[] dx = {-1, 1, 0, 0, 0};
	static int[] dy = {0, 0, -1, 1, 0};
	static ArrayList<int[]> attak;
	static int ans = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		select = new int[3];
		combi(0, 0);
		System.out.println(ans);
	}

	private static void combi(int cnt, int start) {
		if(cnt == 3) {
			int sum = 0;

			int[][] tmp_m = new int[N][M];
			for (int i = 0; i <N ; i++) {
				tmp_m[i] = map[i].clone();
				sum += Arrays.stream(map[i]).sum();
			}

			//System.out.println(Arrays.toString(select));
//			System.out.println("1==================");
//			for(int[] m : tmp_m){
//				System.out.println(Arrays.toString(m));
//			}
//			System.out.print("배치: ");
//			System.out.println(Arrays.toString(select));

			//System.out.println("================================");
			int kill = 0;
			while (sum > 0) {
				//궁수를 배치할 수 있는 경우의 방법을 생성 후 공격
				attak = new ArrayList<>();
				attak(tmp_m);
//				System.out.println("공격할 곳================================");
//				for(int[] a: attak){
//					System.out.println(Arrays.toString(a));
//				}
				for (int i = 0; i < attak.size(); i++) {
					if(tmp_m[attak.get(i)[0]][attak.get(i)[1]] > 0){
						tmp_m[attak.get(i)[0]][attak.get(i)[1]] -= 1;

						if(tmp_m[attak.get(i)[0]][attak.get(i)[1]] == 0){
							kill += 1;
						}
					}
				}

//				System.out.println("2==================");
//				for(int[] m : tmp_m){
//					System.out.println(Arrays.toString(m));
//				}
				//map을 하나씩 밑으로 내리기
				sum = 0;
				for (int i = N-1; i >= 1 ; i--) {
					sum += Arrays.stream(tmp_m[i-1]).sum();

					for (int j = M-1; j >= 0 ; j--) {
						tmp_m[i][j] = tmp_m[i-1][j];
					}
				}
				for (int j = 0; j < M; j++) {
					tmp_m[0][j] = 0;
				}

//				System.out.println("3==================");
//				for(int[] m : tmp_m){
//					System.out.println(Arrays.toString(m));
//				}
			}

			ans = Math.max(ans, kill);
			return;
		}
		for (int i = start; i < M; i++) {
			select[cnt] = i;
			combi(cnt+1, i+1);
		}
	}

	private static void attak(int[][] map) {
		//System.out.println(Arrays.toString(select));
		for (int i = 0; i < 3; i++) {
			//현재 위치에서 D이하의 거리이며 공격할 수 있는 적을 찾는다.
			bfs(N-1, select[i], map);
		}
	}

	private static void bfs(int x, int y, int[][] map) {
		boolean[][] visited = new boolean[N][M];
		//visited[x][y] = true;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[]{x, y});

		int ax = Integer.MAX_VALUE;
		int ay = Integer.MAX_VALUE;
		int distance = Integer.MAX_VALUE;

		while (!q.isEmpty()){
			int[] cur = q.poll();

			for (int i = 0; i < 5; i++) {
				int tx = cur[0] + dx[i];
				int ty = cur[1] + dy[i];

				//System.out.println("tx: " + tx + ", ty: " + ty);
//					if(x == 3 && y== 0){
//						System.out.println("tx: "+tx + ", ty: "+ty);
//					}
				if(tx<0 || tx>=N || ty<0 || ty>=M) continue;
				if(visited[tx][ty]) continue;
				//if(map[tx][ty] == 0) continue;

				int dis_tmp = Math.abs(x+1-tx) + Math.abs(y-ty);
				//System.out.println("dis_tmp: " + dis_tmp);
				//System.out.println("x: " + x + ", y: " + y);
				//System.out.println("distance: "+ distance);
				//System.out.println("tx: " + tx + ", ty: " + ty + " " + "dis_tmp: " + dis_tmp);
				if(dis_tmp > D) continue;

				if(distance > dis_tmp && map[tx][ty] != 0){
					ax = tx;
					ay = ty;
					distance = dis_tmp;
				}else if(distance == dis_tmp && map[tx][ty] != 0){
					//System.out.println("aaa: " + dis_tmp);
					//System.out.println(ay + ", ty: " + ty);
					if(ay > ty){
						//공격범위 이내이며, 더 왼쪽에 있다면
						ax = tx;
						ay = ty;
					}
				}
				visited[tx][ty] = true;
				q.offer(new int[]{tx, ty});
			}
		}

//		System.out.println("x: " + x + ", y: " + y);
//		System.out.println("ax: " + ax + ", ay: " + ay);

		if(ax != Integer.MAX_VALUE){
			attak.add(new int[] {ax, ay});
		}
	}

}