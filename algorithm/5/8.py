# 순열 구하기

def dfs(v):
    global cnt

    if v == m:
        print(*res)
        cnt += 1
        return
    else:
        for i in range(1, n+1):
            if not visited[i]:
                visited[i] = True
                res[v] = i
                dfs(v+1)
                visited[i] = False

if __name__ == '__main__':
    n, m = map(int, input().split())

    res = [0] * m
    visited = [False] * (n+1)
    cnt = 0
    dfs(0)
    
    print(cnt)