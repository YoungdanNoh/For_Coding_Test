#import numpy as np

N, M = map(int, input().split())
board = []
color = []

for i in range(N):
    board.append(list(input()))
#board = np.array(board) #numpy는 이중리스트 슬라이싱 기능을 제공한다.

def check(c, color):
    cnt = 0
    for x in range(8):
        for y in range(8):
            if c == temp[x][y]:  # 처음에는 맨 왼쪽 위칸이 흰색인 경우를 가정하여 계산한다.
                cnt += 1  # 칠해야 할 칸의 수
                if temp[x][y] == 'B':
                    c = 'W'
                else:
                    c = 'B'
            else:
                c = temp[x][y]  # 현재 칸의 색
            if y == 7:  # 다음줄은 현재 색과 같은 색으로 시작해야 하므로 색을 반전시킨다.
                if c == 'B':
                    c = 'W'
                else:
                    c = 'B'

    color.append(cnt)

for n in range(N):
    for m in range(M):
        if (n+8) > N or (m+8) > M:
            continue
        #temp = board[n:n+8, m:m+8]
        temp = []
        for x in range(n, n+8):
            temp.append(board[x][m:m+8])

        check('B', color)
        check('W', color)

print(min(color))