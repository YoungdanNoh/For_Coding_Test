import bisect
import sys

input = sys.stdin.readline

T = int(input())
n = int(input())
A = list(map(int, input().split()))
m = int(input())
B = list(map(int, input().split()))

Asum = []
Bsum = []

for i in range(n):
    s = A[i]
    Asum.append(s)
    for j in range(i+1, n):
        s += A[j]
        Asum.append(s)

for i in range(m):
    s = B[i]
    Bsum.append(s)
    for j in range(i+1, m):
        s += B[j]
        Bsum.append(s)

Asum.sort()
Bsum.sort()

ans = 0

for i in range(len(Asum)):
    l = bisect.bisect_left(Bsum, T-Asum[i])
    r = bisect.bisect_right(Bsum, T-Asum[i])
    ans += r-l

print(ans)