import heapq

def solution(n, paths, gates, summits):
    INF = 1e9
    ans = [INF, INF]
    
    # (출입구 중 1 -> 산봉우리 중 1)의 모든 경로들의 intensity 최소값 구하기
    graph = [[] for i in range(n+1)]
    
    for a, b, c in paths:
        graph[a].append((b, c))
        graph[b].append((a, c))
        
    isGate = [False]*(n+1)
    for i in gates:
        isGate[i] = True
        
    isSummit = [False]*(n+1)
    for i in summits:
        isSummit[i] = True
        
    dist = [INF]*(n+1)
    pq = []
    for g in gates:
        # 모든 출입구에 대해 거리를 0으로 설정
        dist[g] = 0 # 해당 출입구가 시작점
        heapq.heappush(pq, (0, g)) # 거리, 노드

    while pq:
        d, node = heapq.heappop(pq)

        if isSummit[node]:
            # 산봉우리에 도착했으므로 탐색 종료
            continue
        if dist[node] < d:
            continue
                    
        for nn, nd in graph[node]:
            # 현재 노드와 연결된 다음 노드
            if isGate[nn]:
                continue
                    
            new_intensity = max(d, nd) # 휴식없이 이동하는 가장 긴 시간 계산
                    
            if dist[nn] > new_intensity:
                dist[nn] = new_intensity
                heapq.heappush(pq, (dist[nn], nn))
                        
    # 해당 출입구, 산봉우리 조합에서 나올 수 있는 최소의 intensity
    for s in summits:
        if dist[s] < ans[1]:
            ans[0] = s
            ans[1] = dist[s]
        elif dist[s] == ans[1] and s < ans[0]:
            ans[0] = s
            ans[1] = dist[s]
        
    return ans