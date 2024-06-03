n = int(input())

house = list(map(int, input().split()))
house.sort()

idx = (n - 1) // 2

print(house[idx])