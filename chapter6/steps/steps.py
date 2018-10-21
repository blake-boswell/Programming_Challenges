# UVa 846

def main():
    numCases = int(input())
    currentCase = 1
    while currentCase <= numCases:
        start, end = input().split(" ")
        start = int(start)
        end = int(end)
        distance = end - start
        numSteps = 0
        if distance != 0:
            totalDistanceTraveled = 0
            nextStep = 1
            # every other step we take we want to increment the size (simulating stepping from both ends)
            # EX: 1 1 for 2, 1 1 1, for 3 (we are adding on the outter pieces first and working to the mid)
            # while loop can go over (EX: 1 2 1 is still 3 steps for the distance of 3, even though the distance added up is 4)
            count = 0
            while distance > totalDistanceTraveled:
                if count % 2 == 0 and count != 0:
                    nextStep += 1
                totalDistanceTraveled += nextStep
                numSteps += 1
                count += 1
        print(numSteps)
        currentCase += 1

if __name__ == "__main__":
    main()