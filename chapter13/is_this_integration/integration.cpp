// UVa 10209
#include <iostream>
#include <cmath>

using namespace std;

// http://lbv-pc.blogspot.com/
int main (int argc, char** argv) {
    double a;
    while (cin >> a) {
        // Label sections to where area = 4*extraSection + 4*dottedSection + stripedArea
        double area = a * a;
        double cornerArea = area - (M_PI * area / 4);
        double centerArea = area - cornerArea * 2;
        double extraSection = area * (1 - M_PI/6 - sqrt(3)/4);
        double dottedSection = cornerArea - 2 * extraSection;
        double stripedArea = area - (4 * extraSection + 4 * dottedSection);

        printf("%.3f %.3f %.3f\n", stripedArea, 4 * dottedSection, 4 * extraSection);
    }

    return 0;
}