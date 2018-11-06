// UVa 10161

#include <iostream>
#include <cmath>

using namespace std;

int findClosestSquare(int n) {
    double square = round(sqrt(n));
    return (int)square;

}

int main(int argc, char** argv) {
    int stepNum;
    cin >> stepNum;
    while (stepNum != 0) {
        int row, col;
        int square = findClosestSquare(stepNum);
        int step = square * square;
        if (step % 2 == 0) {
            // Closest square step is even
            row = 1;
            col = square;
            if (step < stepNum) {
                // Within [col+1][1..n-1]
                col++;
                step++;
                int diff = stepNum - step;
                row += diff;
            } else if (step > stepNum) {
                // Within up n-1 steps
                int diff = step - stepNum;
                row += diff;
            }
        } else {
            // Closest square step is odd
            row = square;
            col = 1;
            if (step < stepNum) {
                // Within row + 1, right n-1 steps
                row++;
                step++;
                int diff = stepNum - step;
                col += diff;
            } else if (step > stepNum) {
                // Within right n-1 steps
                int diff = step - stepNum;
                col += diff;
            }
        }

        cout << col << " " << row << endl;

        cin >> stepNum;
    }

    return 0;
}