# 상:1, 하:2, 좌:3, 우:4

t = int(input())

dx = [0, -1, 1, 0, 0]
dy = [0, 0, 0, -1, 1]
tbl = [0, 2, 1, 4, 3]

for tc in range(t):
    n, m, k = map(int, input().split())

    arr = []
    for i in range(k):
        arr.append(list(map(int, input().split())))

    for _ in range(m):
        # 1칸 이동처리, 경계면이라면 //2 & 방향 반대로
        for i in range(len(arr)):
            arr[i][0] = arr[i][0] + dx[arr[i][3]] # x좌표 변경
            arr[i][1] = arr[i][1] + dy[arr[i][3]] # y좌표 변경

            if arr[i][0] == 0 or arr[i][0] == n-1 or arr[i][1] == 0 or arr[i][1] == n-1:
                # 경계면이라면
                arr[i][2] //= 2 # 미생물 절반
                arr[i][3] = tbl[arr[i][3]] # 방향 반대

        # 1시간 이동 후 내림차순으로 정렬한다
        arr.sort(key = lambda x: (x[0], x[1], x[2]), reverse=True)
        # x, y 좌표가 같다면 크기를 내림차순으로 정렬한다.

        # 같은 좌표끼리는 합친다
        i = 1
        while i < len(arr):
            if arr[i-1][0:2] == arr[i][0:2]:
                arr[i-1][2] += arr[i][2]
                arr.pop(i)
            else:
                i += 1

    result = 0
    for a in arr:
        result += a[2]

    print("#{0} {1}".format((tc+1), result))