from collections import deque

n, m, k, x = map(int, input().split())

graph = [[] for _ in range(n + 1)]

for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)

result = [-1]*(n+1)
result[x] = 0

q = deque([x])

while q:
    node = q.popleft()
    for i in graph[node]:
        if result[i] == -1:
            q.append(i)
            result[i] = result[node] + 1

flag = False
for r in range(1, n+1):
    if result[r] == k:
        print(r)
        flag = True
if not flag:
    print(-1)