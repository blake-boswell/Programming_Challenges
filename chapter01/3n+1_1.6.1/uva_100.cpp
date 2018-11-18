#include <iostream>
using namespace std;
// UVa 100

int main(int argc, char** argv) {
    int low, high, cylceLength, maxCycleLength;
    bool didSwap = false;
    long int n;

    while (cin >> low >> high) {
        didSwap = false;
        // Check if values were entered reversed
        if (low > high) {
            didSwap = true;
            int temp = low;
            low = high;
            high = temp;
        }

        maxCycleLength = 0;
        for (int i = low; i <= high; i++) {
            n = i;
            cylceLength = 1;
            while (n != 1) {
                if (n % 2 == 0) {
                    n = n / 2;
                } else {
                    n = (n * 3) + 1;
                }
                cylceLength ++;
            }
            if (cylceLength > maxCycleLength) {
                maxCycleLength = cylceLength;
            }
        }
        if (didSwap) {
            cout << high << " " << low << " " << maxCycleLength << endl;
        } else {
            cout << low << " " << high << " " << maxCycleLength << endl;
        }
        
    }
    return 0;
}