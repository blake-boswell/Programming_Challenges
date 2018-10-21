# uVa 10035
"""
Module Docstring
"""
import sys

__author__ = "Blake Boswell"

def main():
    """ Main entry point of the app """
    for line in sys.stdin:
        line = line.replace("\n", "")
        line = line.replace("\r", "")
        firstNum, secondNum = line.split(' ')
        if int(firstNum) == 0 and int(secondNum) == 0:
            break
        
        if secondNum[len(secondNum) - 1] == '\n':
            secondNum = secondNum[:-1]
        firstNum = firstNum[::-1]
        secondNum = secondNum[::-1]
        numDigits = max(len(firstNum), len(secondNum))
        smallerNumDigits = min(len(firstNum), len(secondNum))
        firstIsSmaller = False
        if smallerNumDigits == len(firstNum):
            firstIsSmaller = True
        # print(numDigits)

        carryCount = 0
        carry = 0
        for i in range(0, numDigits):
            
            digit = 0
            if i > smallerNumDigits - 1:
                if firstIsSmaller:
                    digit = int(secondNum[i]) + carry
                    # print(carry, " + ", secondNum[i])
                else:
                    digit = int(firstNum[i]) + carry
                    # print(firstNum[i], " + ", carry)
            else:
                digit = int(firstNum[i]) + int(secondNum[i]) + carry
                # print(firstNum[i], " + ", secondNum[i])
            # print("\t\t", digit)
            if digit >= 10:
                carry = int(digit / 10)
                carryCount += 1
                # print("\t\tThats a carry!")
            else:
                carry = 0
        if carryCount == 0:
            print("No carry operation.")
        elif carryCount == 1:
            print(carryCount, "carry operation.")
        else:
            print(carryCount, "carry operations.")
        
    
    

if __name__ == "__main__":
    """ This is executed when run from the command line """
    main()