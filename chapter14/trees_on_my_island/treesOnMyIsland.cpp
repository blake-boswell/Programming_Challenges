// UVa 10088

#include <iostream>
#include <cmath>

using namespace std;

struct Point {
    long long x;
    long long y;
    Point() {
        this->x = -1;
        this->y = -1;
    }
    Point(long long x, long long y) {
        this->x = x;
        this->y = y;
    }
};

int gcd(int a, int b) {
    // printf("Starting GCD(%i, %i)\n", a, b);
    if (a < b) {
        // swap a & b
        int temp = b;
        b = a;
        a = temp;
    }
    int r;
    // while lesser value isn't 0, if it is return a (greater)
    // if slope of the line is horizontal/ vertical -> return the distance it spans
    while (b) {
        // printf("\tGCD of %i and %i:\n\t", a, b);
        r = b;
        b = a % b;
        a = r;
        // printf("\t%i\n", a);
    }
    // printf("\tFinal GCD: %i\n", a);
    return a;
}

// Shoelace formula
long double getArea(Point polygon[], int numVertices) {
    // A = 1/2 abs(a - b)
    // a = for i to numVertices - 1
    long long a = 0;
    for (int i = 0; i < numVertices - 1; i++) {
        a += (polygon[i].x * polygon[i + 1].y); 
    }
    a += (polygon[numVertices - 1].x * polygon[0].y);

    long long b = 0;
    for (int i = 0; i < numVertices - 1; i++) {
        b += (polygon[i + 1].x * polygon[i].y);
    }
    b += (polygon[0].x * polygon[numVertices - 1].y);


    // printf("\t\t\t%lli %lli\n", a, b);
    long double area = .5 * (long double)abs(a - b);
    // printf("Area: %Lf\n", area);
    return area;
}

long long getPointsOnBoundary(Point polygon[], int numVertices) {
    long long numPoints = 0;
    long long deltaX = abs(polygon[0].x - polygon[numVertices - 1].x);
    long long deltaY = abs(polygon[0].y - polygon[numVertices - 1].y);
    // printf("For edge %lli,%lli -> %lli,%lli\n", polygon[numVertices - 1].x, polygon[numVertices - 1].y, polygon[0].x, polygon[0].y);
    numPoints += gcd(deltaX, deltaY);
    for (int i = 1; i < numVertices; i++) {
        deltaX = abs(polygon[i].x - polygon[i - 1].x);
        deltaY = abs(polygon[i].y - polygon[i - 1].y);
        // printf("For edge %lli,%lli -> %lli,%lli\n", polygon[i - 1].x, polygon[i - 1].y, polygon[i].x, polygon[i].y);
        numPoints += gcd(deltaX, deltaY);
    }
    // printf("NumPoints: %lld\n", numPoints);
    return numPoints;
}

long long picks(Point polygon[], int numVertices) {
    long double area = getArea(polygon, numVertices);
    long long pointsOnBoundary = getPointsOnBoundary(polygon, numVertices);
    return area - (double(pointsOnBoundary) / 2) + 1;
}


int main(int argc, char** argv) {
    int numVertices;
    while (cin >> numVertices && numVertices != 0) {
        // int polygonX[numVertices];
        // int polygonY[numVertices];
        Point polygon[numVertices];
        long long x, y;
        for (int i = 0; i < numVertices; i++) {
            // cin >> polygonX[i] >> polygonY[i];
            cin >> x >> y;
            polygon[i].x = x;
            polygon[i].y = y;
        }

        // Picks
        long long numTrees = picks(polygon, numVertices);
        printf("%lli\n", numTrees);

    }

    return 0;
}