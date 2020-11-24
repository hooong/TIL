# 바둑이 승차

def dfs(v, sum, tsum):
    global max_sum

    # 불필요한 연산 줄이는 작업
    if sum+(total-tsum) < max_sum:
        return

    if sum > c:
        return

    if v == n:
        max_sum = max(max_sum, sum)
        return
    else:
        dfs(v+1, sum + badooks[v], tsum+badooks[v])
        dfs(v+1, sum, tsum+badooks[v])

if __name__ == '__main__':
    c, n = map(int, input().split())
    badooks = []

    for _ in range(n):
        badooks.append(int(input()))
    
    total = sum(badooks)
    max_sum = 0
    dfs(0, 0, 0)

    print(max_sum)
