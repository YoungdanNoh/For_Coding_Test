from collections import deque

n, m = map(int, input().split())
miro = []
for i in range(n):
    miro.append(list(map(int, input())))

# 왼, 오, 위, 아
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

def dfs(ix, iy, miro):
    q = deque()
    q.append((ix, iy))

    while q:
        x, y = q.popleft()
        for i in range(4):
            tx = x + dx[i]
            ty = y + dy[i]
            # 현재 위치에서 왼, 오, 위, 아 위치로 이동해본다.
            if tx >= 0 and tx < n and ty >= 0 and ty < m:
                if miro[tx][ty] == 1:
                    miro[tx][ty] = miro[x][y] + 1
                    q.append((tx, ty))

dfs(0, 0, miro)
print(miro[n-1][m-1])