# 최대점수 구하기 (냅색 알고리즘)

n, m = map(int, input().split())
problem = [list(map(int, input().split())) for _ in range(n)]

dp = [0] * (m+1)
problem.insert(0,[0,0])

for i in range(1, n+1):
    for j in range(problem[i][1], m+1):
        dp[j] = max(dp[j], dp[j-problem[i][1]] + problem[i][0])

print(dp[-1])
