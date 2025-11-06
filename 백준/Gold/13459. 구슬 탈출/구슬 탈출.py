import sys
from collections import deque

read = sys.stdin.readline

N, M = map(int, read().split())
board = []

rx, ry, bx, by = 0, 0, 0, 0
for i in range(N):
    tmp = list(read().strip())
    board.append(tmp)

    for j in range(len(tmp)):
        if tmp[j] == 'R':
            rx, ry = i, j
        elif tmp[j] == 'B':
            bx, by = i, j

def move(x, y, dx, dy):
    cnt = 0

    while True:
        tx = x + dx
        ty = y + dy

        if board[tx][ty] == '#':
            # 벽에 도달
            return x, y, cnt, False
        elif board[tx][ty] == 'O':
            # 구멍에 도달
            return tx, ty, cnt+1, True

        x, y = tx, ty
        cnt += 1

dir = [(-1, 0), (1, 0), (0, -1), (0, 1)]
visited = set()
visited.add((rx, ry, bx, by))
q = deque()
q.append((rx, ry, bx, by, 0)) # 빨간 구슬 위치, 파란 구슬 위치, 이동 횟수

ans = 0
flag = False
while q:
    crx, cry, cbx, cby, cnt = q.popleft()

    if cnt >= 10:
        # 이동 횟수가 10번을 넘어감
        continue

    for dx, dy in dir:
        # 각 구슬을 해당 방향으로 끝까지 이동시킴
        # 빨간 구슬 이동
        nrx, nry, rcnt, rgoal = move(crx, cry, dx, dy)
        # 파란 구슬 이동
        nbx, nby, bcnt, bgoal = move(cbx, cby, dx, dy)

        if bgoal:
            # 파란 구슬이 빠지면 실패!
            continue

        if rgoal:
            ans = 1
            flag = True
            break

        if nrx == nbx and nry == nby:
            # 둘의 위치가 같다면 이동 횟수로 더 앞에 있는 구슬을 판단한다
            if rcnt > bcnt:
                # 빨간 구슬이 더 많이 움직였으므로 파란 구슬보다 뒤에 있음
                nrx -= dx
                nry -= dy
            else:
                nbx -= dx
                nby -= dy

        loc = (nrx, nry, nbx, nby)
        if loc not in visited:
            visited.add(loc)
            q.append((nrx, nry, nbx, nby, cnt+1))

    if flag:
        break

print(ans)