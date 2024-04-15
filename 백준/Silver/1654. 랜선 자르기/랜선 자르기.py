k, n = map(int, input().split()) # k: 가지고 있는 랜선 개수, n: 필요한 랜선 개수

lan = [] # 각 랜선의 길이를 저장
for _ in range(k):
    lan.append(int(input()))

start = 1
end = max(lan)

while start <= end:
    mid = (start + end) // 2

    lan_n = 0 # 만들 수 있는 랜선의 개수
    for i in range(k):
        lan_n += lan[i] // mid

    if lan_n >= n:
        start = mid + 1
    else:
        end = mid - 1

print(end)