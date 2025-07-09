import java.util.*;

class Solution {
    boolean solution(String s) {
        
        Stack<Character> stack = new Stack<>();
        
        char[] chars = s.toCharArray();
        for(int i=0; i<chars.length; i++){
            
            if(chars[i] == '('){
                stack.push('(');
                
            }else if(stack.size() > 0 && stack.peek() == '('){
                stack.pop();
                
            }else{
                return false;
                
            }
        }

        if(stack.size() > 0){
            return false;
            
        }else{
            return true;
        }
    }
}