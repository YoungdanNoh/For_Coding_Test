import heapq

def solution(n, edge):
    
    pq = []
    graph = [[] for _ in range(n+1)]
    dist = [50001 for _ in range(n+1)]
    visited = [False for _ in range(n+1)]
    
    for a, b in edge:
        graph[a].append(b)
        graph[b].append(a)
    
    # 시작 노드의 거리는 0
    dist[1] = 0
    
    heapq.heappush(pq, (0, 1)) # 출발지 넣기
    
    while pq:
        cost, node = heapq.heappop(pq)
        
        if visited[node]:
            continue
        
        visited[node] = True
        
        # 현재 노드와 연결된 노드들 탐색
        for nn in graph[node]:
            if visited[nn]:
                continue
            if dist[nn] > dist[node] + 1:
                dist[nn] = dist[node] + 1
                heapq.heappush(pq, (dist[nn], nn))
        
    dist.pop(0)
    
    mdist = 0
    ans = 0
    for i in range(len(dist)):
        if mdist < dist[i] and dist[i] != 50001:
            mdist = dist[i]
            ans = 1
        elif mdist == dist[i] and mdist != 0:
            ans += 1
        
    return ans