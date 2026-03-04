def solution(commands):
    ans = []
    n = 50*50 # 전체 표 셀을 1차원 배열에 넣었을 때의 길이
    cells = ['']*n # 셀들의 루트에만 값을 저장
    
    parents = [0]*n # 한 셀의 루트인 셀의 인덱스를 저장
    for i in range(n):
        parents[i] = i
        
    def find(x):
        if parents[x] == x:
            return x
        
        parents[x] = find(parents[x])
        return parents[x]
    
    def union(x, y):
        p1 = find(x)
        p2 = find(y)
        
        if p1 == p2:
            # 이미 같은 그룹
            return
        
        # x 그룹으로 병합
        if cells[p1] == '' and cells[p2] != '':
            cells[p1] = cells[p2]
            
        cells[p2] = ''
        parents[p2] = p1
        
    for c in commands:
        cmd = list(c.split())
        
        if cmd[0] == 'UPDATE':
            if len(cmd) == 4:
                # 해당 셀에 값을 넣는다.
                r = int(cmd[1]) - 1
                c = int(cmd[2]) - 1
                cells[find(50*r + c)] = cmd[3] # 루트에 값을 저장
            else:
                for i in range(n):
                    root = find(i)
                    if cells[root] != '' and cells[root] == cmd[1]:
                        cells[root] = cmd[2]
                        
        elif cmd[0] == 'MERGE':
            r1 = int(cmd[1]) - 1
            c1 = int(cmd[2]) - 1
            r2 = int(cmd[3]) - 1
            c2 = int(cmd[4]) - 1
            
            union(50*r1 + c1, 50*r2 + c2)
            
        elif cmd[0] == 'UNMERGE':
            r = int(cmd[1]) - 1
            c = int(cmd[2]) - 1
            
            root = find(50*r + c)
            data = cells[find(50*r + c)]
            
            for i in range(n):
                find(i) # 전체에 대해 경로 압축 -> root가 최상위 루트인 셀들이 바로 root 값을 가질 수 있도록 하기 위함
                
            for i in range(n):
                if find(i) == root:
                    parents[i] = i # 부모를 자기 자신으로
                    cells[i] = ''
            
            cells[50*r + c] = data
            
        else:
            r = int(cmd[1]) - 1
            c = int(cmd[2]) - 1
            
            if cells[find(50*r + c)] == '':
                ans.append("EMPTY")
            else:
                ans.append(cells[find(50*r + c)])
        
    return ans