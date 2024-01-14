N = int(input())
arr = list(map(int, input().split()))

arr.sort()
origin = arr.copy()
count = 0
for i in range(N):
    temp = arr.pop(i)
    
    x = 0
    y = len(arr)-1

    while x < y:
        first = arr[x]
        last = arr[y]
        sum_fl = first + last
        
        if sum_fl > temp:
            y = y-1
        elif sum_fl < temp:
            x = x+1
        else:
            count += 1
            break
    arr = origin.copy()
print(count)