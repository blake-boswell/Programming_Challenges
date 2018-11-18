// UVa 10195

#include <iostream>
#include <cmath>
#define PI 3.14159265

using namespace std;

double degrees(double x) {
    return x * 180.0 / PI;
}

// Using https://www.mathopenref.com/triangleincircle.html
int main(int argc, char** argv) {
    double a, b, c;
    while (cin >> a >> b >> c) {
        double A = acos((b*b + c*c - a*a) / (2*b*c));
        double B = acos((a*a + c*c - b*b) / (2*a*c));
        double C = acos((b*b + a*a - c*c) / (2*a*b));
        if (a == 0.0) {
            B = 0.0;
            C = 0.0;
        }
        if (c == 0.0) {
            A = 0.0;
            B = 0.0;
        }
        if (b == 0.0) {
            A = 0.0;
            C = 0.0;
        }
        // printf("A: %f\tB: %f\tC: %f\tTOTAL: %f\n", degrees(A), degrees(B), degrees(C), degrees(A + B + C));
        double base = c;
        double height = b * sin(A);
        double area = base * height / 2;
        double perimeter = a + b + c;

        double radius;
        if (perimeter > 0.0) {
            radius = 2 * area / perimeter;
        } else {
            radius = 0.0;
        }
        
        printf("The radius of the round table is: %.3f\n", radius);
    }

    return 0;
}