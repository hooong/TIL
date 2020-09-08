# 최대힙
import heapq

maxheap = []
while True:
    num = int(input())

    if num == -1:
        break
    elif num == 0:
        if not maxheap:
            print(-1)
        else:
            print(heapq.heappop(maxheap) * -1)
    else:
        heapq.heappush(maxheap, num * -1)
