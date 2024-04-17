n = int(input())
map_ = []
for _ in range(n):
    map_.append(list(input()))

# 왼, 오, 위, 아
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

danji = []
def dfs(map_, x, y, cnt):
    map_[x][y] = '0'
    if len(danji) == (cnt+1):
        danji[cnt] = danji[cnt] + 1
    else:
        danji.append(1)

    for i in range(4):
        tx = x + dx[i]
        ty = y + dy[i]
        if tx < 0 or tx >= n or ty < 0 or ty >= n:
            # 맵의 범위를 벗어난다면 해당 경로는 스킵한다
            continue
        if map_[tx][ty] == '1':
            dfs(map_, tx, ty, cnt)

cnt = 0
for i in range(n):
    for j in range(n):
        if map_[i][j] == '1':
            dfs(map_, i, j, cnt)
            cnt += 1
print(cnt)
danji.sort()
for d in danji:
    print(d)