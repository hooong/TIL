# 최대 선 연결하기

n = int(input())
arr = list(map(int, input().split()))

dp = [0] * n
for i in range(n):
    dp[i] = 1
    for j in range(i):
        if arr[j] < arr[i]:
            dp[i] = dp[j] + 1

print(max(dp))
