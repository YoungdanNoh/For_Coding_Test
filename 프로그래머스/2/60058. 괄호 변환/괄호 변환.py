def solution(p):
    result = ""

    if p == "":
        return ""

    a = 0
    b = 0
    for i in range(len(p)):
        if p[i] == '(':
            a += 1
        if p[i] == ')':
            b += 1
        if a == b:
            u = list(p[0:i + 1])
            if (i + 1) == len(p):
                v = ""
            else:
                v = p[i + 1:]
            break

    stack = ['(']
    for i in range(1, len(u)):
        if len(stack) == 0:
            stack.append(u[i])
        elif stack[-1] != u[i]:
            stack.pop()
        else:
            stack.append(u[i])

    if len(stack) == 0 and u[0] == "(":
        # u는 "올바른 괄호 문자열"
        result += ''.join(u) + solution(v)
    else:
        result += "("
        result += solution(v)
        result += ")"

        u.pop(0)
        u.pop(len(u) - 1)
        for i in range(len(u)):
            if u[i] == "(":
                result += ")"
            else:
                result += "("
    return result