import java.util.*;
import java.util.regex.*;

class Solution {
    // + : 0 , - : 1 , * : 2
    
    public void cal(String cur){
        Deque<Long> newNum = new ArrayDeque<>();
        Deque<String> newOp = new ArrayDeque<>();
                
        while(!op.isEmpty()){
            String curOp = op.poll();
            long num1 = num.poll();
                    
            if(curOp.equals(cur)){
                long num2 = num.poll();
                
                if(cur.equals("+")){
                    num.addFirst(num1 + num2);
                
                }else if(cur.equals("-")){
                    num.addFirst(num1 - num2);
                    
                }else{
                    num.addFirst(num1 * num2);
                    
                }
                        
            }else{
                newOp.offer(curOp);
                newNum.offer(num1);
                        
            }
        }
                
        while(!num.isEmpty()){
            newNum.offer(num.poll());
                    
        }
                
        num.clear();
        num.addAll(newNum);
                
        op.clear();
        op.addAll(newOp);
        
    }
    
    public void pre(){
        String regex1 = "(\\d+)";
        String regex2 = "([-+*])";
        
        Pattern pattern = Pattern.compile(regex1);
        Matcher matcher = pattern.matcher(exp);
        
        while(matcher.find()){
            num.offer(Long.parseLong(matcher.group(1)));
            
        }
        
        pattern = Pattern.compile(regex2);
        matcher = pattern.matcher(exp);
        
        while(matcher.find()){
            op.offer(matcher.group(1));
            
        }
        
        for(int i=0; i<3; i++){
            if(result[i] == 0){
                // + 연산 하기
                cal("+");
                
            }else if(result[i] == 1){
                // - 연산 하기
                cal("-");
                
            }else if(result[i] == 2){
                // * 연산 하기
                cal("*");
                
            }
            
        }
        
        answer = Math.max(answer, Math.abs(num.poll()));
    }
    
    public void perm(int cnt){
        if(cnt == 3){
            // 수식 계산 함수 호출
            pre();
            
            return;
        }
        for(int i=0; i<3; i++){
            if(visited[i]) continue;
            
            visited[i] = true;
            result[cnt] = i;
            perm(cnt+1);
            visited[i] = false;
        }
    }
    
    static int[] result = new int[3];
    static boolean[] visited = new boolean[3];
    static String exp = "";
    static Deque<Long> num = new ArrayDeque<>();
    static Deque<String> op = new ArrayDeque<>();
    static long answer = 0;
    
    public long solution(String expression) {
        
        exp = expression;
        perm(0);
        return answer;
    }
}