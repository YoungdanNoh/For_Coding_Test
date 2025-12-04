import heapq

def solution(n, paths, gates, summits):
    # 각 게이트에서 출발 -> 산봉우리까지만 이동
    graph = [[] for _ in range(n+1)]
    
    for a, b, c in paths:
        graph[a].append((b, c))
        graph[b].append((a, c))
        
    g_set = set(gates)
    s_set = set(summits)
    
    INF = 10**15
    q = []
    visited = [False]*(n+1)
    dist = [INF]*(n+1)
    
    for g in gates:
        # 모든 게이트에서부터 시작하기
        heapq.heappush(q, (0, g)) # (intensity, 번호)
        dist[g] = 0
        
    while q:
        cost, node = heapq.heappop(q)
        
        if visited[node]:
            continue
        visited[node] = True
        
        # 산봉우리에 도착했다면 더 이상 탐색하지 않기
        if node in s_set:
            continue
            
        for nn, c in graph[node]:
            # 현재 노드와 연결된 다른 노드들 탐색
            if nn in g_set:
                # 다른 게이트로는 이동하지 않는다
                continue
            
            # 현재 경로까지의 intensity와 다음 노드까지의 intensity를 비교해서 더 큰 값을 찾음
            next_inten = max(cost, c)
            if dist[nn] > next_inten:
                # 현재 노드를 거쳐가는 것이 더 최소값이라면
                dist[nn] = next_inten
                heapq.heappush(q, (dist[nn], nn))
    

    # 산봉우리까지의 dist를 보고 그 값이 가장 최소가 되는 곳을 정답으로 고름
    summits.sort()
    min_inten = INF
    ans = [0, 0]
    
    for s in summits:
        if min_inten > dist[s]:
            min_inten = dist[s]
            ans[0] = s
            ans[1] = dist[s]
    
    return ans