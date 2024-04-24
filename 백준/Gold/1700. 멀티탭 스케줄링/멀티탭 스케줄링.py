n, k = map(int, input().split())
arr = list(map(int, input().split()))

plug = []

while len(plug) < n:
    if arr:
        if arr[0] in plug:
            arr.pop(0)
        else:
            plug.append(arr[0])
            arr.pop(0)
    else:
        break

result = 0
while arr:
    freq = -1
    name = 0
    flag = False
    for p in plug:
        for i in range(len(arr)):
            if p == arr[i]:
                # 현재 플러그에 꼽혀있는 것이 다시 또 사용되는 시기
                if freq < i:
                    freq = i
                    name = p
                break
            if i == (len(arr) - 1) and p not in arr:
                name = p
                flag = True
                break
        if flag:
            break

    for i in arr:
        if arr[0] in plug:
            arr.pop(0)
            break
        else:
            idx = plug.index(name)
            plug.pop(idx) # 가장 나중에 다시 쓰게 될 물건을 뽑는다
            plug.append(arr[0]) # 현재 써야 할 물건을 꼽는다
            arr.pop(0)
            result += 1
            break

print(result)