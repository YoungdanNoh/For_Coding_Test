import sys, heapq

T = int(sys.stdin.readline().strip())

for _ in range(T):
    N, M = map(int, sys.stdin.readline().split())

    graph = [[] for i in range(N+1)]

    for i in range(M):
        a, b = map(int, sys.stdin.readline().split())
        graph[a].append(b)
        graph[b].append(a)

    visited = [False]*(N+1)
    q = []
    heapq.heappush(q, (0, 1)) # 1번 노드부터 시작

    result = 0
    while q:
        cost, node = heapq.heappop(q)

        if(visited[node]):
            continue
        visited[node] = True
        result += 1

        for i in graph[node]:
            if not visited[i]:
                heapq.heappush(q, (0, i))

    print(result-1)