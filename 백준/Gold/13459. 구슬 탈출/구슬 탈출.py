import sys
from collections import deque

input = sys.stdin.readline

N, M = map(int, input().split(" "))
board = [0]*N

rx, ry = 0, 0 # 빨간 공 위치
bx, by = 0, 0 # 파란 공 위치
for i in range(N):
    board[i] = (list(input().strip()))

    for j in range(M):
        if board[i][j] == 'R':
            rx, ry = i, j
        elif board[i][j] == 'B':
            bx, by = i, j


def move(i, j, x, y):
    cnt = 0 # 이동 횟수

    while True:
        tx = x + i
        ty = y + j

        if tx<0 or tx>=N or ty<0 or ty>=M:
            break
        if board[tx][ty] == '#':
            # 벽
            break
        if board[tx][ty] == 'O':
            # 구멍
            cnt += 1
            x, y = tx, ty
            break

        cnt += 1
        x, y = tx, ty

    return cnt, x, y

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

# dfs 상하좌우 움직이기
q = deque()
q.append((0, rx, ry, bx, by))
visited = set()
visited.add((rx, ry, bx, by))

ans = 0
while q:
    cur = q.popleft()

    if cur[0] >= 10:
        # 10번 움직였다면
        continue

    for i in range(4):
        rcnt, rx, ry = move(dx[i], dy[i], cur[1], cur[2]) # red 사방으로 이동시켜보기
        bcnt, bx, by = move(dx[i], dy[i], cur[3], cur[4]) # blue 사방으로 이동시켜보기

        if board[bx][by] == 'O':
            # 파란 구슬이 구멍에 빠졌으므로 실패
            continue

        if board[rx][ry] == 'O':
            # 성공
            ans = 1
            break

        if rx == bx and ry == by:
            # 둘이 같은 위치에 있다면
            if rcnt > bcnt:
                # 빨간공이 더 뒤
                rx = rx - dx[i]
                ry = ry - dy[i]
            else:
                bx = bx - dx[i]
                by = by - dy[i]

        v = (rx, ry, bx, by)
        if v not in visited:
            visited.add(v)
            q.append((cur[0]+1, rx, ry, bx, by))

    if ans == 1:
        break

print(ans)