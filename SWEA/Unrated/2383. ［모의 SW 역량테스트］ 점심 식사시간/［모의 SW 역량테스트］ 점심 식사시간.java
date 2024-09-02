import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[][] map;
	static ArrayList<ArrayList<Integer>> p; //사람위치 저장
	static int sx1, sy1, sx2, sy2; //계단 위치 저장
	static int p_cnt; //사람 수 카운트
	static boolean[] isSelected;
	static ArrayList<ArrayList<Integer>> sub1;
	static ArrayList<ArrayList<Integer>> sub2;
	static int[] dx = {-1, 1, 0, 0}; //상하좌우
	static int[] dy = {0, 0, -1, 1};
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//두 무리로 나누는 모든 경우의 수(하나를 부분집합으로 구하면 나머지는 구해짐)
		//-> 각각 1, 2계단 or 2, 1계단으로 내려가 본다.
		//ex) bfs로 1번 계단까지의 최단 경로 구하기 -> 사람이 3명을 넘는다면 이를 대기시간을 처리해준다.
		int T = Integer.parseInt(bf.readLine().trim());
		for(int t=1; t<=T; t++) {
			ans = 100*10*10;
			p = new ArrayList<>();
			
			N = Integer.parseInt(bf.readLine().trim());
			map = new int[N][N];
			
			boolean flag = true;
			p_cnt = 0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(bf.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						//사람
						p.add(new ArrayList<>());
						p.get(p.size()-1).add(i);
						p.get(p.size()-1).add(j);
						p_cnt++;
					}else if(map[i][j] != 0) {
						//계단
						if(flag) {
							flag = false;
							sx1 = i;
							sy1 = j;
						}else {
							sx2 = i;
							sy2 = j;
						}
					}
				}
			}
			isSelected = new boolean[p_cnt];
			subSet(0);
			
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void subSet(int cnt) {
		if(cnt == p_cnt) {
			sub1 = new ArrayList<>(); //1번 부분집합
			sub2 = new ArrayList<>(); //2번 부분집합
			
			for(int i=0; i<p_cnt; i++) {
				if(isSelected[i]) {
					sub1.add(new ArrayList<>());
					sub1.get(sub1.size()-1).add(p.get(i).get(0));
					sub1.get(sub1.size()-1).add(p.get(i).get(1));
				}else {
					sub2.add(new ArrayList<>());
					sub2.get(sub2.size()-1).add(p.get(i).get(0));
					sub2.get(sub2.size()-1).add(p.get(i).get(1));
				}
			}
			
			ArrayList<Integer> sub1_dis = new ArrayList<>();
			ArrayList<Integer> sub2_dis = new ArrayList<>();
			//해당되는 sub1의 인덱스에 계단까지의 거리를 저장
			//(ex 0번 인덱스 값은 sub1의 0번 인덱스 위치에서 계단까지의 거리를 저장)
			
			//1. sub1 --> 계단1 , sub2 --> 계단2
			for(int i=0; i<sub1.size(); i++) {
				sub1_dis.add(Math.abs(sub1.get(i).get(0) - sx1) 
						+ Math.abs(sub1.get(i).get(1) - sy1) + 1);
			}
			for(int i=0; i<sub2.size(); i++) {
				sub2_dis.add(Math.abs(sub2.get(i).get(0) - sx2) 
						+ Math.abs(sub2.get(i).get(1) - sy2) + 1);
			}
			Collections.sort(sub1_dis);
			Collections.sort(sub2_dis);
			
			int t1 = time(sub1_dis, sx1, sy1);
			int t2 = time(sub2_dis, sx2, sy2);
			int temp_t = Math.max(t1, t2);
			ans = Math.min(ans, temp_t+1);
			
			//1. sub1 --> 계단2 , sub2 --> 계단1
			sub1_dis = new ArrayList<>();
			sub2_dis = new ArrayList<>();
			for(int i=0; i<sub1.size(); i++) {
				sub1_dis.add(Math.abs(sub1.get(i).get(0) - sx2) 
						+ Math.abs(sub1.get(i).get(1) - sy2) + 1);
			}
			for(int i=0; i<sub2.size(); i++) {
				sub2_dis.add(Math.abs(sub2.get(i).get(0) - sx1) 
						+ Math.abs(sub2.get(i).get(1) - sy1) + 1);
			}
			Collections.sort(sub1_dis);
			Collections.sort(sub2_dis);
			
			t1 = time(sub1_dis, sx2, sy2);
			t2 = time(sub2_dis, sx1, sy1);
			temp_t = Math.max(t1, t2);
			ans = Math.min(ans, temp_t);
			
			return;
		}
		isSelected[cnt] = true;
		subSet(cnt+1);
		isSelected[cnt] = false;
		subSet(cnt+1);
	}

	private static int time(ArrayList<Integer> sub_dis, int sx, int sy) {
		int t1 = 0;
		
		if(sub_dis.size() == 0) {
			t1 = 0;
		}else if(sub_dis.size() <= 3) {
			t1 = Collections.max(sub_dis) + map[sx][sy];
		}else {
			Queue<Integer> q = new LinkedList<>();
			
			q.offer(sub_dis.get(0));
			q.offer(sub_dis.get(1));
			q.offer(sub_dis.get(2));
			
			int idx = 3;
			while(!q.isEmpty()) {
				int cur_time = q.peek() + map[sx][sy];
				q.poll();
				
				if(idx < sub_dis.size()) {
					if(cur_time > sub_dis.get(idx)) {
						//맨 앞 사람이 나가는 것보다 더 일찍 도착함
						sub_dis.set(idx, sub_dis.get(idx) + cur_time - sub_dis.get(idx));
						//그 다음 사람의 도착시간 차이만큼 증가시키기
						q.offer(sub_dis.get(idx));
					}else {
						q.offer(sub_dis.get(idx));
					}
					idx++;
				}
				t1 = cur_time;
			}
		}
		
		return t1;
	}
}