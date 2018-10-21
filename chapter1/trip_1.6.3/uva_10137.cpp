#include <iostream>
#include <iomanip>
#include <math.h>
using namespace std;
// UVa 10137

int main(int argc, char** argv) {
    bool run = true;
    while (run) {
        int numStudents;
        cin >> numStudents;
        if (numStudents != 0) {
            int input[numStudents];
            int amountAboveAvg = 0;
            int amountBelowAvg = 0;
            int dollars, cents;
            char decimal;
            int sum = 0;
            for (int i = 0; i < numStudents; i++) {
                cin >> dollars >> decimal >> cents;
                input[i] = (dollars * 100) + cents;
                sum += input[i];
            }
            int floorAvg = sum / numStudents;
            int ceilAvg = floorAvg;
            // Round up if there are decimal places beyond the cent
            if (sum % numStudents != 0) {
                ceilAvg += 1;
            }
            for (int i = 0; i < numStudents; i++) {
                if (input[i] < floorAvg) {
                    amountBelowAvg += floorAvg - input[i];
                } 
                if (input[i] > ceilAvg) {
                    amountAboveAvg += input[i] - ceilAvg;
                }
            }
            // cout << "Need: $" << amountAboveAvg << "\t\t\t";
            // cout << "Owed: $" << amountBelowAvg << endl;
            if (amountAboveAvg > amountBelowAvg) {
                cout << "$" << (amountAboveAvg / 100) << '.' << setw(2) << setfill('0') << (amountAboveAvg % 100) << endl;
            } else {
                cout << "$" << (amountBelowAvg / 100) << '.' << setw(2) << setfill('0') << (amountBelowAvg % 100) << endl;
            }
        } else {
            // End loop
            run = false;
        }
    }
    return 0;
}