import sys

read = sys.stdin.readline
K, L = map(int, read().split())
arr = []

for _ in range(L):
    arr.append(read().strip())

arr.reverse()
tmp = set()
new_arr = []
for a in arr:
    if a not in tmp:
        tmp.add(a)
        new_arr.append(a)

new_arr.reverse()

for i in range(K):
    if len(new_arr) > i:
        print(new_arr[i])