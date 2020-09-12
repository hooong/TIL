# 알리바바와 40인의 도둑(Top-Down)

def dfs(y, x):
    if dp[y][x] > 0:
        return dp[y][x]

    if y == 0 or x == 0:
        return dp[y][x]
    else:
        dp[y][x] = min(dfs(y-1, x), dfs(y, x-1)) + board[y][x]
        return dp[y][x]

if __name__ == '__main__':
    n = int(input())
    board = [list(map(int, input().split())) for _ in range(n)]

    dp = [[0] * n for _ in range(n)]
    dp[0][0] = board[0][0]
    for i in range(1, n):
        dp[i][0] = dp[i-1][0] + board[i][0]
        dp[0][i] = dp[0][i-1] + board[0][i]    

    print(dfs(n-1, n-1))
