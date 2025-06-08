import heapq

def solution(n, costs):
    graph = [[] for i in range(n)]
    
    for a, b, cost in costs:
        graph[a].append((b, cost))
        graph[b].append((a, cost))
        
    visited = [False]*n
    pq = []
    heapq.heappush(pq, (0, 0)) # 비용, 시작점
    
    result = 0
    
    while pq:
        c, n = heapq.heappop(pq)
        
        if(visited[n]):
            continue
        visited[n] = True
        result += c
        
        for nn, nc in graph[n]:
            if(not visited[nn]):
                heapq.heappush(pq, (nc, nn))
    
    return result