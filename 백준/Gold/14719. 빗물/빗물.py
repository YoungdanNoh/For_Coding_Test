h, w = map(int, input().split())
b = list(map(int, input().split()))

block = [[0]*w for i in range(h)]

y = 0
for i in b:
    for x in range(i):
        block[h-1-x][y] = 5 # 블록이 있는 곳을 5로 표기한다
    y += 1

s_y = 0 # 시작 y좌표 위치를 담는다
temp = b[0] # 현재 높이를 담는다
cnt = 0 # 빗물이 고이는 총량을 담는다
flag = False

for i in range(1, w):
    if flag:
        if temp < b[i-1]:
            # 빗물이 고이는 곳을 체크 후
            # 현재 블럭의 높이보다 이전 단계의 블럭의 높이가 더 크다면
            # 이전 단계의 블럭의 높이와 좌표를 이용한다
            s_y = i - 1
            temp = b[i - 1]
        flag = False

    if 1 <= b[i]:
        # 블록의 높이가 1 이상일 경우에만 빗물을 가둘 수 있다
        idx = min(b[s_y], b[i]) # 둘 중 작은 값의 높이만큼 빗물이 고인다
        for x in range(idx):
            for y in range(s_y+1, i):
                if block[h - 1 - x][y] == 0:
                    # 해당 위치에 블록이 없고, 빗물이 고인 적도 없다면
                    # 빗물이 고이는 곳이다
                    cnt += 1
                    block[h - 1 - x][y] = 1
        flag = True
print(cnt)