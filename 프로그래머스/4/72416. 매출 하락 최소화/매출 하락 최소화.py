INF = 10**15

def solution(sales, links):
    n = len(sales)
    val = [0] + sales  # 1-index

    # 1) children[u] = u가 팀장일 때, 관리하는 팀원들
    children = [[] for _ in range(n + 1)]
    for a, b in links:          # a: 팀장, b: 팀원 (방향 명시됨!)
        children[a].append(b)

    # 2) DFS: (take[u], skip[u]) 반환
    #    - take[u] : u가 참석할 때, u 서브트리 최소합
    #    - skip[u] : u가 불참할 때, u 서브트리 최소합 (단, 자식 중 최소 1명은 참석해야 함)
    def dfs(u):
        if not children[u]:         # 리프(부하 없음)
            # u가 참석하면 자기 매출이 비용으로 듦, 불참이면 0
            return val[u], 0

        sum_min = 0                 # 자식별 min(take, skip)의 합
        join = False                # 자식 중 '자발적 참석'이 있었는가?
        min_diff = INF              # (take - skip) 최소값 (강제 참석 시 추가 비용)

        for v in children[u]:
            tv, sv = dfs(v)         # 자식 v의 (참석비, 불참비)
            # 1차로 더 싼 쪽을 누적
            if tv < sv:
                sum_min += tv
                join = True         # 자발적 참석 발생
            else:
                sum_min += sv
                # 강제 참석시 추가로 드는 최소비용 후보 갱신
                if tv - sv < min_diff:
                    min_diff = tv - sv

        # u가 참석하는 경우: 자기 비용 + 자식들의 더 싼 쪽
        take_u = val[u] + sum_min

        # u가 불참하는 경우: 자식 중 최소 한 명은 참석이어야 함
        if join:
            skip_u = sum_min                # 이미 한 명 이상 참석
        else:
            skip_u = sum_min + min_diff     # 전원 불참이면, 추가비용이 가장 작은 자식을 '강제 참석'

        return take_u, skip_u

    take_root, skip_root = dfs(1)   # CEO는 1번
    return min(take_root, skip_root)
