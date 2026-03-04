import heapq

def solution(n, s, a, b, fares):
    
    graph = [[] for _ in range(n+1)]
    for c, d, f in fares:
        graph[c].append((d, f))
        graph[d].append((c, f))
    
    def dijkstra(start):
        pq = []
        dist = [1e9]*(n+1) # start에서부터 시작하여 다른 정점까지의 cost를 저장
        visited = [False]*(n+1)
        
        dist[start] = 0
        heapq.heappush(pq, (0, start))
        
        while pq:
            cost, node = heapq.heappop(pq)
            
            if visited[node]:
                continue
            visited[node] = True
            
            for nn, nc in graph[node]:
                # 현재 노드와 연결된 노드들 탐색
                if dist[nn] > (dist[node] + nc):
                    dist[nn] = dist[node] + nc
                    heapq.heappush(pq, (dist[nn], nn))
        
        return dist
    
    tot_dist = [[]] # 각 노드에서 다른 노드까지의 최소거리를 모두 저장한다
    for i in range(1, n+1):
        tot_dist.append(dijkstra(i))
    
    ans = 1e9
    # s -> i / i -> a / i -> b로 가는 경우들 중 최소 비용을 찾는다.
    for i in range(1, n+1):
        ans = min(ans, tot_dist[s][i] + tot_dist[i][a] + tot_dist[i][b])
            
    return ans