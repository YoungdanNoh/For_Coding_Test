import sys

input = sys.stdin.readline

n, m = map(int, input().split()) # n: 나무의 수, m: 집으로 가져가려고 하는 나무의 길이

tree = list(map(int, input().split())) # 나무들의 높이

start = 0
end = max(tree)

result = 0

while start <= end:
    mid = (start + end) // 2 # 절단기의 높이 설정

    carry = 0
    for t in tree:
        if t > mid:
            carry += (t - mid) # 절단기로 자른 후 가져갈 수 있는 나무의 길이를 더한다.

    if carry >= m:
        result = mid
        start = mid + 1
    else:
        end = mid - 1

print(result)