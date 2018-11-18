// UVa 10310

#include <iostream>
#include <cmath>

using namespace std;

double distance(double x1, double y1, double x2, double y2) {
    return sqrt(((y2 - y1)*(y2 - y1)) + ((x2 - x1)*(x2 - x1)));
}

int main(int argc, char** argv) {
    int numHoles;
    double gopherX, gopherY, dogX, dogY;
    while (cin >> numHoles) {
        cin >> gopherX >> gopherY >> dogX >> dogY;
        double holeX[numHoles];
        double holeY[numHoles];
        for (int i = 0; i < numHoles; i++) {
            cin >> holeX[i];
            cin >> holeY[i];
        }
        bool escape = false;
        // Do stuff
        for (int i = 0; i < numHoles; i++) {
            // printf("Checking hole (%.3f,%.3f):\n", holeX[i], holeY[i]);
            double gopherDistance = distance(gopherX, gopherY, holeX[i], holeY[i]);
            double dogDistance = distance(dogX, dogY, holeX[i], holeY[i]);
            // printf("\tGopher dist: %.3f\tDog dist: %.3f", gopherDistance, dogDistance);
            if (gopherDistance <= dogDistance / 2) {
                printf("The gopher can escape through the hole at (%.3f,%.3f).\n", holeX[i], holeY[i]);
                escape = true;
                break;
            }
        }
        if (!escape) {
            printf("The gopher cannot escape.\n");
        }

        string emptyLine;
        getline(cin, emptyLine);
        cin.ignore();
    }

    return 0;
}