#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

// uVA 10038

// Mock of Java split found from:
// http://ysonggit.github.io/coding/2014/12/16/split-a-string-using-c.html
void split(const string str, char delimeter, vector<string>& splitStr) {
    int position = str.find(delimeter);
    int i = 0;
    // Look until position of delimeter is not found
    while (position != string::npos) {
        splitStr.push_back(str.substr(i, position - i));
        i = ++position;
        position = str.find(delimeter, position);
        if (position == string::npos) {
            splitStr.push_back(str.substr(i, str.length()));
        }
    }
}

int main(int argc, char** argv) {
    string line;
    while (getline(cin, line)) {
        bool jolly = true;
        vector<string> inputs;
        split(line, ' ', inputs);
        int numInts = stoi(inputs[0]);
        const int SIZE = numInts;
        bool differences[SIZE];
        if (numInts == 1) {
            cout << "Jolly" << endl;
        } else {
            // Init differences to false
            for (int i = 0; i < SIZE; i++) {
                differences[i] = false;
            }
            // Get absolute differences
            
            int previous = stoi(inputs[1]);
            int current = 0;
            for (int i = 2; i < numInts + 1; i++) {
                current = stoi(inputs[i]);
                int absDifference = current - previous;
                if (absDifference < 0) {
                    absDifference *= -1;
                }
                
                if (absDifference < SIZE && absDifference > 0) {
                    differences[absDifference] = true;
                }
                
                previous = current;
            }
            // Is jolly
            bool isJolly = true;
            for (int i = 1; i < SIZE; i++) {
                if (!differences[i]) {
                    isJolly = false;
                    break;
                }
            }
            if (isJolly) {
                cout << "Jolly" << endl;
            } else {
                cout << "Not jolly" << endl;
            }
        }
    }

    return 0;
}