#include <iostream>
#include <vector>

using namespace std;

int main (int argc, char ** argv) {
    int numCases;
    cin >> numCases;
    int currentCase = 1;
    while (currentCase <= numCases) {
        int x, y;
        cin >> x;
        cin >> y;
        int distance = y - x;
        int mid = distance/2;
        // vector<int> steps = new vector<int>(2);
        // steps[0] = 0;
        // steps[1] = 1;
        int stepSize = 1;
        int numSteps = 1;
        distance -= 1;
        while (distance > mid) {
            
            stepSize++;
            distance -= stepSize;
            numSteps++;
            cout << "\tTaken " << numSteps << " steps! Only " << distance << " distance left!\n";
        }
        if (distance == mid) {
            distance -= stepSize;
            numSteps++;
            cout << "\tTaken " << numSteps << " steps! Only " << distance << " distance left!\n";
        }
        while (distance > 0) {
            stepSize--;
            distance -= stepSize;
            numSteps++;
            cout << "\tTaken " << numSteps << " steps! Only " << distance << " distance left!\n";
        }
        cout << numSteps << endl;

        currentCase++;
    }

    return 0;
}