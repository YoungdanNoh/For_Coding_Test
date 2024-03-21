def solution(numbers, target):
    tot = [0]
    
    for i in range(len(numbers)):
        temp = []
        
        for j in tot:
            temp.append(j + numbers[i])
            temp.append(j - numbers[i])
        tot = temp
        
    cnt = 0
    for t in tot:
        if t == target:
            cnt += 1
    print(cnt)
    return cnt