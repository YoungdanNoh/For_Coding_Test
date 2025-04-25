import sys, heapq

N, M = map(int, sys.stdin.readline().split())

graph = [[] for _ in range(N+1)]
inc = [0]*(N+1)

for i in range(M):
    a, b = map(int, sys.stdin.readline().split())
    graph[a].append(b)
    inc[b] += 1

q = []
for i in range(1, N+1):
    if(inc[i] == 0):
        heapq.heappush(q, i) # 해당 노드를 가리키는 게 없음

while q:
    cur = q.pop()

    print(cur, end=" ")

    for i in graph[cur]:
        inc[i] -= 1

        if(inc[i] == 0):
            heapq.heappush(q, i)