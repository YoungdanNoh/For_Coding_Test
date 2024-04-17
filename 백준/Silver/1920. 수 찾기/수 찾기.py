n = int(input())
n_arr = list(map(int, input().split()))
n_arr.sort()
m = int(input())
m_arr = list(map(int, input().split()))

for i in m_arr:
    start = 0
    end = n - 1
    flag = False

    while start <= end:
        mid = (start + end) // 2
        if n_arr[mid] == i:
            print(1)
            flag = True
            break
        if n_arr[mid] >= i:
            end = mid - 1
        else:
            start = mid + 1

    if not flag:
        print(0)