# 두 리스트 합치기

n = int(input())
l1 = list(map(int, input().split()))
m = int(input())
l2 = list(map(int, input().split()))

result_list = []
p_l1 = 0
p_l2 = 0
while not p_l1 == n or not p_l2 == m:
    if p_l1 == n:
        result_list += l2[p_l2:]
        break
    elif p_l2 == m:
        result_list += l1[p_l1:]
        break
    else:
        if l1[p_l1] < l2[p_l2]:
            result_list.append(l1[p_l1])
            p_l1 += 1
        elif l1[p_l1] > l2[p_l2]:
            result_list.append(l2[p_l2])
            p_l2 += 1
        else:
            for _ in range(2):
                result_list.append(l1[p_l1])
            p_l1 += 1
            p_l2 += 1

print(*result_list)

