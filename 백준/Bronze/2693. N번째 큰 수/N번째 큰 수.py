t = int(input())

for _ in range(t):
    arr = list(map(int, input().split()))
    arr.sort(reverse=True)
    print(arr[2])