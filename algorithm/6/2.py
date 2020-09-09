# 휴가

def dfs(l, sum):
    global max_reward

    if l > n:
        return
    
    if l == n:
        max_reward = max(max_reward, sum)
    else:
        dfs(l+schedule[l][0], sum + schedule[l][1])
        dfs(l+1, sum)

if __name__ == '__main__':
    n = int(input())

    schedule = []
    for _ in range(n):
        schedule.append(list(map(int, input().split())))

    max_reward = 0
    dfs(0, 0)

    print(max_reward)
    