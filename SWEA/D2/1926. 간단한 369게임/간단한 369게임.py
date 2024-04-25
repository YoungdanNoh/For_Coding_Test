n = int(input())
num = ['3', '6', '9']

for i in range(1, n+1):
    arr = list(str(i))
    flag = False

    for a in range(len(arr)):
        if arr[a] in num:
            print("-", end="")
            flag = True

    if flag:
        print(" ", end="")
    else:
        print(''.join(arr), end=" ")