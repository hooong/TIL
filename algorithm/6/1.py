# 최대점수 구하기

def dfs(l, sum, time):
    global max_score
    
    if time > m:
        return 
    
    if l == n:
        max_score = max(max_score, sum)
    else:
        dfs(l+1, sum + problems[l][0], time + problems[l][1])
        dfs(l+1, sum, time)

if __name__ == '__main__':
    n, m = map(int, input().split())

    problems = []
    for _ in range(n):
        problems.append(list(map(int, input().split())))

    max_score = 0
    visited = [False] * (n)
    dfs(0, 0, 0)

    print(max_score)
    