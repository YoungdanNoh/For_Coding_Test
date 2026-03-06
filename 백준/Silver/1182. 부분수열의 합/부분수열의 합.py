import sys

input = sys.stdin.readline

N, S = map(int, input().split())
arr = list(map(int, input().split()))
isSelected = [False]*N

ans = 0
def subSet(cnt):
    global ans

    if cnt == N:
        tmp = 0
        for i in range(len(arr)):
            if isSelected[i]:
                tmp += arr[i]

        if tmp == S:
            ans += 1

        return

    isSelected[cnt] = True
    subSet(cnt+1)
    isSelected[cnt] = False
    subSet(cnt+1)

subSet(0)
if S == 0:
    print(ans - 1)
else:
    print(ans)