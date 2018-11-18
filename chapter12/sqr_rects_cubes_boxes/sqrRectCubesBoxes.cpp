// UVa 10177

#include <iostream>
#include <cmath>

using namespace std;

long long findNumSquares(int length, int dimension) {
    long long sum = 0;
    for (int i = 1; i <= length; i++) {
        sum += pow(i, dimension);
    }
    return sum;
}

// From lecture
long long findNumRectangles(int length, int dimension) {
    long long num = pow((length * (length + 1) / 2), dimension) - findNumSquares(length, dimension);
    return num;
}

int main(int argc, char** argv) {
    int length;
    while (cin >> length) {
        long long num2DSquares = findNumSquares(length, 2);
        long long num2DRectangles = findNumRectangles(length, 2);
        long long num3DSquares = findNumSquares(length, 3);
        long long num3DRectangles = findNumRectangles(length, 3);
        long long num4DSquares = findNumSquares(length, 4);
        long long num4DRectangles = findNumRectangles(length, 4);
        cout << num2DSquares << " " << num2DRectangles << " " 
             << num3DSquares << " " << num3DRectangles << " "
             << num4DSquares << " " << num4DRectangles << endl;
    }
    return 0;
}