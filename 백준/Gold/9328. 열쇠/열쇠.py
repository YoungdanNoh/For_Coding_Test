import sys
from collections import deque

input = sys.stdin.readline

T = int(input())

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

for _ in range(T):
    ans = 0

    h, w = map(int, input().split())

    board = [['.' for i in range(w+2)]]

    for i in range(h):
        tmp = list(input().strip())
        board.append(['.']+tmp+['.'])
    board.append(['.' for i in range(w+2)])

    key = list(input().strip())

    # (0, 0)에서 bfs 시작
    q = deque()
    visited = [[False]*(w+2) for i in range(h+2)]

    q.append((0, 0))
    visited[0][0] = True

    waiting = []

    while q:
        cur = q.popleft()

        for i in range(4):
            tx = cur[0] + dx[i]
            ty = cur[1] + dy[i]

            if tx<0 or tx>=(h+2) or ty<0 or ty>=(w+2):
                continue
            if visited[tx][ty]:
                continue

            if board[tx][ty] == '*':
                # 벽이라면 건너뛰기
                continue
            elif board[tx][ty] == '.':
                # 빈 공간
                visited[tx][ty] = True
                q.append((tx, ty))
            elif board[tx][ty] == '$':
                # 문서가 있는 곳
                ans += 1
                visited[tx][ty] = True
                board[tx][ty] = '.'
                q.append((tx, ty))
            elif ord(board[tx][ty]) < 97:
                # 문이 있는 곳
                ap = ord(board[tx][ty]) + 32 # 키의 아스키 코드 값

                if chr(ap) in key:
                    # 해당 키를 가지고 있다면 문 열 수 있음
                    visited[tx][ty] = True
                    board[tx][ty] = '.'
                    q.append((tx, ty))
                else:
                    # 해당 위치는 따로 담아두어 대기
                    waiting.append((tx, ty))
            else:
                # 키를 획득
                key.append(board[tx][ty])
                board[tx][ty] = '.'
                visited[tx][ty] = True
                q.append((tx, ty))

                remove_list = []
                for x, y in waiting:
                    ap = ord(board[x][y]) + 32

                    if chr(ap) in key:
                        # 해당 키를 가지고 있다면 문 열 수 있음
                        visited[x][y] = True
                        board[x][y] = '.'
                        q.append((x, y))
                        remove_list.append((x, y))

                for r in remove_list:
                    waiting.remove(r)

    print(ans)