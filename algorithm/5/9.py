# 수열 추측하기

# def dfs(l, sum):
#     if l == n and sum == f:
#         print(*seq[1:])
#         exit(0)
#     else:
#         for i in range(1, n+1):
#             if not visited[i]:
#                 visited[i] = True
#                 seq[l+1] = i
#                 dfs(l+1, sum + (i*b[l]))
#                 visited[i] = False

# if __name__ == '__main__':
#     n, f = map(int, input().split())

#     # 파스칼 삼각형 (이항계수 생성)
#     b = [1] * n
#     for i in range(1, n):
#         b[i] = b[i-1] * (n-i) // i

#     seq = [0] * (n+1)
#     visited = [False] * (n+1)
#     dfs(0, 0)

import itertools as it

n, f = map(int, input().split())

# 파스칼 삼각형 (이항계수 생성)
b = [1] * n
for i in range(1, n):
    b[i] = b[i-1] * (n-i) // i

a = list(range(1, n+1))
for tmp in it.permutations(a):
    sum = 0
    for L, x in enumerate(tmp):
        sum += x * b[L]
    if sum == f:
        print(*tmp)
        break
