# k번째 큰 수

n, k = map(int, input().split())
arr = list(map(int, input().split()))

result = []
for i in range(n-2):
    for j in range(i+1, n-1):
        for l in range(j+1, n):
            result.append(arr[i] + arr[j] + arr[l])

result.sort(reverse=True)

print(result[k-1])