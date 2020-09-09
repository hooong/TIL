# 송아지 찾기

from collections import deque

s, e = map(int, input().split())
MAX = 10000
visited = [0] * (MAX+1)

queue = deque()
visited[s] = 0
queue.append(s)

while queue:
    cur = queue.popleft()

    for next in (cur-1, cur+1, cur+5):
        if 0 < next <= MAX:
            if next == e:
                print(visited[cur] + 1)
                exit(0)

            if visited[next] == 0:
                queue.append(next)
                visited[next] = visited[cur] + 1
