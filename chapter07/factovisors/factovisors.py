# UVa 10139
# Find if m divides n! (n! / m == 0)
# Find prime factorization of m
# for p in primeFactorization(m): find number of p's in n!
# ** Do this by finding the exponent of a prime, p, in n! http://www.algorithmist.com/index.php/Int_get_powers(n,_p) **
# Make sure they don't appear more in m than in n!

from sys import stdin
from math import sqrt

def getPrimeFactors(num):
    """ Get the prime factors of num """
    factors = list()
    remaining = num
    while remaining % 2 == 0:
        remaining = int(remaining / 2)
        factors.append(2)
    
    i = 3
    # No reason to check anything bigger than sqrt(x) b/c anything less is just the matching multiplier (EX: 3 * 500 = 1500, don't need to check 500 also)
    while i <= int(sqrt(remaining)):
        while remaining % i == 0:
            remaining = int(remaining / i)
            factors.append(i)
        # Only have to count odds
        i += 2
    # The final prime
    if remaining > 2:
        factors.append(remaining)

    return factors

# Idea from http://www.algorithmist.com/index.php/Int_get_powers(n,_p)
def getNumPrimes(num, prime):
    """ Get the number of occurrences of a prime in the factorization of num! """
    result = 0
    power = prime
    while power <= num:
        result += int(num/power)
        power *= prime
    return result



def main():
    for line in stdin:
        if len(line) > 3:
            numbers = line.split(' ')
            n = int(numbers[0])
            m = int(numbers[1])
            if m != 0:
                mPrimes = getPrimeFactors(m)
                # print(mPrimes)
                mDivides = True
                for prime in mPrimes:
                    if mDivides == False:
                        break
                    numPrimes = getNumPrimes(n, prime)
                    mOccurrences = mPrimes.count(prime)
                    # print("{} < {}: {}".format(numPrimes, mOccurrences, numPrimes < mOccurrences))
                    if numPrimes < mOccurrences:
                        mDivides = False
                if mDivides:
                    print("{} divides {}!".format(m, n))
                else:
                    print("{} does not divide {}!".format(m, n))
            else:
                print("{} divides {}!".format(m, n))


if __name__ == "__main__":
    main()