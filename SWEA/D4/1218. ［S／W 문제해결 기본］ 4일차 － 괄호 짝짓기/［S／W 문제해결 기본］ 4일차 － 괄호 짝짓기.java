import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int t=1; t<=10; t++) {
			st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(bf.readLine());
			String temp = st.nextToken();
			String[] arr = temp.split("");
			Stack<String> stack = new Stack<>();
			
			int ans = 1;
			for(int i=0; i<N; i++) {
				if(stack.size() == 0) {
					stack.push(arr[i]);
				}else if(arr[i].equals(")")) {
					if(stack.peek().equals("(")) {
						stack.pop();
					}else {
						ans = 0;
						break;
					}
				}else if(arr[i].equals("]")) {
					//System.out.println(stack);
					if(stack.peek().equals("[")) {
						stack.pop();
					}else {
						ans = 0;
						break;
					}
				}else if(arr[i].equals("}")) {
					if(stack.peek().equals("{")) {
						stack.pop();
					}else {
						ans = 0;
						break;
					}
				}else if(arr[i].equals(">")) {
					if(stack.peek().equals("<")) {
						stack.pop();
					}else {
						ans = 0;
						break;
					}
				}else {
					stack.push(arr[i]);
				}
			}
			
			System.out.println("#"+t+" "+ans);
		}
		
	}

}