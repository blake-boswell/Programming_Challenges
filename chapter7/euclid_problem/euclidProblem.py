# UVa 10104
from sys import stdin
from math import floor

def gcd(a, b):
    # if a < b:
    #     return gcd(b, a)
    if b == 0:
        return (1, 0, a)
    (xPrev, yPrev, d) = gcd(b, a%b)
    x = yPrev
    y = (xPrev - floor(a/b) * yPrev)

    return (x, y, d)

def main():
    for line in stdin:
        if len(line) > 2:
            (a, b) = line.split(' ')
            a = int(a)
            b = int(b)
            (x, y, d) = gcd(a, b)
            print(x, y, d)

if __name__ == "__main__":
    main()