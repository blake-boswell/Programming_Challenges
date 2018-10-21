#include <iostream>
#include <vector>
#include <fstream>

// UVa 10152

using namespace std;

int main(int argc, char** argv) {
    // ofstream out;
    // out.open("out.txt");
    int numCases;
    int currentCase = 0;
    cin >> numCases;
    while (currentCase < numCases) {
        int numTurtles;
        cin >> numTurtles;
        string whiteSpace;
        getline(cin, whiteSpace);
        vector<string> originalOrder;
        vector<string> finalOrder;
        for (int i = 0; i < numTurtles; i++) {
            string turtle;
            getline(cin, turtle);
            originalOrder.push_back(turtle);
        }
        for (int i = 0; i < numTurtles; i++) {
            string turtle;
            getline(cin, turtle);
            finalOrder.push_back(turtle);
        }
        // cout << "INTITIAL:\n\n";
        // for (int i = 0; i < numTurtles; i++) {
        //     cout << originalOrder[i] << endl;
        // }
        // cout << "\nFINAL:\n\n";
        // for (int i = 0; i < numTurtles; i++) {
        //     cout << finalOrder[i] << endl;
        // }
        // Process
        int oIter = originalOrder.size() - 1;
        int fIter = finalOrder.size() - 1;
        vector<string> movedTurtles;
        while (oIter >= 0 && fIter >= 0) {
            if (originalOrder[oIter] != finalOrder[fIter]) {
                string turtle = originalOrder[oIter];
                // cout << "Moving " << turtle << "!\n";
                movedTurtles.push_back(turtle);
                originalOrder.erase(originalOrder.begin() + oIter);
                oIter--;
                // Take out of stack
                // Put in a toSort group
                // figure out the order from there
                
                // cout << "AFTER Removal\n\n";
                // for (vector<string>::iterator i = originalOrder.begin(); i != originalOrder.end(); i++) {
                //     cout << *i << endl;
                // }
            } else {
                oIter--;
                fIter--;
            }
            
        }

        for (int i = finalOrder.size() - originalOrder.size(); i >= 0; i--) {
            for (int j = 0; j < movedTurtles.size(); j++) {
                if (finalOrder[i] == movedTurtles[j]) {
                    cout << movedTurtles[j] << endl;
                    // out << movedTurtles[j];
                    // out << "\n";
                    movedTurtles.erase(movedTurtles.begin() + j);
                }
            }
        }

        currentCase++;
        // out << "\n";
        cout << endl;
    }
    // out.close();

    return 0;
}