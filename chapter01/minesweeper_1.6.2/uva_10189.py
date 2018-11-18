# uVa 10189
"""
Module Docstring
"""

__author__ = "Blake Boswell"

# f = open("out.txt", "w")
def processGrid(inputGrid):
    """ Function used to get an output grid with hint numbers for the given minesweeper input grid """
    # Create an output matrix that is the same size, initialized with 0's
    numRows = len(inputGrid)
    numCols = len(inputGrid[0])
    # https://stackoverflow.com/questions/6667201/how-to-define-a-two-dimensional-array-in-python for making matrix
    outputGrid = [[0 for x in range(0, numCols)] for y in range(0, numRows)]
    for i in range(0, numRows):
        for j in range(0, numCols):
            if inputGrid[i][j] == '*':
                outputGrid[i][j] = '*'
                # Check left
                if ((j - 1) >= 0) and (inputGrid[i][j-1] != '*'):
                    outputGrid[i][j-1] += 1
                # Check Right
                if ((j + 1) < len(inputGrid[i])) and (inputGrid[i][j+1] != '*'):
                    outputGrid[i][j+1] += 1
                # Check up
                if ((i - 1) >= 0)and (inputGrid[i-1][j] != '*'):
                    outputGrid[i-1][j] += 1
                # Check up - left
                if (((i - 1) >= 0) and ((j - 1) >= 0)) and (inputGrid[i-1][j-1] != '*'):
                    outputGrid[i-1][j-1] += 1
                # Check up - right
                if (((i - 1) >= 0) and ((j + 1) < len(inputGrid[i]))) and (inputGrid[i-1][j+1] != '*'):
                    outputGrid[i-1][j+1] += 1
                # Check down
                if ((i + 1) < len(inputGrid)) and (inputGrid[i+1][j] != '*'):
                    outputGrid[i+1][j] += 1
                # Check down - left
                if (((i + 1) < len(inputGrid)) and ((j - 1) >= 0)) and (inputGrid[i+1][j-1] != '*'):
                    outputGrid[i+1][j-1] += 1
                # Check down - right
                if (((i + 1) < len(inputGrid)) and ((j + 1) < len(inputGrid[i]))) and (inputGrid[i+1][j+1] != '*'):
                    outputGrid[i+1][j+1] += 1
    return outputGrid


                


def getInputGrid(gridDimensions):
    """ Function used to get the inputs of the incoming grid """
    # Gets (row, col)
    numRows = gridDimensions[0]
    numCols = gridDimensions[1]
    inputGrid = list()
    if numRows != 0 and numCols != 0:
        currentRow = 0
        while currentRow < numRows:
            row = input()
            inputGrid.append(row)
            currentRow += 1
    return inputGrid

def getGridDimensions():
    """ Function used to get the dimensions of the incoming grid """
    dimensionInput = input()
    # To deal with blank lines
    while dimensionInput == '':
        dimensionInput = input()
    gridDimensions = tuple(map(int, dimensionInput.split(' ')))
    return gridDimensions

def showGrid(grid):
    """ Function to print the grid to output """
    for i in range(0, len(grid)):
        for j in range(0, len(grid[0])):
            print(grid[i][j], end='')
            # f.write(str(grid[i][j]))
        print()
        # f.write("\n")

def main():
    """ Main entry point of the app """
    
    run = True
    fieldNum = 1
    while run:
        gridDimensions = getGridDimensions()
        if gridDimensions[0] == 0 and gridDimensions[1] == 0:
            run = False
        else: 
            inputGrid = getInputGrid(gridDimensions)
            outputGrid = processGrid(inputGrid)
            # f.write(f"Field #{fieldNum}:\n")
            if fieldNum > 1:
                print()
            print(f"Field #{fieldNum}:")
            showGrid(outputGrid)
            fieldNum += 1
            # f.write("\n")
    

if __name__ == "__main__":
    """ This is executed when run from the command line """
    main()