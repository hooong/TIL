# 동전 바꿔주기

def dfs(l, sum):
    global cnt

    if sum > t:
        return
    
    if l == k:
        if sum == t:
            cnt += 1
    else:
        for i in range(coins[l][1] + 1):
            dfs(l+1, sum + coins[l][0] * i)

if __name__ == '__main__':
    t = int(input())
    k = int(input())

    coins = []
    for _ in range(k):
        coins.append(list(map(int, input().split())))

    cnt = 0
    dfs(0, 0)

    print(cnt)
