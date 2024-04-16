a, p = map(int, input().split())

arr = [a]
a = list(str(a))

while True:
    result = 0
    for i in range(len(a)):
        result += int(a[i]) ** p
    arr.append(result)

    if result in arr[:-1]:
        break
    a = list(str(arr[-1]))

print(arr.index(result))