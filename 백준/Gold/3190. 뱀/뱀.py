n = int(input())
k = int(input())

board = [[0]*n for _ in range(n)] #사과의 위치를 담는다.
board[0][0] = 1 #처음 위치는 무조건 방문처리

for _ in range(k):
    a, b = map(int, input().split())
    board[a-1][b-1] = -1 #사과의 위치에 5라는 값을 넣는다.

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
tail = [0, 0] #뱀의 꼬리 위치
time = 1 #게임 시간
flag = False #이중 반복문을 종료할지 판단한다.
m_tail = 1
snake = 1
for n1 in range(n):
    for n2 in range(n):
        if (time-1) in X:
            if move[X.index(time-1)] == 'L':
                # 왼쪽으로 회전
                direc += 1
                if direc > 3:
                    direc = 0
            elif move[X.index(time-1)] == 'D':
                # 오른쪽으로 회전
                direc -= 1
                if direc < 0:
                    direc = 3
        x = x + dx[direc]
        y = y + dy[direc]
        if x >= 0 and x < n and y >= 0 and y < n:
            if board[x][y] != -1 and board[x][y] <= 0:
                # 자기자신의 몸과 부딪히지 않았다면 방문처리한다.
                time += 1

                board[x][y] = snake
                board[tail[0]][tail[1]] = 0
                snake += 1

                for b in range(n):
                    if m_tail in board[b]:
                        t_x = b
                        t_y = board[b].index(m_tail)
                tail = [t_x, t_y]
                m_tail += 1

            elif board[x][y] == -1 and board[x][y] <= 0:
                # 사과가 있고 벽이나 자기자신의 몸과 부딪히지 않았다면 방문처리한다.
                time += 1

                board[x][y] = snake
                snake += 1
            else:
                # 자기자신의 몸과 부딪혔다면 게임을 끝낸다.
                flag = True
                break
        else:
            # 벽에 부딪혔다면 게임을 끝낸다.
            flag = True
            break
    if flag:
        break
print(time)