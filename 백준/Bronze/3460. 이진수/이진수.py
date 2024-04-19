t = int(input())

for _ in range(t):
    arr = []
    n = int(input())

    while n > 1:
        temp = n % 2
        n = n // 2
        arr.append(temp)

    arr.append(n)
    for i in range(len(arr)):
        if arr[i] == 1:
            print(i, end = ' ')