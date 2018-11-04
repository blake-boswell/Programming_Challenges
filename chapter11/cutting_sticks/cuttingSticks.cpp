// UVa 10003

#include <iostream>
#define INT_MAX __INT_MAX__

using namespace std;

// int cost(int start, int end) {

// }

int main(int argc, char** argv) {
    int stickLength, numCuts;
    
    while (true) {
        cin >> stickLength;
        if (stickLength == 0) {
            break;
        }
        cin >> numCuts;
        numCuts += 2;
        int cuts[numCuts];
        cuts[0] = 0;
        cuts[numCuts - 1] = stickLength;
        // cost[i][j]: min price to cut from index i to j
        int cost[numCuts][numCuts];

        for (int i = 0; i < numCuts; i++) {
            for (int j = 0; j < numCuts; j++) {
                cost[i][j] = -1;
                if (i == j-1) {
                    cost[i][j] = 0;
                }
            }
        }

        for (int i = 1; i < numCuts - 1; i++) {
            // Given in increasing  order
            cin >> cuts[i];
        }

        // We've got to build the matrix bottom up
        /**
         * | |0|7|8|9|10| <-- answer
         * | | |0|4|5| 6|
         * | | | |0|2| 3|
         * | | | | |0| 1|
         * | | | | | | 0|
         */
        for (int i = numCuts - 1; i >= 0; i--) {
            for (int j = i + 1; j < numCuts; j++) {
                // Cost to cut from i to i + 1 is 0, you can't cut it
                if (i == j - 1) {
                    cost[i][j] = 0;
                } else {
                    int min = INT_MAX;
                    // Find the minimum of our cutting options.
                    for (int k = i + 1; k < j; k++) {
                        if (min > (cost[i][k] + cost[k][j])) {
                            min = cost[i][k] + cost[k][j];
                        }
                    }
                    cost[i][j] = min + (cuts[j] - cuts[i]);
                }
            }
        }

        // cout << endl;
        // for (int i = 0; i < numCuts; i++) {
        //     for (int j = 0; j < numCuts; j++) {
        //         cout << cost[i][j] << " ";
        //     }
        //     cout << endl;
        // }

        cout << "The minimum cutting is " << cost[0][numCuts - 1] << "." << endl;
    }
    

    return 0;
}