# 동전 분배하기
import sys

def dfs(l, sum_a, sum_b, sum_c):
    global min_dis

    if l == n:
        if not sum_a == sum_b and not sum_b == sum_c and not sum_a == sum_c:
            max_sum = max(sum_a, max(sum_b, sum_c))
            min_sum = min(sum_a, min(sum_b, sum_c))
            min_dis = min(min_dis, (max_sum - min_sum))
    else:
        dfs(l+1, sum_a + coins[l], sum_b, sum_c)
        dfs(l+1, sum_a, sum_b + coins[l], sum_c)
        dfs(l+1, sum_a, sum_b, sum_c + coins[l])

if __name__ == '__main__':
    n = int(input())

    coins = []
    for _ in range(n):
        coins.append(int(input()))

    min_dis = sys.maxsize
    dfs(0, 0, 0, 0)

    print(min_dis)
