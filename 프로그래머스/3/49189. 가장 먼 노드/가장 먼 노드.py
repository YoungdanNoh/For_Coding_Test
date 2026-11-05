import heapq

def solution(n, edge):
    ans = 0
    INF = 1e9
    
    # 간선 가중치 모두 1
    graph = [[] for _ in range(n+1)]
    visited = [False]*(n+1)
    dist = [INF]*(n+1)
    
    for a, b in edge:
        graph[a].append(b)
        graph[b].append(a)
    
    hq = []
    
    dist[1] = 0
    
    heapq.heappush(hq, (0, 1))
    while hq:
        cost, n = heapq.heappop(hq)
        
        if visited[n]:
            continue
        visited[n] = True
        
        for nn in graph[n]:
            if dist[nn] > dist[n] + 1:
                dist[nn] = dist[n] + 1
                heapq.heappush(hq, (dist[nn], nn))
    
    max_dist = 0
    for i in dist:
        if i == INF:
            continue
        if max_dist < i:
            max_dist = i
    
    for i in dist:
        if i == max_dist and i != 0:
            ans += 1
    
    return ans