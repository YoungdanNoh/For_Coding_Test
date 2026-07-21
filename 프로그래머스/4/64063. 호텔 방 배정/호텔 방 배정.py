import sys

# 재귀 한도 늘리기
sys.setrecursionlimit(10**6)
    
def solution(k, room_number):
    nxt = {}
    
    def find(x):
        if x not in nxt:
            nxt[x] = x+1
            return x

        num = find(nxt[x]) # 배정 가능한 방 번호
        nxt[x] = num + 1 # 다음에 같은 방 번호 요청이 오면 1개를 건너뛰어야 한다.
        return num

    ans = [0]*len(room_number)
    
    for i in range(len(room_number)):
        ans[i] = find(room_number[i])
    
    return ans