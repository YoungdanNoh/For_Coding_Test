def solution(dice):
    R = len(dice) // 2
    selected = [False]*len(dice)
    maxWin = 0
    ans = []
    
    def dfs(result, d, cnt, tot):
        if cnt == R:
            # 마지막 주사위까지 한 면을 골랐다면, 점수의 합을 결과 리스트에 넣는다.
            result.append(tot)
            return
        
        for i in range(6):
            # cnt번째 주사위의 6면 중 하나를 선택
            idx = d[cnt]
            dfs(result, d, cnt+1, tot+dice[idx][i])
            
    def lowerbound(a, bList):
        start = 0
        end = len(bList) - 1
        result = len(bList) # bList에서 처음으로 a 이상(>= a)이 되는 위치(index)
                            # 즉, a보다 작은 bList 값의 갯수를 알려준다.
        
        while start <= end:
            mid = (start+end) // 2
            
            if bList[mid] >= a:
                result = mid
                end = mid - 1
            else:
                start = mid + 1
                
        return result
    
    def combi(cnt, start):
        # A가 가져갈 주사위 선택
        if cnt == R:
            a = []
            b = []
            
            for i in range(len(selected)):
                if selected[i]:
                    a.append(i)
                else:
                    b.append(i)
                    
            # A가 주사위를 굴려 나올 수 있는 모든 경우의 수
            aList = []
            dfs(aList, a, 0, 0)
            
            # B가 주사위를 굴려 나올 수 있는 모든 경우의 수
            bList = []
            dfs(bList, b, 0, 0)
            
            # 각 A의 점수에 대해 A가 승리하는 경우의 수 세기
            bList.sort()
            
            win = 0
            for i in aList:
                win += lowerbound(i, bList)
                
            nonlocal maxWin, ans
            if maxWin < win:
                ans = []
                maxWin = win
                
                for i in range(len(selected)):
                    if selected[i]:
                        ans.append(i+1)
            
            return

        for i in range(start, len(dice)):
            selected[i] = True
            combi(cnt+1, i+1)
            selected[i] = False
            
    combi(0, 0)
    
    return ans