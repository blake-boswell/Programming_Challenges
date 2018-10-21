#include <iostream>
#include <algorithm>
#include <cmath>

using namespace std;

// UVa 10041

int main(int argc, char** argv) {
    // Read input
    int numCases, currentCase = 1;
    cin >> numCases;
    while (currentCase <= numCases) {
        int numRelatives, median;
        cin >> numRelatives;
        int streetNumbers[numRelatives];
        for (int i = 0; i < numRelatives; i++) {
            cin >> streetNumbers[i];
            // cout << "Adding " << streetNumbers[i] << " to array" << endl;
        }
        sort(streetNumbers, streetNumbers + numRelatives);
        if (numRelatives % 2 == 0) {
            int upperMid = ceil(numRelatives / 2);
            int lowerMid = floor(numRelatives / 2);
            median = (streetNumbers[lowerMid] + streetNumbers[upperMid]) / 2;
        } else {
            median = streetNumbers[numRelatives/2];
        }

        // Find sum of distance
        int totalDistance = 0;
        for (int i = 0; i < numRelatives; i++) {
            int difference = median - streetNumbers[i];
            if (difference < 0) {
                difference *= -1;
            }
            totalDistance += difference;
        }
        cout << totalDistance << endl;
        
        currentCase++;
    }
    // Sort input
    // Get median
    // Add differences between each num
    return 0;
}