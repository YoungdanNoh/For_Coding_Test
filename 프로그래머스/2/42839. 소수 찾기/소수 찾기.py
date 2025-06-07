def solution(numbers):
    # 순열 문제
    
    def perm(cnt, r):
        global answer 
        
        if(cnt == r):
            num = int(''.join(result))
            
            # 이제 소수 판별 시작
            if(num < 2):
                return
            
            for i in range(2, num):
                if(num%i == 0):
                    return
            
            numlist.append(num)
            return
            
        for i in range(len(numbers)):
            if(isSelected[i]):
                continue
            
            isSelected[i] = True # 선택하거나
            result[cnt] = numbers[i]
            perm(cnt+1, r)
            
            isSelected[i] = False # 안 하거나
    
    numlist = []
    for i in range(1, len(numbers)+1):
        # 1개 ~ numbers 원소 갯수만큼의 숫자를 순열로 뽑음
        result = [0 for _ in range(i)]
        isSelected = [False] * len(numbers)
        perm(0, i)
        
    tmp = set(numlist)
        
    return len(tmp)