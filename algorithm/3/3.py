# 뮤직비디오

def count(cap):
    sum = 0
    cnt = 1
    for t in songs:
        if sum + t > cap:
            sum = t
            cnt += 1
        else:
            sum += t
    return cnt

n, m = map(int, input().split())
songs = list(map(int, input().split()))

start = max(songs)
end = sum(songs)
while start <= end:
    mid = (start + end) // 2

    if count(mid) <= m:
        answer = mid
        end = mid - 1
    else:
        start = mid + 1

print(answer)
