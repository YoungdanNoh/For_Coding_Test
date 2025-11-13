import sys

# ---------------------------------------
# 1. 트리 초기화 (build)
# ---------------------------------------
def build(node, start, end):
    """
    node : tree 배열에서 현재 노드 번호 (1부터 시작)
    start, end : 이 노드가 담당하는 nums 의 구간 [start, end]

    역할:
      - 리프 노드면: nums[start] 값을 그대로 tree[node]에 저장
      - 내부 노드면: 왼쪽/오른쪽 자식의 구간 합을 더해서 tree[node]를 채움
    """
    # 리프 노드 (구간 길이 1: start == end)
    if start == end:
        tree[node] = arr[start]
        return tree[node]

    # 중간 지점
    mid = (start + end) // 2

    # 왼쪽 자식: node*2, [start, mid]
    left_sum = build(node * 2, start, mid)
    # 오른쪽 자식: node*2+1, [mid+1, end]
    right_sum = build(node * 2 + 1, mid + 1, end)

    # 현재 노드는 "왼쪽 구간 합 + 오른쪽 구간 합"
    tree[node] = left_sum + right_sum
    return tree[node]


# ---------------------------------------
# 2. 구간 합 query
# ---------------------------------------
def query(node, start, end, left, right):
    """
    [left, right] 구간의 합을 구하는 함수.

    node : 현재 노드 번호 (tree 배열 인덱스)
    start, end : 현재 노드가 담당하는 구간 [start, end]
    left, right : 우리가 알고 싶은 구간 [left, right]

    세 가지 경우로 나눠서 처리:
      1) 전혀 안 겹침 → 0 반환
      2) 완전히 포함됨 → tree[node] 그대로 반환
      3) 일부만 겹침 → 자식 둘로 내려가서 결과 합치기
    """

    # (1) 전혀 겹치지 않는 경우
    # ex) [start, end] = [0,3], [left,right] = [4,7]
    if right < start or end < left:
        return 0  # 합 세그트리이므로 0 (기본값)

    # (2) 현재 구간 [start, end]가 쿼리 구간 [left, right]에 완전히 포함되는 경우
    #     → 이 노드 전체 값을 사용해도 됨 (더 쪼갤 필요 없음)
    if left <= start and end <= right:
        return tree[node]

    # (3) 일부만 겹치는 경우 → 자식들로 나누어서 다시 구함
    mid = (start + end) // 2
    sum_left = query(node * 2, start, mid, left, right)
    sum_right = query(node * 2 + 1, mid + 1, end, left, right)
    return sum_left + sum_right


# ---------------------------------------
# 3. 값 업데이트 (point update)
# ---------------------------------------
def update(node, start, end, idx, diff):
    """
    nums[idx] 값이 (기존값 + diff) 로 바뀌었을 때,
    세그먼트 트리에서 관련된 노드들의 값을 모두 갱신하는 함수.

    node : 현재 노드 번호
    start, end : 이 노드가 담당하는 구간 [start, end]
    idx : 실제 값이 바뀐 nums 의 인덱스
    diff : (새 값 - 기존 값)

    아이디어:
      - 이 노드의 구간에 idx가 포함되지 않으면 → return
      - 포함되면:
          - tree[node]에 diff만큼 더해 주고
          - 리프가 아니면 자식으로 내려가서 계속 반영
    """

    # idx가 이 노드의 구간 [start, end] 밖이면 아무 영향 없음
    if idx < start or idx > end:
        return

    # 이 노드는 idx를 포함하는 구간이므로, 구간 합에도 diff만큼 영향이 감
    tree[node] += diff

    # 리프 노드면 더 내려갈 자식이 없음
    if start == end:
        return

    # 아니라면 자식 노드로 내려가서 계속 반영
    mid = (start + end) // 2
    update(node * 2, start, mid, idx, diff)
    update(node * 2 + 1, mid + 1, end, idx, diff)


read = sys.stdin.readline

N, M, K = map(int, read().split())
arr = []
for i in range(N):
    arr.append(int(read().strip()))

tree = [0] * (4*N)   # 구간 합을 저장할 배열
# 전체 구간 [0, n-1]으로 트리를 초기화
build(1, 0, N-1)
for i in range(M+K):
    inp = list(map(int, read().split()))

    if inp[0] == 1:
        # 숫자 바꾸기
        idx = inp[1] - 1
        new_value = inp[2]
        diff = new_value - arr[idx] # 값의 차이
        arr[idx] = new_value

        # 세그먼트 트리에 변경 사항 반영
        update(1, 0, N-1, idx, diff)
    else:
        # 구간 합 출력
        print(query(1, 0, N-1, inp[1]-1, inp[2]-1))