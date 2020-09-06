# 침몰하는 타이타닉

n, m = map(int, input().split())
person = list(map(int, input().split()))

person.sort(reverse=True)

cnt = 0
w = 0
while person:
    w += person.pop(0)
    cnt += 1

    for i in range(len(person)):
        if w + person[i] <= m:
            person.pop(i)
    w = 0

print(cnt)

# cnt = 0
# while person:
#     cnt += 1
#     if len(person) == 1:
#         break
#     elif person[0] + person[-1] > m:
#         person.pop()
#     else:
#         person.pop(0)
#         person.pop()
# print(cnt)