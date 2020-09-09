# 경로 탐색

def dfs(v):
    global cnt

    if v == 5:
        cnt += 1
        return 
    else:
        for i in idj[v]:
            if not visited[i]:
                visited[i] = True
                dfs(i)
                visited[i] = False

if __name__ == '__main__':
    n, m = map(int, input().split())
    
    idj = [[] for _ in range(n+1)]
    for _ in range(m):
        a, b = map(int, input().split())
        idj[a].append(b)

    visited = [False] * (n+1)
    visited[1] = True
    cnt = 0
    dfs(1)
    print(cnt)