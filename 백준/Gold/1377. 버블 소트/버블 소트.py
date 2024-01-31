import sys

N = int(input())
A = []
for i in range(N):
    temp = int(sys.stdin.readline()) # sys로 입력받지 않으면 런타임 에러남..ㅎ
    A.append((temp, i)) # index, 값 받기

changed = False

sort_A = sorted(A)

# 1라운드 당 어느 하나의 값이 왼쪽으로 이동할 수 있는 횟수는 딱 1번이다.
# 따라서 구하려는 값 i(이동이 발생하지 않은 라운드 번호)는 왼쪽으로 이동한 index 값의 최댓값에 +1을 하여 구할 수 있다.
answer = 0

for i in range(N):
    answer = max(sort_A[i][1] - i, answer) # (원본 index 값 - 원래 해당 자리의 index)으로 왼쪽으로 index가 얼마나 이동되었는지 구할 수 있다. 
                                  # 즉, (정렬 전 index - 정렬 후 index)
    
print(answer+1)
