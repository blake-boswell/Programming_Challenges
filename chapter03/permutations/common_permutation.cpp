#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

// UVa 10252

int main(int argc, char** argv) {
    // given 2 strings, find the longest string that is a permutation of similar letters from the strings
    string first, second;
    while (getline(cin, first) && getline(cin, second)) {
        // Alpha-sort the words
        sort(first.begin(), first.end());
        sort(second.begin(), second.end());
        string similarChars = "";
        const int secondLength = second.length();
        bool usedLetters[secondLength];
        for (int i = 0; i < second.length(); i++) {
            usedLetters[i] = false;
        }
        for (int i = 0; i < first.length(); i++) {
            for (int j = 0; j < second.length(); j++) {
                if (first[i] == second[j] && !usedLetters[j]) {
                    // similarChars.push_back(first[i]);
                    similarChars += first[i];
                    usedLetters[j] = true;
                    break;  // Move onto next letter, we found a match!
                }
            }
        }
        cout << similarChars << endl;

    }

    return 0;
}