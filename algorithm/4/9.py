# Anagram

str1 = input()
str2 = input()

alpha1 = dict()
alpha2 = dict()

for c in str1:
    if c in alpha1.keys():
        alpha1[c] += 1
    else:
        alpha1[c] = 1

for c in str2:
    if c in alpha2.keys():
        alpha2[c] += 1
    else:
        alpha2[c] = 1
     
print("YES" if alpha1 == alpha2 else "NO")