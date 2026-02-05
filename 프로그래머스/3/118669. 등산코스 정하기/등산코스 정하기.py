import heapq

def solution(n, paths, gates, summits):
    # 각 게이트에서 출발 -> 산봉우리까지 이동
    # 되돌아올 때는 동일한 경로로 돌아오면 됨
    
    graph = [[] for i in range(n+1)]
    
    for a, b, c in paths:
        graph[a].append((b, c))
        graph[b].append((a, c))
        
    INF = 10**15
    dist = [INF for i in range(n+1)]
    visited = [False for i in range(n+1)]
    
    g_set = set(gates)
    s_set = set(summits)
    
    pq = []
    for g in gates:
        heapq.heappush(pq, (0, g)) # intensity, 시작점
        dist[g] = 0
    
    while pq:
        inten, node = heapq.heappop(pq)
        
        if node in s_set:
            # 산봉우리 도착
            continue
            
        if visited[node]:
            continue
        visited[node] = True
        
        for nn, cost in graph[node]:
            
            if nn in g_set:
                # 다른 게이트로는 이동x
                continue
            
            new_inten = max(inten, cost) # 해당 경로에서의 intensity를 계산
                
            if dist[nn] > new_inten:
                dist[nn] = new_inten
                heapq.heappush(pq, (dist[nn], nn))
    
    # 산봉우리 노드들을 순차적으로 순회하며 ans값 탐색
    summits.sort()
    min_inten = INF
    ans = [0, 0]
    
    for s in summits:
        if min_inten > dist[s]:
            min_inten = dist[s]
            ans[0] = s
            ans[1] = dist[s]
    
    return ans