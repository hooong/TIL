# 플로이드 워샬 알고리즘
import sys
MAX = sys.maxsize

n, m = map(int, input().split())
dp = [[MAX] * n for _ in range(n)]

for i in range(n):
    dp[i][i] = 0

for i in range(m):
    a, b, c = map(int, input().split())
    dp[a-1][b-1] = c

for k in range(n):
    for i in range(n):
        for j in range(n):
            dp[i][j] = min(dp[i][j], dp[i][k] + dp[k][j])

for i in range(n):
    for j in range(n):
        if dp[i][j] == MAX:
            print('M', end=' ')
        else:
            print(dp[i][j], end=' ')
    print()
