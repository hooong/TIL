# 돌다리 건너기

n = int(input())

dp = [0] * (n+1)
dp[1] = 2
dp[2] = 3

for i in range(3, n+1):
    dp[i] = dp[i-2] + dp[i-1]

print(dp[-1])
