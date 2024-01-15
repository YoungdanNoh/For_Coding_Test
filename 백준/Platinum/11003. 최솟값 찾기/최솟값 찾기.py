from collections import deque

N,L = map(int, input().split())
nums = list(map(int, input().split()))

q = deque([])

for i in range(N):
    while q and q[-1][1] > nums[i]:
        # q에 값이 있고, 가장 최근에 들어온 값이 현재 값보다 크다면 가장 최근값을 제거한다.
        q.pop()
    
    q.append((i+1, nums[i]))
    
    while q and q[-1][0] - q[0][0] >= L:
        # q에 값이 있고, (최근에 들어온 값의 번호 - 가장 오래된 값의 번호)가 L보다 크거나 같다면 범위를 벗어난 것이므로 왼쪽에서 제거한다.
        # ex) L=3 이라면 (i-L+1) ~ i범위에서 최솟값을 구해야 함(이때 i<=0인 숫자는 무시한다.)
        # 따라서 -1~1, 0~2, 1~3, 2~4 ... 따라서 L의 길이만큼 범위가 정해진다.
        q.popleft()
    
    print(q[0][1], end=" ")