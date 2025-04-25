import sys, heapq

N, M, X = map(int, sys.stdin.readline().split())

graph = [[] for _ in range(N+1)]

for i in range(M):
    s, e, t = map(int, sys.stdin.readline().split())

    graph[s].append((e, t))

def dik(start):
    dist = [1e9]*(N+1)
    visited = [False]*(N+1)

    q = []

    dist[start] = 0
    heapq.heappush(q, (0, start))

    while(len(q) > 0):
        cur_cost, cur_node = heapq.heappop(q)

        if(visited[cur_node]):
            continue
        visited[cur_node] = True

        for next_node, weight in graph[cur_node]:
            if(dist[next_node] > cur_cost + weight):
                # 현재 저장된 거리 vs 현재 정점에서 i[1]로 가는 비용 비교
                dist[next_node] = cur_cost + weight

                heapq.heappush(q, (dist[next_node], next_node))

    #print(dist)
    return dist

result = 0
from_X = dik(X) # X에서 각 노드까지의 최단거리

for i in range(1, N+1):
    to_X = dik(i)[X]  # 현재 노드에서 X까지의 최단거리
    total = to_X + from_X[i]  # 왕복 거리
    result = max(result, total)

print(result)