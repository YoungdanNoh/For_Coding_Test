N = int(input())
nums = list(map(int, input().split()))

stack = []
nge = [-1]*N

for i in range(N):
    while stack and stack[-1][1] < nums[i]:
        nge[stack[-1][0]] = nums[i]
        stack.pop() 
    stack.append((i, nums[i]))
    
print(*nge)