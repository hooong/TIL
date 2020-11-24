# 봉우리

def is_peak(mountain, i, j):
    cur_pos = mountain[i][j]
    
    if cur_pos > mountain[i-1][j] and \
        cur_pos > mountain[i+1][j] and \
            cur_pos > mountain[i][j-1] and \
                cur_pos > mountain[i][j+1]:
        return True
    return False

n = int(input())

mountain = []

mountain.append([0] * (n+2))
for _ in range(n):
    tmp = [0]
    tmp += list(map(int, input().split()))
    tmp += [0]
    mountain.append(tmp)
mountain.append([0] * (n+2))

count_of_peaks = 0
for i in range(1, n+1):
    for j in range(1, n+1):
            if is_peak(mountain, i, j):
                count_of_peaks += 1

print(count_of_peaks)
    