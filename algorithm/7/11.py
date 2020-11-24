# 동전교환
import sys

n = int(input())
coins = list(map(int, input().split()))
m = int(input())

dp = [sys.maxsize] * (m+1)
dp[0] = 0
coins.insert(0,0)

for i in range(1, n+1):
    for j in range(coins[i], m+1):
        dp[j] = min(dp[j], dp[j-coins[i]] + 1)

print(dp[-1])
