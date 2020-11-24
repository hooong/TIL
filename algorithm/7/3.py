# 계단오르기

def dfs(m):
    if dp[m] > 0:
        return dp[m]
    else:
        dp[m] = dfs(m-1) + dfs(m-2)
        return dp[m]

if __name__ == '__main__':
    n = int(input())

    dp = [0] * (n+1)
    dp[1] = 1
    dp[2] = 2
    
    print(dfs(n))
