import sys

#10018

def main():
    """ Main function of the program """
    numCases = int(input())

    for i in range(0, numCases):
        num = int(input())
        loop = True
        numIterations = 0
        while loop:
            numIterations += 1
            reverseNum = int(str(num)[::-1])
            # print(num)
            # print(reverseNum)
            num = num + reverseNum
            numString = str(num)
            if numString == numString[::-1]:
                loop = False
        print(numIterations, num)
            



if __name__ == "__main__":
    main()