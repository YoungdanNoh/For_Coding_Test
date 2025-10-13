def solution(tickets):
    answer = []
    
    ticket = {}
    for a, b in tickets:
        if a in ticket:
            ticket[a].append(b)
        else:
            ticket[a] = [b]
    
    for key in ticket:
        ticket[key].sort()
    
    def dfs(ticket, start):
        # 티켓 정보, 출발지
        if start in ticket:
            while ticket[start]:
                # 현재 티켓으로 갈 수 있는 경로를 모두 가보기
                dfs(ticket, ticket[start].pop(0))
        answer.append(start)
    
    dfs(ticket, "ICN") # ICN에서 출발
    
    return answer[::-1]