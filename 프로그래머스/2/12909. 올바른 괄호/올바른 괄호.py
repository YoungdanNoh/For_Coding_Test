from collections import deque

def solution(s):
    answer = True
    
    q = deque()
    for i in s:
        if i == ")":
            # 직전에 들어간 값이 "("인지 체크
            if q and q[-1] == "(":
                q.pop()
                continue
        q.append(i)
    
    if len(q) > 0:
        return False
    else:
        return True