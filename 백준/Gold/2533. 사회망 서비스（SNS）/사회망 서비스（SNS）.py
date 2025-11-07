import sys

sys.setrecursionlimit(1000000)
read = sys.stdin.readline

def dfs(start):
    visited[start] = True

    for i in graph[start]:
        if not visited[i]:
            dfs(i)
            dp[start][0] += dp[i][1]
            dp[start][1] += min(dp[i][0], dp[i][1])

N = int(read())
graph = [[] for _ in range(N+1)]
for i in range(N-1):
    u, v = map(int, read().split())
    graph[u].append(v)
    graph[v].append(u)

visited = [False] * (N + 1)
dp = [[0, 1] for _ in range(N + 1)]

dfs(1)
print(min(dp[1][0], dp[1][1]))