# 최소힙

import heapq

minheap = []
while True:
    num = int(input())

    if num == -1:
        break
    elif num == 0:
        if not minheap:
            print(-1)
        else:
            print(heapq.heappop(minheap))
    else:
        heapq.heappush(minheap, num)
