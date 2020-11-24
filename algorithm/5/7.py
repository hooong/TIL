# 동전교환
import sys

def dfs(l, sum):
    global cnt

    if l > cnt:
        return 

    if sum > changes:
        return 

    if sum == changes:
        cnt = min(cnt, l)
        return
    else:
        for i in range(n):
            dfs(l + 1, sum + coins[i])

if __name__ == '__main__':
    n = int(input())
    coins = list(map(int, input().split()))
    changes = int(input())

    cnt = sys.maxsize
    dfs(0, 0)
    print(cnt)
