# 위상정렬

n, m = map(int, input().split())
adj = [[] for _ in range(n+1)]
degree = [0] * (n+1)

for i in range(m):
    a, b = map(int, input().split())

    adj[a].append(b)
    degree[b] += 1

q = []
for i in range(1, n+1):
    if degree[i] == 0:
        q.append(i)
        degree[i] = -1

while q:
    v = q.pop(0)
    print(v, end=' ')

    for node in adj[v]:
        degree[node] -= 1
    
    for i in range(1, n):
        if degree[i] == 0:
            q.append(i)
            degree[i] = -1
print()
