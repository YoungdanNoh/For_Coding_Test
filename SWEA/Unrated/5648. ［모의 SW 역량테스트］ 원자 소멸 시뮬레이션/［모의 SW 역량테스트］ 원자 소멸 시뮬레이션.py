t = int(input())

# 상(0), 하(1), 좌(2), 우(3)
dx = [0, 0, -1, 1]
dy = [1, -1, 0, 0]
for tc in range(t):
    n = int(input())

    arr = []
    for i in range(n):
        arr.append(list(map(int, input().split())))
        # [0]:x좌표, [1]:y좌표, [2]:이동방향, [3]:보유 에너지
    for i in range(len(arr)):
        arr[i][0] *= 2
        arr[i][1] *= 2

    result = 0
    for _ in range(4001):
        for i in range(len(arr)):
            arr[i][0] = arr[i][0] + dx[arr[i][2]] # x좌표 변경
            arr[i][1] = arr[i][1] + dy[arr[i][2]]  # y좌표 변경

        # 중복좌표를 찾는다.
        v = set()
        ddel = set()
        for i in range(len(arr)):
            if (arr[i][0], arr[i][1]) in v:
                ddel.add((arr[i][0], arr[i][1]))
            else:
                v.add((arr[i][0], arr[i][1]))

        # 중복 좌표들은 삭제한다.
        for i in range(len(arr)-1, -1, -1):
            if arr[i][0] < -2000 or arr[i][0] > 2000 or arr[i][2] < -2000 or arr[i][2] > 2000:
                arr.pop(i)
            elif (arr[i][0], arr[i][1]) in ddel:
                result += arr[i][3]
                arr.pop(i)

    print("#{0} {1}".format((tc+1), result))