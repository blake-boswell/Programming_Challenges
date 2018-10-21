#include <iostream>

using namespace std;

// UVa 10010

bool checkUp(int i) {
    return (i - 1 >= 0);
}

bool checkDown(int i, int maxRow) {
    return (i + 1 < maxRow);
}

bool checkLeft(int j) {
    return (j - 1 >= 0);
}

bool checkRight(int j, int maxCol) {
    return (j + 1 < maxCol);
}

bool checkUpRight(int i, int j, int maxCol) {
    return (checkUp(i) && checkRight(j, maxCol));
}

bool checkUpLeft(int i, int j) {
    return (checkUp(i) && checkLeft(j));
}

bool checkDownRight(int i, int j, int maxRow, int maxCol) {
    return (checkDown(i, maxRow) && checkRight(j, maxCol));
}

bool checkDownLeft(int i, int j, int maxRow) {
    return (checkDown(i, maxRow) && checkLeft(j));
}

bool isInBounds(int i, int j, int maxRow, int maxCol) {
    return (i >= 0 && i < maxRow && j >= 0 && j < maxCol);
}

void findWordInGrid(char* grid, string currentWord) {
    
}

int main(int argc, char** argv) {
    // Read num cases
    int numCases, numRows, numCols, numWords;
    int currentCase = 1;
    cin >> numCases;
    while (currentCase <= numCases) {
        cin >> numRows;
        cin >> numCols;
        char grid[numRows][numCols];
        // cout << "Num Cases: " << numCases << "\nNum rows: " << numRows << "\nNum cols: " << numCols << endl;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                cin >> grid[i][j];
            }
        }
        cin >> numWords;
        string words[numWords];
        for (int i = 0; i < numWords; i++) {
            cin >> words[i];
        }

        // cout << "Grid:\n";
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                // cout << grid[i][j] << " ";
            }
            // cout << "\n";
        }
        // cout << "Words\n";
        for (int i = 0; i < numWords; i++) {
            // cout << words[i] << endl;
        }

        for (int word = 0; word < numWords; word++) {
            string currentWord = words[word];
            // cout << "Searching for " << currentWord << endl;
            bool wordFound = false;
            // Find word in grid (currentWord)
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    
                    if (tolower(currentWord[0]) == tolower(grid[i][j])) {
                        // cout << "First letter " << grid[i][j] << " found @ " << i << ", " << j << endl;
                        // First letter found
                        if (currentWord.length() == 1) {
                            // Word is found
                            cout << i + 1 << " " << j + 1 << endl;
                            wordFound = true;
                        } else if (checkUp(i)) {
                            int row = i - 1;
                            for (int letter = 1; letter < currentWord.length(); letter++) {
                                
                                if (isInBounds(row, j, numRows, numCols)) {
                                    if (tolower(grid[row][j]) == tolower(currentWord[letter])) {
                                        // cout << "Found " << currentWord[letter] << " at " << row << ", " << j << endl;
                                        if (letter == currentWord.length() - 1) {
                                            // Found a winner
                                            // cout << "\n\t[WORD FOUND] " << currentWord << " @ R, C: " << i << ", " << j << endl;
                                            cout << i + 1 << " " << j + 1 << endl;
                                            wordFound = true;
                                        }
                                    } else {
                                        // Not up
                                        break;
                                    }
                                    
                                } else {
                                    break;
                                }
                                row--;
                            }
                        } 
                        
                        if (checkUpLeft(i, j) && !wordFound) {
                            int row = i - 1;
                            int col = j - 1;
                            for (int letter = 1; letter < currentWord.length(); letter++) {
                                
                                // cout << row << ", " << col << "\n\n";
                                if (isInBounds(row, col, numRows, numCols)) {
                                    if (tolower(grid[row][col]) == tolower(currentWord[letter])) {
                                        // cout << "Found " << currentWord[letter] << " at " << row << ", " << col << endl;
                                        if (letter == currentWord.length() - 1) {
                                            // Found a winner
                                            // cout << "\n\t[WORD FOUND] " << currentWord << " @ R, C: " << i << ", " << j << endl;
                                            cout << i + 1 << " " << j + 1 << endl;
                                            wordFound = true;
                                        }
                                    } else {
                                        // Not up
                                        // cout << "Aborting search... Found " << grid[row][col] << " when we were looking for " << currentWord[letter] << endl;
                                        break;
                                    }
                                    
                                } else {

                                    break;
                                }
                                row--;
                                col--;
                            }
                        }
                        
                        
                        if (checkUpRight(i, j, numCols) && !wordFound) {
                            int row = i - 1;
                            int col = j + 1;
                            for (int letter = 1; letter < currentWord.length(); letter++) {
                                
                                // cout << row << ", " << col << "\n\n";
                                if (isInBounds(row, col, numRows, numCols)) {
                                    if (tolower(grid[row][col]) == tolower(currentWord[letter])) {
                                        // cout << "Found " << currentWord[letter] << " at " << row << ", " << col << endl;
                                        if (letter == currentWord.length() - 1) {
                                            // Found a winner
                                            // cout << "\n\t[WORD FOUND] " << currentWord << " @ R, C: " << i << ", " << j << endl;
                                            cout << i + 1 << " " << j + 1 << endl;
                                            wordFound = true;
                                        }
                                    } else {
                                        // Not up
                                        // cout << "Aborting search... Found " << grid[row][col] << " when we were looking for " << currentWord[letter] << endl;
                                        break;
                                    }
                                    
                                } else {

                                    break;
                                }
                                row--;
                                col++;
                            }
                        }
                         
                        if (checkLeft(j && !wordFound)) {
                            int col = j - 1;
                            for (int letter = 1; letter < currentWord.length(); letter++) {
                                
                                if (isInBounds(i, col, numRows, numCols)) {
                                    if (tolower(grid[i][col]) == tolower(currentWord[letter])) {
                                        // cout << "Found " << currentWord[letter] << " at " << i << ", " << col << endl;
                                        if (letter == currentWord.length() - 1) {
                                            // Found a winner
                                            // cout << "\n\t[WORD FOUND] " << currentWord << " @ R, C: " << i << ", " << j << endl;
                                            cout << i + 1 << " " << j + 1 << endl;
                                            wordFound = true;
                                        }
                                    } else {
                                        // Not up
                                        break;
                                    }
                                    
                                } else {
                                    break;
                                }
                                col--;
                            }
                        }
                        
                        if (checkRight(j, numCols) && !wordFound) {
                            int col = j + 1;
                            for (int letter = 1; letter < currentWord.length(); letter++) {
                                
                                if (isInBounds(i, col, numRows, numCols)) {
                                    if (tolower(grid[i][col]) == tolower(currentWord[letter])) {
                                        // cout << "Found " << currentWord[letter] << " at " << i << ", " << col << endl;
                                        if (letter == currentWord.length() - 1) {
                                            // Found a winner
                                            // cout << "\n\t[WORD FOUND] " << currentWord << " @ R, C: " << i << ", " << j << endl;
                                            cout << i + 1 << " " << j + 1 << endl;
                                            wordFound = true;
                                        }
                                    } else {
                                        // Not up
                                        break;
                                    }
                                    
                                } else {
                                    break;
                                }
                                col++;
                            }
                        }
                        
                        if (checkDown(i, numRows) && !wordFound) {
                            int row = i + 1;
                            for (int letter = 1; letter < currentWord.length(); letter++) {
                                
                                if (isInBounds(row, j, numRows, numCols)) {
                                    if (tolower(grid[row][j]) == tolower(currentWord[letter])) {
                                        // cout << "Found " << currentWord[letter] << " at " << row << ", " << j << endl;
                                        if (letter == currentWord.length() - 1) {
                                            // Found a winner
                                            // cout << "\n\t[WORD FOUND] " << currentWord << " @ R, C: " << i << ", " << j << endl;
                                            cout << i + 1 << " " << j + 1 << endl;
                                            wordFound = true;
                                        }
                                    } else {
                                        // Not up
                                        break;
                                    }
                                    
                                } else {
                                    break;
                                }
                                row++;
                            }
                        }
                        
                        if (checkDownLeft(i, j, numRows) && !wordFound) {
                            int row = i + 1;
                            int col = j - 1;
                            for (int letter = 1; letter < currentWord.length(); letter++) {
                                
                                // cout << row << ", " << col << "\n\n";
                                if (isInBounds(row, col, numRows, numCols)) {
                                    if (tolower(grid[row][col]) == tolower(currentWord[letter])) {
                                        // cout << "Found " << currentWord[letter] << " at " << row << ", " << col << endl;
                                        if (letter == currentWord.length() - 1) {
                                            // Found a winner
                                            // cout << "\n\t[WORD FOUND] " << currentWord << " @ R, C: " << i << ", " << j << endl;
                                            cout << i + 1 << " " << j + 1 << endl;
                                            wordFound = true;
                                        }
                                    } else {
                                        // Not up
                                        // cout << "Aborting search... Found " << grid[row][col] << " when we were looking for " << currentWord[letter] << endl;
                                        break;
                                    }
                                    
                                } else {

                                    break;
                                }
                                row++;
                                col--;
                            }
                        } 
                        
                        if (checkDownRight(i, j, numRows, numCols) && !wordFound) {
                            int row = i + 1;
                            int col = j + 1;
                            for (int letter = 1; letter < currentWord.length(); letter++) {
                                
                                // cout << row << ", " << col << "\n\n";
                                if (isInBounds(row, col, numRows, numCols)) {
                                    if (tolower(grid[row][col]) == tolower(currentWord[letter])) {
                                        // cout << "Found " << currentWord[letter] << " at " << row << ", " << col << endl;
                                        if (letter == currentWord.length() - 1) {
                                            // Found a winner
                                            // cout << "\n\t[WORD FOUND] " << currentWord << " @ R, C: " << i << ", " << j << endl;
                                            cout << i + 1 << " " << j + 1 << endl;
                                            wordFound = true;
                                        }
                                    } else {
                                        // Not up
                                        // cout << "Aborting search... Found " << grid[row][col] << " when we were looking for " << currentWord[letter] << endl;
                                        break;
                                    }
                                    
                                } else {

                                    break;
                                }
                                row++;
                                col++;
                            }
                        }
                        if (wordFound) {
                            break;
                        }

                        
                        
                        // bool leftInBounds = checkLeft(j);
                        // bool rightInBounds = checkRight(j, numCols);
                        // bool upInBounds = checkUp(i);
                        // bool downInBounds = checkDown(i, numRows);
                        // Loop throught the directions and check up - upLeft - upRight - left - right - down - downLeft - downRight 
                    }
                }
                if (wordFound) {
                    break;
                }
            }
        }

        currentCase++;
        if (currentCase <= numCases) {
            cout << "\n";
        }
    }

    // Read m n
    // Read m lines of n letters
    // Read numWords
    // Read numWords words to search for

    // Output m n of topmost & leftmost occurrence of the word (starting point)
    // All words can be found 

    return 0;
}