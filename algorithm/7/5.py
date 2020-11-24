# 최대 부분 증가수열

n = int(input())
seq = list(map(int, input().split()))

dp = [0] * n
for i in range(n):
    dp[i] = 1
    for j in range(i):
        if seq[j] < seq[i]:
            dp[i] = max(dp[i], dp[j]+1)

print(max(dp))
