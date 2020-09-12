# 회장뽑기
import sys
MAX = sys.maxsize

n = int(input())
dp = [[MAX] * n for _ in range(n)]
for i in range(n):
    dp[i][i] = 0

while True:
    a, b = map(int, input().split())

    if a == -1 and b == -1:
        break

    dp[a-1][b-1] = 1
    dp[b-1][a-1] = 1

for k in range(n):
    for i in range(n):
        for j in range(n):
            dp[i][j] = min(dp[i][j], dp[i][k] + dp[k][j])

user = []
for i in range(n):
    user.append(max(dp[i]))

min_score = min(user)
print(min_score, user.count(min_score))
for i in range(n):
    if user[i] == min_score:
        print(i+1, end=' ')
print()
