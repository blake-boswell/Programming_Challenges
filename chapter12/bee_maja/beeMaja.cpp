// UVa 10182
#include <iostream>
#include <cmath>

using namespace std;

int getRing(int hcNum) {
    int ring = 0;
    int k = 1;
    while (hcNum > k) {
        ring++;
        k += ring * 6;
    }
    return ring;
}

bool isEndOfRing(int hcNum) {
    return (hcNum == getRing(hcNum));
}

int getRingEnd(int step) {
    step++;
    return (1 + (3 * (step - 1) * step));
}

int main(int argc, char** argv) {
    int hcNum;
    while (cin >> hcNum) {
        int step = getRing(hcNum);
        int x, y;
        if (isEndOfRing(hcNum)) {
            // Maps to end of a ring
            x = step;
            y = 0;
        } else {
            int deltaX = 0;
            int deltaY = 0;
            // Get the end of ring
            int ringEnd = getRingEnd(step);
            int currentHc = ringEnd;
            int count = 0;
            // cout << "Ring End: " << ringEnd << endl;
            // cout << "Target: " << hcNum << endl;
            // cout << "Step: " << step << endl;
            while (currentHc != hcNum) {
                for (int i = 0; i < step; i++) {
                    // Take a step
                    int code = count % 6;
                    // cout << "Code: " << code << endl;
                    switch (code) {
                        case 0:
                            // Going up
                            deltaY -= 1;
                            break;
                        case 1:
                            // Going up-left
                            deltaX -= 1;
                            break;
                        case 2:
                            // Going down-left
                            deltaX -= 1;
                            deltaY += 1;
                            break;
                        case 3:
                            // Going down
                            deltaY += 1;
                            break;
                        case 4:
                            // Going down-right
                            deltaX += 1;
                            break;
                        case 5:
                            // Going up-right
                            deltaX += 1;
                            deltaY -= 1;
                            break;
                    }
                    currentHc--;
                    // cout << "Current location: " << currentHc << endl;
                    if (currentHc == hcNum) {
                        break;
                    }
                }
                
                count++;
            }
            x = step + deltaX;
            y = deltaY;

        }

        cout << x << " " << y << endl;
    }
    

    return 0;
}