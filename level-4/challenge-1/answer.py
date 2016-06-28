from __future__ import division, print_function

from math import factorial
binomial_coefficient_cache = dict()
qq_cache = dict()


def binomial_coefficient_naive(n, k):
    d = n - k
    if d < 0:
        return 0
    return factorial(n) // factorial(k) // factorial(d)
current_binomial = binomial_coefficient_naive


def binomial_memoized(n, k):
    if (n, k) in binomial_coefficient_cache:
        return binomial_coefficient_cache[n, k]
    res = current_binomial(n, k)
    binomial_coefficient_cache[n, k] = res
    return res
binomial = binomial_memoized


def qq(n, k):
    '''
    Number of labeled, simply connected Graphs of order n, size k
    '''
    if (n, k) in qq_cache:
        return qq_cache[n, k]
    s = n * (n - 1) // 2
    if k < n - 1 or k > s:
        res = 0
    elif k == n - 1:
        res = int(pow(n, (n - 2)))
    else:
        res = binomial(s, k)
        for m in range(0, n - 1):
            res1 = 0
            lb = max(0, k - (m + 1) * m // 2)
            for p in range(lb, k - m + 1):
                np = (n - 1 - m) * (n - 2 - m) // 2
                res1 += binomial(np, p) * qq(m + 1, k - p)

            res -= binomial(n - 1, m) * res1

    qq_cache[n, k] = res
    return res


def gf5(n):
    '''
    Number of labeled, simply connected Graphs of order n
    '''
    ub = (n * (n - 1)) // 2
    qn = sum([qq(n, k) for k in range(n - 1, ub + 1)])
    return(qn)

gf3_cache = dict()
B_cache = dict()


def B(m):
    if m in B_cache:
        return B_cache[m]
    B_cache[m] = int(pow(2, m * (m - 1) // 2)) if m >= 2 else 1
    return B_cache[m]


def gf3(n):
    '''
    Number of labeled, simply connected Graphs of order n,
    computed very quickly
    '''
    if n in gf3_cache:
        return gf3_cache[n]
    if n < 2:
        s = 1
    else:
        q = n - 1
        s = B(q + 1) - sum(binomial(q, m) * B(q - m) * gf3(m + 1)
                           for m in range(q))
    gf3_cache[n] = s
    return s