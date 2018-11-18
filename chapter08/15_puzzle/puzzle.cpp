/**
 * Problem:
 * 
 * Given two numbers n and k, determine the number of ways one can put k bishops
 * on an n × n chessboard so that no two of them are in attacking positions.
 * 
 * In: Each test case occupies a single line in
 * the input file and contains two integers n(1 ≤ n ≤ 8) and k(0 ≤ k ≤ n2).
 * A test case containing two zeros terminates the input.
 * 
 * Out: For each test case, print a line containing the total number of ways one can put the
 * given number of bishops on a chessboard of the given size so that no two of them lie in
 * attacking positions. You may safely assume that this number will be less than 10^15.
 * 
 * Search Space
 * 2 ways (bishop or no bishop) for n * n spots => 2^(n*n) ways to place n bishops on an n*n board
 * *ooooooo
 * `o``````     Once the row before (n ways) has been chosen, the following row has one less option (n-1 ways)
 * 
 * on a 4x4 board, b, row i has b[i] = b[i-1] * 
 */

#include <iostream>
#include <vector>

using namespace std;

const int MAX_CANDIDATES = 4; // L, R, U, D
const int MAX_SOLUTION_SIZE = 50;
// For traversing the puzzle
const int L = -1;
const int R = 1;
const int D = 4;
const int U = -4;
bool foundSolution = false;
int solutionSize;

void swap(int puzzle[], int squareK, int target) {
    int temp = puzzle[squareK];
    puzzle[squareK] = puzzle[target];
    puzzle[target] = temp;
}

/** 
 * Check whether the kth square on the row is threatened by a previously placed bishop
 * If so, move on
 * If note, include it as a possible candidate
*/
void constructCandidates(int squareK, int candidates[], int* numCandidates) {
    *numCandidates = 0;
    cout << "[1] numCandidates: " << *numCandidates << endl;
    if (squareK >= 4 && squareK <= 15) {
        candidates[*numCandidates] = U;
        *numCandidates = *numCandidates + 1;
    }
    cout << "[2] numCandidates: " << *numCandidates << endl;
    if (squareK >= 0 && squareK <= 11) {
        candidates[*numCandidates] = D;
        *numCandidates = *numCandidates + 1;
    }
    cout << "[3] numCandidates: " << *numCandidates << endl;
    if (squareK % 4 != 0) {
        candidates[*numCandidates] = L;
        *numCandidates = *numCandidates + 1;
    }
    cout << "[4] numCandidates: " << *numCandidates << endl;
    if ((squareK - 3) % 4 != 0) {
        candidates[*numCandidates] = R;
        *numCandidates = *numCandidates + 1;
    }
    cout << "[5] numCandidates: " << *numCandidates << endl;
}

/**
 * Prints the contents of the solution once it is found 
 */
void processSolution(int solutions[], int solutionSize) {
    for (int i = 0; i < solutionSize; i++) {
        if (solutions[i] == L) {
            cout << "L ";
        } else if (solutions[i] == R) {
            cout << "R ";
        } else if (solutions[i] == D) {
            cout << "D ";
        } else if (solutions[i] == U) {
            cout << "U ";
        }
    }
}

/**
 * Tests if our puzzle is solved
 */
bool isSolution(int puzzle[]) {
    if (puzzle[15] != 0) {
        return false;
    }
    for (int i = 0; i < 15; i++) {
        if (puzzle[i] != i+1) {
            return false;
        }
    }
    foundSolution = true;
    return true;
}
/**
 * Check if squareK is a solution (hit the end of a row)
 */
void backtrack(int solutions[], int squareK, int puzzle[], int lastMove) {
    int candidates[MAX_CANDIDATES];
    int numCandidates;
    cout << "Backtrack square: " << squareK << endl;
    if (isSolution(puzzle)) {
        processSolution(solutions, solutionSize);
    } else {
        // squareK++;
        constructCandidates(squareK, candidates, &numCandidates);
        for (int i = 0; i < numCandidates; i++) {
            // Avoids the infinite loop, no going back right away
            if (lastMove != -1 * candidates[i]) {
                // processSolution(solutions, solutionSize);
                // cout << "Solution Size: " << solutionSize << endl;
                // cout << "MAX SOL SIZE: " << MAX_SOLUTION_SIZE << endl;
                solutions[solutionSize] = candidates[i];
                solutionSize++;
                swap(puzzle, squareK, squareK + candidates[i]);
                squareK += candidates[i];
                backtrack(solutions, squareK, puzzle, candidates[i]);
                
                if (foundSolution) {
                    // Terminate early
                    return;
                } else {
                    solutionSize--;
                    swap(puzzle, squareK, squareK - candidates[i]);
                }
            }
        }
    }
}

int main(int argc, char** argv) {
    int numPuzzles;
    cin >> numPuzzles;
    for (int i = 0; i < numPuzzles; i++) {
        solutionSize = 0;
        int solutions[MAX_SOLUTION_SIZE];
        int puzzle[16];
        int inputSquare;
        int emptyIndex;
        for (int j = 0; j < 16; j++) {
            cin >> inputSquare;
            if (inputSquare == 0) {
                emptyIndex = j;
            }
            puzzle[j] = inputSquare;
        }
        cout << "\n\nPuzzle:\n";
        for (int j = 0; j < 16; j++) {
            cout << puzzle[j] << " ";
            if ((j + 1) % 4 == 0) {
                cout << endl;
            }
        }
        cout << "\nEmpty Index: " << emptyIndex << endl;
        backtrack(solutions, emptyIndex, puzzle, 100);
    }
    
    
    return 0;
}