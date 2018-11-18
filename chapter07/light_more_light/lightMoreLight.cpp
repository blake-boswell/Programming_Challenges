// UVa 10110
// Getting the number of divisors took too long
// http://www.algorithmist.com/index.php/UVa_10110 makes a good point that there is always an even number of divisors if the num isn't a square

#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

int main(int argc, char** argv) {
    unsigned int numLights;
    cin >> numLights;
    while (numLights != 0) {
        // vector<int> divisors;
        bool hasEvenNumFactors = true;
        int root = sqrt(numLights);
        if (root * root == numLights) {
            // If it is a square we need to remove 1
            hasEvenNumFactors = false;
        }
        
        if (hasEvenNumFactors) {
            cout << "no" << endl;
        } else {
            cout << "yes" << endl;
        }

        cin >> numLights;
    }

    return 0;
}