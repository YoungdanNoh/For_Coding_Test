x = int(input())

bar = [64]

while x != sum(bar):
    idx = bar.index(min(bar)) # 최소 길이 막대의 인덱스 번호
    mb = bar.pop(idx)
    if sum(bar) + (mb/2) >= x:
        bar.append((mb / 2))
    else:
        bar.append((mb / 2))
        bar.append((mb / 2))

print(len(bar))