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

const int MAX_CANDIDATES = 1000;
bool foundSolution = false;
long long solutionCount;
// int numBishopsOnBoard = 0;

/**
 * Check if position is a threat diagonally to squareK
 */
bool diagonalThreat(long long solutions[], int squareK, int i, int j) {
    if (abs(squareK - j) == abs(i - solutions[j])) {
        cout << "Square " << squareK << " has a threat from sqaure " << j << endl;
        return true;
    } else {
        return false;
    }
}

/** 
 * Check whether the kth square on the row is threatened by a previously placed bishop
 * If so, move on
 * If note, include it as a possible candidate
*/
void constructCandidates(long long solutions[], int squareK, int numBishops, int candidates[], int* numCandidates) {
    bool legalMove;

    *numCandidates = 0;
    for (int i = 1; i <= numBishops; i++) {
        legalMove = true;
        for (int j = 1; j < squareK; j++) {
            if (diagonalThreat(solutions, squareK, i, j)) {
                legalMove = false;
            }
        }
        if (legalMove) {
            candidates[*numCandidates] = i;
            *numCandidates = *numCandidates + 1;
            cout << *numCandidates << endl;
        }
    }
}

/**
 * Counts a solution once it is constructed 
 */
void processSolution() {
    solutionCount++;
}

/**
 * Tests to see if we have enough bishops to stop adding them to the solution
 */
bool isSolution(int* numBishopsOnBoard, int numBishops) {
    return (numBishops == *numBishopsOnBoard);
}
/**
 * Check if squareK is a solution (hit the end of a row)
 */
void bishopsBacktracker(long long solutions[], int squareK, int numBishops, int* numBishopsOnBoard) {
    *numBishopsOnBoard = *numBishopsOnBoard + 1;
    cout << "Num Bishops: " << *numBishopsOnBoard << endl;
    int candidates[MAX_CANDIDATES];
    int numCandidates;

    if (isSolution(numBishopsOnBoard, numBishops)) {
        processSolution();
    } else {
        cout << "SquareK: " << squareK << endl;
        squareK++;
        constructCandidates(solutions, squareK, numBishops, candidates, &numCandidates);
        for (int i = 0; i < numCandidates; i++) {
            cout << "i: " << i << " k: " << squareK << endl;
            solutions[squareK] = candidates[i];
            bishopsBacktracker(solutions, squareK, numBishops, numBishopsOnBoard);
            *numBishopsOnBoard = *numBishopsOnBoard - 1;
            if (foundSolution) {
                // Terminate early
                return;
            }
        }
        
    }
}

int main(int argc, char** argv) {
    int rowSize, numBishops;
    cin >> rowSize;
    cin >> numBishops;
    while (!(rowSize == 0 && numBishops == 0)) {
        if (rowSize <= 0) {
            cout << 0 << endl;
        } else {
            cout << "Here we go" << endl;
            long long solutions[65];
            cout << "Big ass array" << endl;
            solutionCount = 0;
            int numBishopsOnBoard = 0;
            bishopsBacktracker(solutions, 0, numBishops, &numBishopsOnBoard);
            cout << solutionCount << endl;
        }
        
        cin >> rowSize;
        cin >> numBishops;
    }
    
    return 0;
}