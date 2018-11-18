# UVa 10049

def generateSequence():
    # sequence.append((0,1,2,4,6,9,12,16))
    SIZE = 700000
    sequence = [0, 1, 2, 4, 6, 9, 12, 16]
    lastIndex = len(sequence) - 1
    # occurences of the number
    occurences = 4
    while lastIndex < SIZE:
        prev = sequence[lastIndex]
        # find the number of occurences for this gap
        count = sequence[occurences + 1] - sequence[occurences]
        while count > 0:
            changedIndex = prev + occurences
            sequence.append(changedIndex)
            count -= 1
            lastIndex += 1
        occurences += 1

    return sequence



def main():
    n = int(input())
    # (value: start index) 1:1 2:2 3:3 4:6 5:9 => [0,1,2,2,3,3,4,4,4,5,5,5,6,6,6,6..,]
    changeSequence = generateSequence()
    while n != 0:
        print(changeSequence[1:20])
        for i in range(1, 700000):
            if n < changeSequence[i]:
                print(i-1)
                break
        n = int(input())


if __name__ == '__main__':
    main()