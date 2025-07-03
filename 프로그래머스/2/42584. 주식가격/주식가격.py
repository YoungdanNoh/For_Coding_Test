from collections import deque

def solution(prices):
    answer = [0 for i in range(len(prices))]
    
    q = deque()
    
    for i in range(len(prices)):
        
        q.append((i, prices[i], 1))
        
        qlen = len(q)
        
        for j in range(qlen):
            idx, p, t = q.popleft()
            
            if idx >= i:
                q.append((idx, p, t))
                continue
            
            if p > prices[i]:
                # 가격 떨어짐
                answer[idx] = t
            else:
                # 가격 떨어지지X
                q.append((idx, p, t+1))
                
    while q:
        idx, p, t = q.popleft()
        answer[idx] = t-1
    
    return answer