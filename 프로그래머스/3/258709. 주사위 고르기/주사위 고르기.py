def solution(dice):
    global Dice, n, selected, maxWin, answer
    
    Dice = dice
    n = len(dice)
    selected = [False for i in range(n)]
    maxWin = 0
    answer = []
    
    combi(0, 0)
    
    return answer

def combi(cnt, start):
    global maxWin, answer
    
    if cnt == (n/2):
        # A 주사위 다 고름
        aIdx = [] # A 주사위의 Dice 인덱스들
        bIdx = [] # B 주사위의 Dice 인덱스들
        
        for k in range(n):
            if selected[k]:
                aIdx.append(k)
            else:
                bIdx.append(k)
        
        # 1. A의 주사위를 모두 굴려 나올 수 있는 모든 조합을 구한다.
        aList = []
        dfs(0, aList, 0, aIdx)
        
        # 2. B의 주사위를 모두 굴려 나올 수 있는 모든 조합을 구한다.
        bList = []
        dfs(0, bList, 0, bIdx)
        
        # 3. A가 승리하는 경우의 수를 센다.
        bList.sort()
        
        win = 0
        for a in aList:
            # 각 aList의 값에 대해서 bList가 지는 경우의 수를 구한다.
            win += lowerbound(a, bList)
                    
        # maxWin보다 현재의 win 값이 더 크다면 A 주사위의 조합 변경
        if maxWin < win:
            maxWin = win
            answer = []
            
            for i in range(n):
                if selected[i]:
                    answer.append(i+1)
        
        return
    
    for i in range(start, n):
        selected[i] = True
        combi(cnt+1, i+1)
        selected[i] = False
        
def dfs(cnt, arr, s, idx):
    
    if cnt == (n/2):
        # 마지막 주사위까지 다 돌렸다면
        arr.append(s)
        return
    
    for i in range(6):
        # idx[cnt]번째 주사위의 i번째 숫자를 선택
        num = idx[cnt]
        dfs(cnt+1, arr, s+Dice[num][i], idx)
    
def lowerbound(a, bList):
    start = 0
    end = len(bList) - 1
    result = len(bList)
    
    while start <= end:
        mid = (start+end) // 2
        
        if bList[mid] >= a:
            result = mid
            end = mid - 1
        else:
            start = mid + 1
            
    return result