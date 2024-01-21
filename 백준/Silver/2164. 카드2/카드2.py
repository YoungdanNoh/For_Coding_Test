from collections import deque

N = int(input())
card = deque([i+1 for i in range(N)])
cnt = 0

while len(card) != 1:
    cnt += 1
    if cnt % 2 == 1:
        card.popleft()
    else:
        a = card.popleft()
        card.append(a)
print(card[0])