import heapq

def solution(n, costs):
    ans = 0
    
    graph = [[] for i in range(n)]
    for a, b, c in costs:
        graph[a].append((b, c))
        graph[b].append((a, c))
        
    visited = [False]*n
        
    # 0번 섬을 시작값으로 설정
    pq = []
    heapq.heappush(pq, (0, 0))
    
    while pq:
        cost, node = heapq.heappop(pq)
        
        if visited[node]:
            continue
            
        ans += cost
        visited[node] = True
        
        for nn, nc in graph[node]:
            if visited[nn]:
                continue
            heapq.heappush(pq, (nc, nn))
    
    return ans