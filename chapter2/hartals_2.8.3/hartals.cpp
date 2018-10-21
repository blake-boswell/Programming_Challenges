#include <iostream>
#include <cmath>

// UVa 10050

using namespace std;

bool isFriday(int day) {
    int weekNum = ceil((float)day / 7);
    if (7 * (weekNum - 1) + 6 == day) {
        return true;
    } 
    return false;
}

bool isSaturday(int day) {
    int weekNum = ceil((float)day / 7);
    if (7 * (weekNum - 1) + 7 == day) {
        return true;
    }
    return false;
}

int main(int argc, char** argv) {
    // Read input
    int currentCase = 0;
    int numCases;
    cin >> numCases;
    while (currentCase < numCases) {
        int numDays, numParties;
        cin >> numDays;
        cin >> numParties;
        int hartalNums[numParties];
        for (int i = 0; i < numParties; i++) {
            cin >> hartalNums[i];
        }
        int missingDays = 0;
        for (int i = 1; i <= numDays; i++) {
            // Check for Fri/Sat
            if (!isFriday(i) && !isSaturday(i)) {
                for (int j = 0; j < numParties; j++) {
                    if (i % hartalNums[j] == 0) {
                        missingDays++;
                        break;
                    }
                }
            }
        }
        cout << missingDays << endl;
        currentCase++;
    }
    return 0;
}