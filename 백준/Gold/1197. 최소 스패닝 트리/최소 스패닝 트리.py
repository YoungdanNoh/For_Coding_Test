import sys, heapq

V, E = map(int, sys.stdin.readline().split())

graph = [[] for _ in range(V + 1)]
start = 0

for i in range(E):
    a, b, c = map(int, input().split())
    graph[a].append((c, b))
    graph[b].append((c, a))  # 무방향 그래프이므로 양쪽 추가

    if(i == 0):
        start = a

q = []
visited = [0 for i in range(V+1)]
heapq.heappush(q, (0, start))

cost = 0
while(len(q) > 0):
    cur = heapq.heappop(q)

    if(visited[cur[1]] == 1):
        continue
    visited[cur[1]] = 1

    cost += cur[0]

    for nc, nn in graph[cur[1]]:
        if(visited[nn] == 0):
            heapq.heappush(q, (nc, nn))

print(cost)