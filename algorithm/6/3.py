# 양팔저울

def dfs(l, sum):
    if l > k:
        return
    else:
        x[sum] = True

        if not l == k:
            dfs(l+1, sum + weight[l])
            dfs(l+1, abs(sum - weight[l]))
            dfs(l+1, sum)

if __name__ == '__main__':
    k = int(input())
    weight = list(map(int, input().split()))
    s = sum(weight)

    x = [False] * (s+1)
    x[0] = True
    dfs(0, 0)
    
    print(x.count(False))
