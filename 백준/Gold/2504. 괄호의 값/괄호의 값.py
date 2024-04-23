brk = input()

s = []
tmp = 1
result = 0

for i in range(len(brk)):
    if brk[i] == '(':
        s.append(brk[i])
        tmp *= 2
    elif brk[i] == '[':
        s.append(brk[i])
        tmp *= 3
    elif brk[i] == ')':
        if not s or s[-1] != '(':
            # 스택이 비었거나 현재 스택의 최신 값과 쌍을 이루지 않는다면 올바른 괄호열이 아니다.
            result = 0
            break
        elif brk[i-1] == '(':
            result += tmp
        s.pop()
        tmp //= 2 # 괄호가 끝났으므로 해당 값만큼 나눠준다.
    else:
        if not s or s[-1] != '[':
            # 스택이 비었거나 현재 스택의 최신 값과 쌍을 이루지 않는다면 올바른 괄호열이 아니다.
            result = 0
            break
        elif brk[i-1] == '[':
            result += tmp
        s.pop()
        tmp //= 3  # 괄호가 끝났으므로 해당 값만큼 나눠준다.

if s:
    # 모든 반복문을 지났는데도 스택에 값이 남아있다면 '('나 '['로 끝난 올바르지 않은 괄호열이다.
    print(0)
else:
    print(result)