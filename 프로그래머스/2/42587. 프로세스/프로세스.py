from collections import deque

def solution(priorities, location):
    answer = 0
    
    q = deque()
    
    for i in range(len(priorities)):
        q.append((i, priorities[i]))
        
    priorities.sort(reverse = True)
    cnt = 0
    
    while q:
        idx, p = q.popleft()
        
        if priorities[cnt] == p:
            # 현재 실행해야 할 프로세스임
            if location == idx:
                answer = cnt
                break
            cnt += 1
            
        else:
            # 다음 순서로..
            q.append((idx, p))
            
    return cnt+1