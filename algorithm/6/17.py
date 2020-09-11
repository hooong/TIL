# 피자 배달 거리
import sys

def cal_len():
    total = 0
    for h in house:
        min_d = sys.maxsize
        for i in remain:
            min_d = min(min_d, abs(pizza[i][0] - h[0]) + abs(pizza[i][1] - h[1]))
        total += min_d
    return total

def dfs(l, s):
    global min_delivery

    if l == m:
        min_delivery = min(min_delivery, cal_len())
        return
    else:
        for i in range(s, len(pizza)):
            remain[l] = i
            dfs(l+1, i+1)

if __name__ == '__main__':
    n, m = map(int, input().split())

    city = [list(map(int, input().split())) for _ in range(n)]

    pizza = []
    house = []
    for i in range(n):
        for j in range(n):
            if city[i][j] == 1:
                house.append([i, j])
            if city[i][j] == 2:
                pizza.append([i, j])
    
    remain = [0] * m
    min_delivery = sys.maxsize
    dfs(0, 0)

    print(min_delivery)
    