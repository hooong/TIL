# 이분검색

def binary_search(start, end, arr, key):
    mid = (start + end) // 2

    if arr[mid] == key:
        return mid
    elif arr[mid] > key:
        return binary_search(start, mid-1, arr, key)
    else:
        return binary_search(mid+1, end, arr, key)

n, m = map(int, input().split())
arr = list(map(int, input().split()))
arr.sort()
print(arr)

print(binary_search(0, n-1, arr, m) + 1)