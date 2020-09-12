# 네트워크 선 자르기 (Bottom-Up)

n = int(input())
dp = [0] * n
dp[0] = 1
dp[1] = 2

for i in range(2, n):
    dp[i] = dp[i-1] + dp[i-2]

print(dp[-1])