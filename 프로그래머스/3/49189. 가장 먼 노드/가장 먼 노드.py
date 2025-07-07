import heapq

def solution(n, edge):
    
    graph = [[] for _ in range(n+1)]
    
    for a, b in edge:
        graph[a].append(b)
        graph[b].append(a)
    
    dist = [1e9]*(n+1)
    visited = [False]*(n+1)
    q = []
    
    heapq.heappush(q, (0, 1))
    dist[1] = 0
    
    while q:
        cd, cn = heapq.heappop(q)
        
        if visited[cn]:
            continue
        
        visited[cn] = True
        
        for nn in graph[cn]:
            # cn과 연결된 노드 탐색
            if dist[nn] > (cd + 1):
                dist[nn] = cd + 1
                heapq.heappush(q, (dist[nn], nn))
    
    dist.sort(reverse = True)
    mdist = -1
    cnt = 0
    
    for d in dist:
        if d == 1e9:
            continue    
        elif mdist == -1:
            mdist = d
            
        if d == mdist and d != 0:
            cnt += 1
        elif mdist != -1:
            break
    
    return cnt