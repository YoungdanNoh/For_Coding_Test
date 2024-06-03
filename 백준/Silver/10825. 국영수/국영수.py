n = int(input())

grade = []

for _ in range(n):
    grade.append(input().split())

grade.sort(key = lambda x: (-int(x[1]), int(x[2]), -int(x[3]), x[0]))

for i in grade:
    print(i[0])