import sys

input = sys.stdin.readline

N = int(input())
result = [0]*N
isSelected = [False]*N

def perm(cnt):
    if cnt == N:
        for i in result:
            print(i, end=" ")
        print()
        return

    for i in range(N):
        if isSelected[i]:
            continue

        isSelected[i] = True
        result[cnt] = i+1
        perm(cnt+1)

        isSelected[i] = False

perm(0)