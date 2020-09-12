# 가장 높은 탑 쌓기

n = int(input())
block = [list(map(int, input().split())) for _ in range(n)]

dp = [0] * n
for i in range(n):
    dp[i] = block[i][1]
    for j in range(i):
        if block[i][0] < block[j][0] and block[i][2] < block[j][2]:
            dp[i] = max(dp[i], dp[j] + block[i][1])

print(max(dp))
