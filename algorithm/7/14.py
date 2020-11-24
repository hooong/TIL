# 다익스트라
import heapq, sys

def dijkstra(s):
    d = [sys.maxsize] * (n+1)

    hq = []
    heapq.heappush(hq, [0, s])
    d[s] = 0

    while hq:
        cur_d, cur_n = heapq.heappop(hq)

        if d[cur_n] < cur_d:
            continue
        
        for node, dis in adj[cur_n]:
            if d[node] > dis + cur_d:
                d[node] = dis + cur_d
                heapq.heappush(hq, [d[node], node])
    return d

if __name__ == '__main__':
    n, m = map(int, input().split())
    adj = [[] for _ in range(n+1)]

    for _ in range(m):
        a, b, c = map(int, input().split())
        adj[a].append([b, c])

    for i in range(1, n+1):
        dis = dijkstra(i)
        dis.pop(0)
        for v in dis:
            if v == sys.maxsize:
                print('M', end=' ')
            else:
                print(v, end=' ')
        print()
