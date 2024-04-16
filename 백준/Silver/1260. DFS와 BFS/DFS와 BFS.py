from collections import deque

n, m, v = map(int, input().split())

graph = [[] for _ in range(n+1)]
for i in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

for g in graph:
    g.sort()

def dfs(graph, visited, node):
    visited[node] = True
    print(node, end=' ')

    for i in graph[node]:
        if not visited[i]:
            dfs(graph, visited, i)

def bfs(graph, visited, node):
    q = deque([node])
    visited[node] = True
    print(node, end=' ')

    while q:
        no = q.popleft()

        for i in graph[no]:
            if not visited[i]:
                q.append(i)
                visited[i] = True
                print(i, end=' ')

visited = [False]*(n+1)
dfs(graph, visited, v)
print()
visited = [False]*(n+1)
bfs(graph, visited, v)