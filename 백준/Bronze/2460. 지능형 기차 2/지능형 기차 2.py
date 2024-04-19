arr = []

for _ in range(10):
    arr.append(list(map(int, input().split())))

people = []
cnt = 0
for i in range(10):
    cnt -= arr[i][0]
    cnt += arr[i][1]
    people.append(cnt)

print(max(people))