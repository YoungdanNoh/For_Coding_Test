arr = []

N = int(input())

for i in range(N):
    a = int(input())
    arr.append(a)
    
arr.sort()

for i in arr : print(i)