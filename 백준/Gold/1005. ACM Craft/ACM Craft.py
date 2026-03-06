import sys
import heapq

input = sys.stdin.readline
T = int(input())

for _ in range(T):
    N, K = map(int, input().split())
    times = [0] + list(map(int, input().split()))

    graph = [[] for i in range(N+1)]
    indegree = [0]*(N + 1)
    for i in range(K):
        a, b = map(int, input().split())
        graph[a].append(b) # b를 짓기 위해선 a를 지어야한다
        indegree[b] += 1

    target = int(input())

    pq = []
    for i in range(1, N+1):
        if indegree[i] == 0:
            heapq.heappush(pq, (times[i], i))
            # 건설에 걸리는 시간으로 최소힙

    cost = 0
    while pq:
        time, cur = heapq.heappop(pq)

        if cur == target:
            cost = time

        for nn in graph[cur]:
            # cur이 가리키는 건물들의 indegree 1감소
            indegree[nn] -= 1

            if indegree[nn] == 0:
                heapq.heappush(pq, (time+times[nn], nn))

    print(cost)