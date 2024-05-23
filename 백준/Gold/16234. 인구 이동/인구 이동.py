import math
from collections import deque

n, l, r = map(int, input().split())

country = []
for _ in range(n):
    country.append(list(map(int, input().split())))

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def bfs(x, y, visited):
    visited[x][y] = True

    q = deque()
    q.append((x, y))

    border = []
    border.append((x, y))

    while q:
        px, py = q.popleft()

        for i in range(4):
            tx = px + dx[i]
            ty = py + dy[i]

            if tx < 0 or tx >= n or ty < 0 or ty >= n:
                continue
            if visited[tx][ty]:
                continue
            if l <= abs(country[px][py] - country[tx][ty]) <= r:
                border.append((tx, ty))
                q.append((tx, ty))
                visited[tx][ty] = True

    if len(border) == 1:
        # 인구 이동이 없다면
        return 0

    add = 0
    for b in border:
        add += country[b[0]][b[1]]
    avg = math.trunc(add / (len(border)))

    for b in border:
        country[b[0]][b[1]] = avg

    return 1


cnt = 0
while True:
    stop = 0
    visited = [[False] * n for i in range(n)]

    for i in range(n):
        for j in range(n):
            if visited[i][j]:
                continue
            stop += bfs(i, j, visited)
    if stop == 0:
        break
    cnt += 1

print(cnt)