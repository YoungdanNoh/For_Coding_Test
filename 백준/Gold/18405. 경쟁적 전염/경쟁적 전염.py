from collections import deque

n, k = map(int, input().split())
arr = []
virus = [] # 바이러스 위치

for i in range(n):
    arr.append(list(map(int, input().split())))
    for j in range(n):
        if arr[i][j] != 0:
            virus.append((arr[i][j], i, j, 0))
            # 순서대로 바이러스 번호, x, y, 초
virus.sort()
is_, ix, iy = map(int, input().split())

# 상하좌우
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
q = deque(virus)
    
while q:
    v, x, y, s = q.popleft()

    if is_ == s:
        break

    for i in range(4):
        tx = x + dx[i]
        ty = y + dy[i]

        if tx < 0 or tx >= n or ty < 0 or ty >= n:
            continue
        if arr[tx][ty] != 0:
            continue
        arr[tx][ty] = v
        q.append((v, tx, ty, s+1))

print(arr[ix-1][iy-1])