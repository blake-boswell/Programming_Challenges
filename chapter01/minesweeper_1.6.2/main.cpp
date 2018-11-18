#include <iostream>
using namespace std;

char** processGrid(string inputGrid[], int numRows) {
    int numCols = inputGrid[0].length();
    cout << "\t\t\tNum cols: " << numCols << endl;
    static char outputGrid[100][100] = { '0' };
    cout << "\n\n\nOutput Grid Initialized\n\n\n";
    for (int i = 0; i < numRows; i++) {
        for (int j = 0; j < numCols; j++) {
            if (inputGrid[i][j] == '*') {
                outputGrid[i][j] = '*';
                // Check left
                if (((j - 1) >= 0) && (inputGrid[i][j-1] != '*')) {
                    cout << "\t\t\n\nCheck Left (i,j): (" << i << "," << j << ")\n\n";
                    outputGrid[i][j-1] += 1;
                } 
                // Check Right
                if (((j + 1) < numCols) && (inputGrid[i][j+1] != '*')) {
                    cout << "\t\t\n\nCheck Right (i,j): (" << i << "," << j << ")\n\n";
                    outputGrid[i][j+1] += 1;
                }
                // Check up
                if (((i - 1) >= 0) && (inputGrid[i-1][j] != '*')) {
                    cout << "\t\t\n\nCheck Up (i,j): (" << i << "," << j << ")\n\n";
                    outputGrid[i-1][j] += 1;
                }
                // Check up - left
                if ((((i - 1) >= 0) && ((j - 1) >= 0)) && (inputGrid[i-1][j-1] != '*')) {
                    cout << "\t\t\n\nCheck Up-Left (i,j): (" << i << "," << j << ")\n\n";
                    outputGrid[i-1][j-1] += 1;
                }
                // Check up - right
                if ((((i - 1) >= 0) && ((j + 1) < numCols)) && (inputGrid[i-1][j+1] != '*')) {
                    cout << "\t\t\n\nCheck Up-Right (i,j): (" << i << "," << j << ")\n\n";
                    outputGrid[i-1][j+1] += 1;
                }
                // Check down
                if (((i + 1) < numRows) && (inputGrid[i+1][j] != '*')) {
                    cout << "\t\t\n\nCheck Down (i,j): (" << i << "," << j << ")\n\n";
                    outputGrid[i+1][j] += 1;
                }
                // Check down - left
                if ((((i + 1) < numRows) && ((j - 1) >= 0)) && (inputGrid[i+1][j-1] != '*')) {
                    cout << "\t\t\n\nCheck Down-Left (i,j): (" << i << "," << j << ")\n\n";
                    outputGrid[i+1][j-1] += 1;
                }
                // Check down - right
                if ((((i + 1) < numRows) && ((j + 1) < numCols)) && (inputGrid[i+1][j+1] != '*')) {
                    cout << "\t\t\n\nCheck Down-Right (i,j): (" << i << "," << j << ")\n\n";
                    outputGrid[i+1][j+1] += 1;
                }
            }
        }
    }
    return outputGrid;
}

void showGrid(string inputGrid[], int numRows) {
    for (int i = 0; i < numRows; i++) {
        cout << inputGrid[i] << "\n";
    }
    cout << "\n";

}

int main(int argc, char** argv) {
    // Read n & m integers (0 < n, m <= 100) row & col
    int numRows;
    int numCols;
    string row;
    // char* inputGrid;
    // char* outputGrid;
    bool terminated = false;
    while (!terminated) {
        cin >> numRows;
        cin >> numCols;
        if (numRows == 0 && numCols == 0) {
            // Terminate program
            terminated = true;
        } else {
            string inputGrid[numRows];
            row = "";
            for (int i = 0; i < numRows; i++) {
                cin >> row;
                inputGrid[i] = row;
            }
            cout << "\n\n\nSuccessful Read\n\n\n";
            char** outputGrid = processGrid(inputGrid, numRows);
            cout << "\n\n\nProcess Grid Successful\n\n\n";
            showGrid(outputGrid, numRows);
        }
    }
    
    
        // Determine output representation of line
    return 0;
}