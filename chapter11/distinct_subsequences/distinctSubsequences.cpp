// UVa 10069

#include <iostream>

using namespace std;

int main(int argc, char** argv) {
    int numTestCases;
    int testCase = 1;
    cin >> numTestCases;
    cin.ignore();
    while (testCase <= numTestCases) {
        string original;
        getline(cin, original);
        cout << "Orig: " << original << endl;
        string sub;
        getline(cin, sub);
        cout << "Sub: " << sub << endl;

        int originalLen = original.length();
        int subLen = sub.length();

        // occ[i][j] gives the number of subsequences of sub[1..i] that are in original[1..j]
        // keep the 0th row for empty sub && 0th col for empty original
        /**
         * | | |b|a|b|g|b|a|g|
         * | |1|1|1|1|1|1|1|1|
         * |b|0|1|1|2|2|3|3|3|
         * |a|0|0|1|1|1|1|4|4|
         * |g|0|0|0|0|1|1|1|5| <- answer
         */
        long long occ[subLen + 1][originalLen + 1];
        // The occurences of an empty string in a sequence is 1
        for (int i = 0; i < originalLen + 1; i++) {
            occ[0][i] = 1;
        }
        // The occurences of a string in an empty sequence is 0
        // Except an empty sequence in an empty sequnence is still 1
        for (int i = 1; i < subLen + 1; i++) {
            occ[i][0] = 0;
        }

        for (int i = 1; i < subLen + 1; i++) {
            for (int j = 1; j < originalLen + 1; j++) {
                // When the letter in sub we are on appears in original
                // Add the occurences of the previous sub[1..i-1] in original[1..j-1] with the occurences of sub[1..i] in original[1..j-1]
                if (sub[i - 1] == original[j - 1]) {
                    occ[i][j] = occ[i - 1][j - 1] + occ[i][j - 1];
                } else {
                    occ[i][j] = occ[i][j - 1];
                }
            }
        }

        cout << "GRID:" << endl;
        for (int i = 0; i < subLen + 1; i++) {
            for (int j = 0; j < originalLen + 1; j++) {
                cout << occ[i][j] << " ";
            }
            cout << endl;
        }

        cout << occ[subLen][originalLen] << endl;

        testCase++;
    }

    return 0;
}