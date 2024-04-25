t = int(input())

for i in range(t):
    n = int(input())
    arr = list(map(int, input().split()))
    
    max_c = arr[-1]
    money = 0
    
    for a in range(n-2, -1, -1):
        if arr[a] >= max_c:
            max_c = arr[a]
        else:
            money = money + (max_c - arr[a])
    print("#{0} {1}".format((i+1), money))