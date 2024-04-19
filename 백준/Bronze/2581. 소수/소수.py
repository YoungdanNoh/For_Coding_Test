m = int(input())
n = int(input())

arr = []
for i in range(m, n+1):
    flag = False
    if i == 1:
        continue
    if i == 2:
        arr.append(i)
    for j in range(2, i):
        if i % j == 0:
            flag = True
            break
    if not flag and i not in arr:
        arr.append(i)

if len(arr) > 0:
    print(sum(arr))
    print(min(arr))
else:
    print(-1)