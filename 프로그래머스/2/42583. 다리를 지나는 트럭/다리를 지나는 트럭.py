from collections import deque

def solution(bridge_length, weight, truck_weights):
    
    q = deque()
    q.append(((truck_weights[0], bridge_length)))
    
    time = 1
    idx = 1
    
    while q:
        time += 1

        cur = 0 # 현재 무게
        
        #tmpq = deque([])
        # for tw, b in q:
        #     if (b-1) > 0:
        #         cur += tw
        #         tmpq.append((tw, b-1))
        #     q.popleft()
        
        for _ in range(len(q)):
            tw, b = q.popleft()
            
            if (b-1) > 0:
                cur += tw
                q.append((tw, b-1))
        
        if idx < len(truck_weights):
            if (cur + truck_weights[idx]) <= weight:
                q.append((truck_weights[idx], bridge_length))
                idx += 1
            
    return time