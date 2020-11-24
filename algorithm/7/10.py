# 가방문제 (냅색알고리즘)

n, limit = map(int, input().split())
dp = [0] * (limit + 1)

for i in range(n):
    w, v = map(int, input().split())
    for j in range(w, limit+1):
        dp[j] = max(dp[j], dp[j-w] + v)

print(dp[-1])
