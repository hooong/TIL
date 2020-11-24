# 부분집합 구하기(DFS)

def dfs(v):
    if v == n+1:
        for i in range(1, n+1):
            if arr[i] == 1:
                print(i, end=' ')
        print()
    else:
        arr[v] = 1
        dfs(v+1)
        arr[v] = 0
        dfs(v+1)
    
if __name__ == '__main__':
    n = int(input())
    arr = [0] * (n+1)
    dfs(1)
