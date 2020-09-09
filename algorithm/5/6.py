# 중복순열 구하기

def dfs(l):
    global cnt

    if l == m:
        cnt += 1
        print(*res)
    else:
        for i in range(1, n+1):
            res[l] = i
            dfs(l+1)

if __name__ == '__main__':
    n, m = map(int, input().split())

    res = [0] * m
    cnt = 0
    dfs(0)
    print(cnt)
