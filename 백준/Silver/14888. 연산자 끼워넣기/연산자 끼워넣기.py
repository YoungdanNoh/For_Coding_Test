import itertools

N = int(input())
arr_a = list(map(int, input().split()))

# 순서대로 +, -, x, / 의 개수
op_in = list(map(int,input().split()))

op = []
for i in range(4):
    for j in range(op_in[i]):
        if i == 0:
            op.append("+")
        elif i == 1:
            op.append("-")
        elif i == 2:
            op.append("*")
        else:
            op.append("/")

op = list(set(list(itertools.permutations(op, N-1))))

def cal(op, a, b):
    if op == "+":
        return a + b
    elif op == '-':
        return a - b
    elif op == "*":
        return a * b
    else:
        if a < 0:
            return -((-a) // b)
        else:
            return a // b

result = []
for o in range(len(op)):
    tmp = 0
    for i in range(1, N):
        if i == 1:
            tmp = cal(op[o][i-1], arr_a[i-1], arr_a[i])
        else:
            tmp = cal(op[o][i-1], tmp, arr_a[i])
    result.append(tmp)

print(max(result))
print(min(result))