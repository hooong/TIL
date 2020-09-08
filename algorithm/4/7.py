# 교육과정 설계

necessary = input()

n = int(input())
courses = []
for _ in range(n):
    courses.append(input())

for course in courses:
    before = 0
    for i in necessary:
        if course.find(i) - before < 0:
            print('NO')
            break
        else:
            before = course.find(i)
    else:
        print("YES")
