# 네트워크 선 자르기 (Top-Down: 재귀, 메모이제이션)

def dfs(m):
    if dp[m] > 0:
        return dp[m]
        
    if m == 1 or m == 2:
        return m
    else:
        dp[m] = dfs(m-1) + dfs(m-2)
        return dp[m]

if __name__ == '__main__':
    n = int(input())
    dp = [0] * (n+1)

    print(dfs(n))
