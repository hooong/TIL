# 알리바바와 40인의 도둑 (Bottom-Up)

n = int(input())
board = [list(map(int, input().split())) for _ in range(n)]

dp = [[0] * n for _ in range(n)]
for i in range(n):
    for j in range(n):
        if i == 0 and j == 0:
            dp[i][j] = board[i][j]
        elif i == 0:
            dp[i][j] = dp[i][j-1] + board[i][j]
        elif j == 0:
            dp[i][j] = dp[i-1][j] + board[i][j]
        else:
            dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + board[i][j]

print(dp[-1][-1])
