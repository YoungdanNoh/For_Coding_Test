N = int(input())
P = list(map(int,input().split(' ')))

P.sort()

answer = 0
for i in range(N+1):
    for j in range(i):
        answer = answer + P[j]
        
print(answer)