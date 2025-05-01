import copy
import sys

# 중앙에 1개를 두고 시계 방향 회전을 하는 형태
# K개의 연산이 들어오면 -> 가능한 순서를 모두 구해 배열을 돌린 후 가장 최소 값을 구한다.

N, M, K = map(int, sys.stdin.readline().split())

arr = []

for i in range(N):
    arr.append(list(map(int, sys.stdin.readline().split())))

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0] # 오른쪽, 아래, 왼쪽, 위

cal = []
for i in range(K):
    cal.append(list(map(int, sys.stdin.readline().split())))


result = 1e9
def rotate():
    global result
    arr2 = copy.deepcopy(arr)

    for i in range(K):
        r = cal[select[i]][0]
        c = cal[select[i]][1]
        s = cal[select[i]][2]

        visited = [[False]*M for _ in range(N)]

        sx, ex = r-s-1, r+s-1
        sy, ey = c-s-1, c+s-1
        #print("sx: ", sx, " ex: ", ex, " sy: ", sy, " ey: ", ey)

        tx, ty = sx, sy
        tmp = arr2[tx][ty]
        for S in range(s):
            if(S > 0):
                tx += 1
                ty += 1
                tmp = arr2[tx][ty]
            for j in range(4):
                while True:
                    nx = tx + dx[j]
                    ny = ty + dy[j]
                    #print("tx: ", tx, " ty: ", ty)
                    #print("nx: ", nx, " ny: ", ny)
                    #print("ex: ", ex, " ey: ", ey)

                    if(nx<sx or nx>ex or ny<sy or ny>ey):
                        # 범위 내가 아니라면
                        break
                    if(visited[nx][ny]):
                        # 이미 방문한 곳이라면
                        break

                    tmp2 = arr2[nx][ny]
                    arr2[nx][ny] = tmp
                    tmp = tmp2

                    tx, ty = nx, ny
                    visited[nx][ny] = True

            # for i in range(N):
            #     print(arr2[i])
            # print("=============================")

    for i in range(N):
        rs = sum(arr2[i])
        result = min(result, rs)



isSelected = [False]*K
select = [0]*K
def perm(cnt):
    if(cnt == K):
        # 이 순서로 진행해보기
        #print(select)
        rotate()
        return
    for i in range(K):
        if(isSelected[i]):
            continue

        select[cnt] = i
        isSelected[i] = True

        perm(cnt+1)
        isSelected[i] = False
perm(0)

print(result)