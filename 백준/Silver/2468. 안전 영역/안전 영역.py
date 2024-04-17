import sys

sys.setrecursionlimit(10000)

n = int(input())

graph = []
for i in range(n):
    graph.append(list(map(int, input().split())))

min_ = min(map(min, graph))
max_ = max(map(max, graph))

# 왼, 오, 위, 아
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]
def dfs(visited, x, y, m):
    visited[x][y] = True

    for i in range(4):
        tx = x + dx[i]
        ty = y + dy[i]

        if tx < 0 or tx >= n or ty < 0 or ty >= n:
            continue
        if graph[tx][ty] > m:
            if not visited[tx][ty]:
                dfs(visited, tx, ty, m)

visited = [[False] * n for _ in range(n)]
cnt = 0
area = []
for m in range(min_, max_ + 1):
    for i in range(n):
        for j in range(n):
            if graph[i][j] > m and not visited[i][j]:
                dfs(visited, i, j, m)
                cnt += 1
    area.append(cnt)
    cnt = 0
    visited = [[False] * n for _ in range(n)]

if min_ == max_:
    print(1)
else:
    print(max(area))