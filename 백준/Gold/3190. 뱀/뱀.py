from collections import deque

n = int(input())
k = int(input())

board = [[0]*n for _ in range(n)] #사과의 위치를 담는다.
board[0][0] = 1 #처음 위치는 무조건 방문처리

for _ in range(k):
    a, b = map(int, input().split())
    board[a-1][b-1] = 5 #사과의 위치에 5라는 값을 넣는다.

l = int(input()) #뱀의 방향 변환 횟수
X = []
move = []
for _ in range(l):
    a, b = input().split()
    X.append(int(a)) #x초 후 방향전환
    move.append(b) #'L'은 왼쪽으로 90도 방향전환
                   #'D'는 오른쪽으로 90도 방향전환

dx = [0, -1, 0, 1]
dy = [1, 0, -1, 0]
# 순서대로 오, 위, 왼, 아
direc = 0 #처음 방향은 오른쪽
x, y = 0, 0 #뱀의 머리 좌표
q = deque() #뱀의 위치를 담을 큐
q.append((x, y))
time = 1 #게임시간

def turn(d, m):
    if m == 'L':
        d += 1
        if d > 3:
            d = 0
    elif m == 'D':
        d -= 1
        if d < 0:
            d = 3
    return d

while True:
    # 방향 전환 시
    if (time-1) in X:
        direc = turn(direc, move[X.index(time-1)])

    x = x + dx[direc]
    y = y + dy[direc]

    if x < 0 or x >= n or y < 0 or y >= n:
        break
    if board[x][y] != 5 and board[x][y] == 0:
        # 사과가 없고
        # 자기자신의 몸과 부딪히지 않았다면 방문처리한다.
        time += 1

        board[x][y] = 1
        q.append((x, y))
        tx, ty = q.popleft()
        board[tx][ty] = 0

    elif board[x][y] == 5:
        # 사과가 있다면
        time += 1

        q.append((x, y))
    else:
        break

print(time)