from collections import deque

def solution(n, results):
    # 내가 이기는 수 + 내가 지는 수 = n-1 개라면 정확하게 순위 메기기 가능
    winG = [[] for _ in range(n+1)]
    lossG = [[] for _ in range(n+1)]
    
    for a, b in results:
        winG[a].append(b)
        lossG[b].append(a)
    
    win = [0 for _ in range(n+1)]
    loss = [0 for _ in range(n+1)]
    
    # 각 노드에 대해서 이기는 선수 수 세기
    for i in range(1, n+1):
        q = deque()
        visited = [False for _ in range(n+1)]
        visited[i] = True
        
        q.append(i) # 노드 번호
        cnt = -1
        while q:
            cur = q.popleft()
            cnt+=1
            
            for nn in winG[cur]:
                if visited[nn]:
                    continue
                visited[nn] = True
                q.append(nn)
                
        win[i] = cnt
        
    # 각 노드에 대해서 지는 선수 수 세기
    for i in range(1, n+1):
        q = deque()
        visited = [False for _ in range(n+1)]
        visited[i] = True
        
        q.append(i) # 노드 번호
        cnt = -1
        while q:
            cur = q.popleft()
            cnt+=1
            
            for nn in lossG[cur]:
                if visited[nn]:
                    continue
                visited[nn] = True
                q.append(nn)
                
        loss[i] = cnt
            
    ans = 0
    for i in range(1, n+1):
        if win[i] + loss[i] == n-1:
            ans += 1
    
    
    return ans