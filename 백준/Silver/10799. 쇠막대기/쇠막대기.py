bar = input()

stack = ['(']
cnt = 0
for b in range(1, len(bar)):
    if bar[b] == '(':
        # 현재 괄호가 '('라면 쇠막대의 시작이거나 레이저의 시작이다.
        stack.append('(')
    else:
        if bar[b - 1] == '(':
            # 현재 괄호가 ')'이고 이전 괄호가 '('이라면 레이저이다.
            stack.pop()
            cnt += len(stack)
        else:
            # 현재 괄호가 ')'이고 이전 괄호가 ')'이라면 쇠막대의 끝이다.
            stack.pop()
            cnt += 1

print(cnt)