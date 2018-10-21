#include <iostream>

using namespace std;

// Ideas: Split board into white and black (they won't effect each other)
// We can put a bishop on white then black, and so on
// the idea could be to turn the board 45 degrees so its columns of 1,2,..,n-1,n,n-1,..,2,1
// if the bishop is on col k, no other bishop can be there.

void bishops(int n, int numBishops) {

}

int main(int argc, char** argv) {
    int n, numBishops;
    while (cin >> n >> numBishops) {
        if (n != 0 && numBishops != 0) {
            bool board[n * n];
            bishops(n, numBishops);
        }
    }

    return 0;
}