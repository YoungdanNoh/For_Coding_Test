# 빌딩 안팎으로 이동 가능
# 따라서 전체 맵을 .으로 감싸서 어느 곳에서든 움직일 수 있도록 만들어준다.
import sys
from collections import deque

input = sys.stdin.readline
T = int(input())

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

for t in range(T):
    h, w = map(int, input().strip().split())

    graph = [['.' for _ in range(w+2)]]
    for i in range(h):
        tmp = list(input().strip())
        graph.append(['.'] + tmp + ['.'])
    graph.append(['.' for _ in range(w+2)])

    keys = set(input().strip())

    # (0, 0)에서 부터 bfs 시작
    visited = [[False]*(w+2) for _ in range(h+2)]
    q = deque()
    q.append((0, 0))
    visited[0][0] = True

    waiting = []
    ans = 0
    while q:
        cur = q.popleft()

        for i in range(4):
            tx = cur[0] + dx[i]
            ty = cur[1] + dy[i]

            if tx<0 or tx>=(h+2) or ty<0 or ty>=(w+2):
                continue
            if visited[tx][ty]:
                continue

            if graph[tx][ty] == '.':
                # 빈 공간
                q.append((tx, ty))
                visited[tx][ty] = True
            elif graph[tx][ty] == '*':
                # 벽
                continue
            elif graph[tx][ty] == '$':
                # 문서
                ans += 1
                q.append((tx, ty))
                graph[tx][ty] = '.'
                visited[tx][ty] = True
            elif 'A' <= graph[tx][ty] <= 'Z':
                # 문
                if chr(ord(graph[tx][ty]) + 32) in keys:
                    # 키를 가지고 있다면 방문 가능
                    q.append((tx, ty))
                    graph[tx][ty] = '.'
                    visited[tx][ty] = True
                else:
                    # 다음에 키를 획득할 수 있으므로 일단 저장
                    waiting.append((tx, ty))
            else:
                # 키
                keys.add(graph[tx][ty])
                graph[tx][ty] = '.'
                visited[tx][ty] = True
                q.append((tx, ty))

                for wait in range(len(waiting)-1, -1, -1):
                    x = waiting[wait][0]
                    y = waiting[wait][1]
                    if chr(ord(graph[x][y]) + 32) in keys:
                        # 키를 가지고 있다면 방문 가능
                        q.append((x, y))
                        graph[x][y] = '.'
                        visited[x][y] = True
                        waiting.pop(wait) # 해당 장소를 웨이팅 리스트에서 삭제

    print(ans)