t = int(input())

for i in range(t):
    n = int(input())
    
    arr = list(map(int, input().split()))
    arr.sort()
    max_cnt = 0
    max_num = 0
    for a in range(len(arr)):
        temp = arr.count(arr[a])
        if temp >= max_cnt: #최빈수가 여러개일 때는 가장 큰 값으로
            max_cnt = temp
            max_num = arr[a]
            a = a + temp
            
    print("#{0} {1}".format(n, max_num))