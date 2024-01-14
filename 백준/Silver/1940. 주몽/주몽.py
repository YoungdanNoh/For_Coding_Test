N,M = [int(input()) for _ in range(2)]
arr = list(map(int, input().split()))

arr.sort()

i = 0
j = N - 1
count = 0

while arr[i] < arr[j]:
    first = arr[i]
    last = arr[j]
    sum_fl = first + last
    
    if sum_fl > M:
        j = j-1
    elif sum_fl < M:
        i = i+1
    else:
        count += 1
        j -= 1
        i += 1
print(count)