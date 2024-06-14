N = int(input())

arr = [0]*(N+1)
#  0 1 2 3 4 5 6 7 8 ...
# [0 1 1                 ] 첫번째 인덱스 값은 사용하지 않음

for i in range(2, N+1):
    arr[i] = arr[i-1] + 1 # 1을 더하여 만드는 경우

    if i%2 == 0:
        # 2로 나누어 떨어지는 경우
        arr[i] = min(arr[i], (arr[i//2] + 1))

    if i%3 == 0:
        # 3으로 나누어 떨어지는 경우
        arr[i] = min(arr[i], (arr[i//3] + 1))

print(arr[N])