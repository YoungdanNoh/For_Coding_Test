import copy
import sys
from collections import deque

read = sys.stdin.readline

N, M = map(int, read().split())
board = []

for i in range(N):
    line = list(read().strip())
    board.append(line)

# 빨간 구슬, 파란 구슬 위치
rx, ry = 0, 0
bx, by = 0, 0
for i in range(N):
    for j in range(M):
        if board[i][j] == 'R':
            rx, ry = i, j
        if board[i][j] == 'B':
            bx, by = i, j

def move(x, y, dx, dy):
    # 벽 앞 or 구멍까지 굴려보기
    cnt = 0 # 이동 칸 수
    while True:
        nx, ny = x + dx, y + dy
        if board[nx][ny] == '#':
            # 벽이면 그 전 칸에서 정지
            return x, y, cnt, False
        if board[nx][ny] == 'O':
            # 구멍이면 그 칸에서 종료
            return nx, ny, cnt + 1, True
        x, y = nx, ny
        cnt += 1

# 상하좌우
dirs = [(-1, 0), (1, 0), (0, -1), (0, 1)]

visited = set()
q = deque()
q.append((rx, ry, bx, by, 0))
visited.add((rx, ry, bx, by))

ans = 0
flag = False
while q:
    crx, cry, cbx, cby, depth = q.popleft()

    if depth >= 10:
        # 10번 넘어가는 순간 실패
        # 현재 10번째라면 이 다음은 11번째가 되므로 실패
        continue

    for dx, dy in dirs:
        # 각 구슬을 해당 방향 끝까지
        nrx, nry, rcnt, r_in = move(crx, cry, dx, dy)
        nbx, nby, bcnt, b_in = move(cbx, cby, dx, dy)

        if b_in:
            # 파란 구슬이 빠지면 실패
            continue
        if r_in:
            ans = 1
            flag = True
            break

        # 같은 칸에 멈췄다면 이동한 칸 수로 두 구슬의 위치 지정해주기
        if nrx == nbx and nry == nby:
            # 이동 칸 수가 더 작은 쪽이 앞에 있던 구슬임
            if rcnt > bcnt:
                nrx -= dx
                nry -= dy
            else:
                nbx -= dx
                nby -= dy

        loc = (nrx, nry, nbx, nby)
        if loc not in visited:
            visited.add(loc)
            q.append((nrx, nry, nbx, nby, depth + 1))

    if flag:
        break

print(ans)