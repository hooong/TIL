# 인접행렬

n, m = map(int, input().split())

adj = [[0] * n for _ in range(n)]
for _ in range(m):
    a, b, cost = map(int, input().split())
    adj[a-1][b-1] = cost

for row in adj:
    print(*row)
