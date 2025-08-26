import java.util.*;

class Solution {
    public String[] solution(String[] s) {
        String[] ans = new String[s.length];
        
        for (int idx = 0; idx < s.length; idx++) {
            String str = s[idx];

            // 1) 스택처럼 쓰는 StringBuilder로 "110" 모두 제거 + 개수 카운트
            StringBuilder stack = new StringBuilder();
            int count110 = 0;

            for (char c : str.toCharArray()) {
                stack.append(c);
                int n = stack.length();
                if (n >= 3 &&
                    stack.charAt(n - 3) == '1' &&
                    stack.charAt(n - 2) == '1' &&
                    stack.charAt(n - 1) == '0') {
                    stack.delete(n - 3, n); // "110" 제거
                    count110++;
                }
            }

            // 2) 삽입 위치: rest의 "마지막 0" 바로 뒤 (0이 없으면 맨 앞)
            int insertPos = 0;
            for (int i = stack.length() - 1; i >= 0; i--) {
                if (stack.charAt(i) == '0') {
                    insertPos = i + 1;
                    break;
                }
            }

            // 3) 해당 위치에 "110" * count110 몰아서 삽입
            StringBuilder res = new StringBuilder(stack.length() + 3 * count110);
            res.append(stack, 0, insertPos); // 삽입 위치 앞 부분을 결과에 붙임
            for (int i = 0; i < count110; i++) res.append("110"); // 뽑아낸 "110"들을 삽입
            res.append(stack, insertPos, stack.length()); // 삽입 위치 뒤 부분을 결과에 붙임

            ans[idx] = res.toString();
        }
        
        return ans;
    }
}