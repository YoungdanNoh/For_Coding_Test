t = int(input())

for i in range(t):
    arr = list(map(int, input().split()))
    result = 0
    for a in arr:
        if a % 2 == 1:
            result += a
    print("#%d %d" % ((i+1), result))