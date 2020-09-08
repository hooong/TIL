# 합이 같은 부분집합

def dfs(v, sum):
    if sum > total // 2:
        return
    if v == n:
        if sum == total - sum:
            print("YES")
            exit(0)
    else:
        dfs(v+1, sum+arr[v])
        dfs(v+1, sum)

if __name__ == '__main__':
    n = int(input())
    arr = list(map(int, input().split()))
    total = sum(arr)

    dfs(0, 0)
    print("NO")