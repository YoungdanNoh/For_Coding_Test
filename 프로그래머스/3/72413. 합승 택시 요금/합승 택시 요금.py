import heapq

def solution(n, s, a, b, fares):
    
    graph = [[] for i in range(n+1)]
    
    for v, w, cost in fares:
        graph[v].append((w, cost))
        graph[w].append((v, cost))
    
    def dijkstra(start):
        pq = []
        visited = [False]*(n+1)
        dist = [1e9]*(n+1)
        
        heapq.heappush(pq, (0, start)) # cost, 시작점
        dist[start] = 0
        
        while pq:
            cost, now = heapq.heappop(pq)
            
            if(visited[now]):
                continue
            visited[now] = True
            
            for nn, c in graph[now]:
                if(dist[nn] > dist[now] + c):
                    dist[nn] = dist[now] + c
                    heapq.heappush(pq, (dist[nn], nn))
                    
        return dist
    
    dijk = [[0]]
    for i in range(1, n+1):
        dijk.append(dijkstra(i)) # i번 노드에서 시작하여 다른 노드까지의 최소 경로를 구함
        
    result = 1e9
    for i in range(1, n+1):
        result = min(result, dijk[s][i] + dijk[i][a] + dijk[i][b])
        # start -> i번 노드를 거치고, 해당 i에서 a와 b로 가는 최소 경로를 모두 구해봄
        
    return result