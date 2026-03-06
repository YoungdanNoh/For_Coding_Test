import sys

input = sys.stdin.readline

N, M = map(int, input().split())
arr = []
for i in range(1, N+1):
    arr.append(i)
result = ['']*M

def combi(cnt, start):
    if cnt == M:
        for i in result:
            print(i, end=" ")
        print()
        return

    for i in range(start, N):
        result[cnt] = arr[i]
        combi(cnt+1, i+1)

combi(0, 0)