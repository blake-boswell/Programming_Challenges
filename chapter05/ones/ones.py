import sys

# 10127

def main():
    for line in sys.stdin:
        num = int(line)
        found = False
        numDigits = 1
        ones = 1
        while ones % num != 0:
            ones = (ones * 10) + 1
            numDigits += 1
        print(numDigits)


if __name__ == "__main__":
    main()