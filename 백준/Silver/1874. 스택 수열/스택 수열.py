n = int(input())
nums = [int(input()) for _ in range(n)]

stack = []
answer = []
x = 1
flag = 0

for i in range(n):
    while nums[i] >= x:
        # 현재 값이 x보다 크거나 같다면 값을 append
        stack.append(x)
        x += 1
        answer.append('+')
        
    if nums[i] == stack[-1]:
        # stack 끝 값과 같을 때
        stack.pop()
        answer.append('-')
    else:
        print('NO')
        flag = 1
        break
        
if flag == 0:
    for a in answer:
        print(a)