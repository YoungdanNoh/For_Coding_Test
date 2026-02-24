import sys
from collections import deque

input = sys.stdin.readline

N, M, K = map(int, input().split())
graph = []

for _ in range(N):
    graph.append(list(map(int, input().strip())))

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

visited = [[[False] * (K+1) for _ in range(M)] for _ in range(N)]
''' (x, y) 좌표를 몇개의 벽을 부숴서 도착했는지 구별하여 방문처리를 해주기 위해 
    visited[N][M][K+1]의 배열을 만들어준다.
'''
q = deque()
visited[0][0][0] = True
q.append((0, 0, 1, 0))  # x, y, 이동횟수, 벽을 부순 횟수

ans = 1e9
while q:
    cur = q.popleft()

    if cur[0] == (N - 1) and cur[1] == (M - 1):
        # 목적지 도착
        '''
        큐에서 pop되는 순서는 거리(dist)가 작은 것부터다.
        그래서 목표 지점을 처음 만나는 dist가 최단거리다.
        '''
        ans = min(ans, cur[2])
        break

    for i in range(4):
        tx = cur[0] + dx[i]
        ty = cur[1] + dy[i]

        if tx < 0 or tx >= N or ty < 0 or ty >= M:
            continue
        if graph[tx][ty] == 0 and visited[tx][ty][cur[3]]:
            # 해당 위치에 벽이 없고, 이미 방문한 상태라면
            continue
        if graph[tx][ty] == 1 and cur[3] < K and visited[tx][ty][cur[3]+1]:
            # 해당 위치에 벽이 있고, 이미 방문한 상태라면
            continue

        if graph[tx][ty] == 0:
            # 벽이 없는 곳을 다음으로 방문
            visited[tx][ty][cur[3]] = True
            q.append((tx, ty, cur[2] + 1, cur[3]))
        elif graph[tx][ty] == 1 and cur[3] < K:
            # 해당 위치는 벽이지만 부술 수 있는 횟수가 남았을 때
            visited[tx][ty][cur[3]+1] = True
            q.append((tx, ty, cur[2] + 1, cur[3] + 1))

if ans == 1e9:
    print(-1)
else:
    print(ans)
