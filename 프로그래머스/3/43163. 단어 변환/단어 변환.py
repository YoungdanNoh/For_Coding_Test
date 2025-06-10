result = 1e9

def solution(begin, target, words):
    
    # 주의!
    # 인덱스 순으로만 단어를 바꿀 수 있는게 아님. 이전 인덱스에 있는 단어로도 바꿀 수 있음.
    visited = [False]*len(words)
    
    def dfs(idx, word, cnt):
        global result
        
        if(result < cnt):
            return
        
        if(target not in words):
            return
        
        # 이번에 접근할 idx, 현재의 단어(word)
        if(word == target):
            result = min(result, cnt)
            return
        
        for i in range(len(words)):
            
            if(not visited[i]):
                # 현재 단어와 1개 차이가 나면 바꾸기 or 안 바꾸기
                tmp = 0
                for j in range(len(word)):
                    if(word[j] == words[i][j]):
                        tmp += 1

                if(tmp == (len(word)-1)):
                    visited[i] = True
                    dfs(i+1, words[i], cnt+1) #바꾸기
                    
                visited[i] = False
    
    dfs(0, begin, 0)
    
    if(result == 1e9):
        return 0
    else:
        return result
    