import heapq

def solution(N, road, K):
    ans = 0
    INF = 1e9
    dist = [INF] * (N+1)
    
    graph = [[] for i in range(N+1)]
    
    for a, b, c in road:
        graph[a].append((b, c))
        graph[b].append((a, c))
        
    # 1번 출발 -> 다른 모든 마을
    dist[1] = 0
    
    pq = []
    heapq.heappush(pq, (0, 1))
    
    while pq:
        d, n = heapq.heappop(pq)
        
        for nn in graph[n]:
            # 현재 노드와 연결된 마을들 탐색
            if dist[nn[0]] > (d + nn[1]):
                dist[nn[0]] = d + nn[1]
                heapq.heappush(pq, (dist[nn[0]], nn[0]))
                
    for d in dist:
        if d <= K:
            ans += 1

    return ans