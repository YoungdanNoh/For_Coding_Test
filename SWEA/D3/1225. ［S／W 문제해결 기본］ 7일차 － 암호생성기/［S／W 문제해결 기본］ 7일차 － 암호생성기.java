import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int t=1; t<=10; t++) {
			st = new StringTokenizer(bf.readLine());
			int tc = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer> pass = new ArrayList<>();
			st = new StringTokenizer(bf.readLine());
			for(int i=0; i<8; i++) {
				pass.add(Integer.parseInt(st.nextToken()));
			}
			
			boolean flag = true;
			while(flag) {
				for(int i=1; i<=5; i++) {
					pass.set(0, pass.get(0) - i);
					if(pass.get(0) <= 0) {
						pass.set(0, 0);
						pass.add(pass.get(0));
						pass.remove(0);
						flag = false;
						break;
					}else {
						pass.add(pass.get(0));
						pass.remove(0);
					}
				}
			}
			
			System.out.print("#"+t+" ");
			for(int i=0; i<8; i++) {
				System.out.print(pass.get(i) + " ");
			}
			System.out.println();
		}
	}

}