# 수들의 조합

def dfs(l, s):
    global cnt

    if l == k:
        if sum(res) % m == 0:
            cnt += 1
            return 
    else:
        for i in range(s, n):
            res[l] = arr[i]
            dfs(l+1, i+1)

if __name__ == '__main__':
    n, k = map(int, input().split())
    arr = list(map(int, input().split()))
    m = int(input())

    cnt = 0
    res = [0] * k
    dfs(0, 0)

    print(cnt)