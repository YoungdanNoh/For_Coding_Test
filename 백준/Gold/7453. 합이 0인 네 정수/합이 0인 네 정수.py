import sys
from collections import defaultdict

read = sys.stdin.readline

N = int(read())
A = []
B = []
C = []
D = []

for _ in range(N):
    tmp = list(map(int, read().strip().split()))
    A.append(tmp[0])
    B.append(tmp[1])
    C.append(tmp[2])
    D.append(tmp[3])

AB = []
CD = defaultdict(int)

for a in A:
    for b in B:
        AB.append(a+b)

for c in C:
    for d in D:
        CD[c+d] += 1

ans = 0
for ab in AB:
    # CD에서 -ab인 값을 찾으면 더했을 때 0이 된다.
    if -ab in CD:
        ans += CD[-ab]

print(ans)