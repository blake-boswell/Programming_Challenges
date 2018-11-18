#include <iostream>
#include <math.h>

using namespace std;

long long numPermutations;
bool finished;

void constructCandidates(int candidates[], int *numCandidates, int numLeftVisible, int numRightVisible, int n, int k, int prev, int prevChoices[]) {
    *numCandidates = 0;
    if (k == 0) {
        if (numLeftVisible == 1) {
            candidates[0] = n;
            *numCandidates = 1;
            return;
        }
        if (numLeftVisible == n) {
            finished = true;
            numPermutations = 1;
            return;
        }
        if (numRightVisible == n) {
            finished = true;
            numPermutations = 1;
            return;
        }
        // First level      0, 1, 2,..., n-1
        for (int i = 0; i < n; i++) {
            candidates[i] = i;
        }
        *numCandidates = n;
    } else {
        // if (k == n - 1) {
        //     // Last choice
        //     if (numRightVisible == 1) {
        //         candidates[0] = n;
        //         *numCandidates = 1;
        //         return;
        //     }
        // }
        // All other levels
        int prevChoicesSize = n - k + 1;
        for (int i = 0; i < prevChoicesSize; i++) {
            if (prevChoices[i] != prev) {
                candidates[*numCandidates] = prevChoices[i];
                *numCandidates = *numCandidates + 1;
            }
        }
        
    }
}

void processSolution(int solution[], int n) {
    // cout << "Found solution!\n";
    // for (int i = 0; i < n; i++) {
    //     cout << solution[i] << " ";
    // }
    // cout << "\n\n";
    numPermutations++;
}

bool isSolution(int candidate[], int n, int k, int numLeftVisible, int numRightVisible) {
    bool isFullSolution = ((k + 1) == n);
    if (!isFullSolution) {
        return false;
    }

    int tallest = candidate[0];
    int leftVisibleCount = 1;
    for (int i = 1; i < n; i++) {
        if (candidate[i] > tallest) {
            if (leftVisibleCount >= numLeftVisible) {
                // End early, too many
                return false;
            }
            leftVisibleCount++;
            tallest = candidate[i];
        }
    }
    tallest = candidate[n-1];
    int rightVisibleCount = 1;
    for (int i = n - 2; i >= 0; i--) {
        if (candidate[i] > tallest) {
            if (rightVisibleCount >= numRightVisible) {
                // End early, too many
                return false;
            }
            rightVisibleCount++;
            tallest = candidate[i];
        }
    }

    if (leftVisibleCount == numLeftVisible && rightVisibleCount == numRightVisible) {
        return true;
    }
    return false;

}

void isSolutionTest() {
    int candidate[4];
    candidate[0] = 4;
    candidate[1] = 3;
    candidate[2] = 1;
    candidate[3] = 2;
    int p = 1;
    int r = 3;
    bool res = isSolution(candidate, 4, 0, p, r);
    if (res) {
        cout << "true\n";
    } else {
        cout << "false\n";
    }
}

void backtrack(int solution[], int n, int numLeftVisible, int numRightVisible, int k, int previousChoice, int previousChoices[]) {
    int candidates[n];
    int numCandidates;

    if (isSolution(solution, n, k, numLeftVisible, numRightVisible)) {
        processSolution(solution, n);
    } else {
        k++;
        if (k == n) {
            return;
        }
        constructCandidates(candidates, &numCandidates, numLeftVisible, numRightVisible, n, k, previousChoice, previousChoices);
        for (int i = 0; i < numCandidates; i++) {
            solution[k] = candidates[i];
            backtrack(solution, n, numLeftVisible, numRightVisible, k, solution[k], candidates);
            if (finished) {
                cout << "Finishing early" << endl;
                return;
            }
        }
    }

}

int main(int argc, char** argv) {
    // isSolutionTest();
    int n, numLeftVisible, numRightVisible;
    cin >> n >> numLeftVisible >> numRightVisible;
    // cout << n << "\n" << numLeftVisible << "\n" << numRightVisible << endl;
    int solution[n];
    numPermutations = 0;
    finished = false;
    backtrack(solution, n, numLeftVisible, numRightVisible, -1, 0, NULL);
    cout << numPermutations << endl;
    return 0;
}