a, b = map(int, input().split())
arr = []

cnt = 1
while len(arr) < b:
    for _ in range(cnt):
        arr.append(cnt)
    cnt += 1

hap = 0
for i in range(a-1, b):
    hap += arr[i]

print(hap)