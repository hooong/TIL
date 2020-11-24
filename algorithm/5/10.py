# 조합 구하기

# def dfs(l):
#     if l == m:
#         if not set(res) in coms:
#             coms.append(set(res))
#             print(*res)
#         return
#     else:
#         for i in range(1, n+1):
#             if not visited[i]:
#                 visited[i] = True
#                 res[l] = i
#                 dfs(l+1)
#                 visited[i] = False

# if __name__ == '__main__':
#     n, m = map(int, input().split())

#     coms = []
#     res = [0] * m
#     visited = [False] * (n+1)
#     dfs(0)
#     print(len(coms))

def dfs(l, s):
    global cnt

    if l == m:
        cnt += 1
        print(*res)
        return
    else:
        for i in range(s, n+1):
            res[l] = i
            dfs(l+1, i+1)

if __name__ == '__main__':
    n, m = map(int, input().split())

    cnt = 0
    res = [0] * m
    dfs(0,1)
    print(cnt)
    