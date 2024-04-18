from collections import deque
import sys

input = sys.stdin.readline

n, m = map(int, input().split())
graph = [[] for i in range(n + 1)]

for i in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

visited = [False] * (n+1)

def bfs(graph, visited, start):
    visited[start] = True
    q = deque()
    q.append(start)

    while q:
        node = q.popleft()
        for i in graph[node]:
            if not visited[i]:
                visited[i] = True
                q.append(i)

cnt = 0
for i in range(1, n+1):
    if not visited[i]:
        bfs(graph, visited, i)
        cnt += 1
print(cnt)