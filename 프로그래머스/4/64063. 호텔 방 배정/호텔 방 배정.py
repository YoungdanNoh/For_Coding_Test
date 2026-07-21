import sys

# 재귀 한도 늘리기
sys.setrecursionlimit(10**6)
    
def solution(k, room_number):
    nxt = {}
    
    def find(x):
        if x not in nxt:
            nxt[x] = x+1
            return x

        nxt[x] = find(nxt[x])
        return nxt[x]

    ans = [0]*len(room_number)
    
    for i in range(len(room_number)):
        ans[i] = find(room_number[i])
    
    return ans