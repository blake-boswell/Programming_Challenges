// UVa 10249

#include <iostream>
#include <climits>

using namespace std;

struct Table {
    int id;
    int capacity;
    Table() {
        this->id = -1;
        this->capacity = -1;
    }
    Table(int capacity) {
        this->id = -1;
        this->capacity = capacity;
    }
};

int main(int argc, char** argv) {
    int numTeams, numTables;
    bool run = true;
    while (run) {
        cin >> numTeams;
        cin >> numTables;
        if (numTeams == 0 && numTables == 0) {
            run = false;
            break;
        }
        int numMembers[numTeams];
        Table tables[numTables];
        int maxNum = INT_MIN;
        for (int i = 0; i < numTeams; i++) {
            cin >> numMembers[i];
            if (numMembers[i] > maxNum) {
                maxNum = numMembers[i];
            }
        }
        // cout << maxNum << endl;
        int teamToTable[numTeams][numTables];
        bool valid = true;
        if (maxNum > numTables) {
            valid = false;
            // cout << "Cause maths\n";
        } else {
            for (int i = 0; i < numTables; i++) {
                int capacity;
                cin >> capacity;
                tables[i].capacity = capacity;
                tables[i].id = i + 1;
                // cout << "Table " << i << " cap set to " << tables[i].capacity << endl;
            }

            for (int i = 0; i < numTables; i++) {
                cout << "[" << tables[i].id << "]: " << tables[i].capacity << endl;
            }
            cout << "\n\n";

            // Insertion sort
            for (int i = 1; i < numTables; i++) {
                Table key = tables[i];
                for (int j = i - 1; j >= 0; j--) {
                    if (tables[i].capacity > tables[j].capacity) {
                        // swap
                        Table temp = tables[i];
                        tables[i] = tables[j];
                        tables[j] = temp;
                        i--;
                    }
                }
            }

            for (int i = 0; i < numTables; i++) {
                cout << "[" << tables[i].id << "]: " << tables[i].capacity << endl;
            }

            // for each member of each team, assign them a seat at a diff table
            for (int i = 0; i < numTeams && valid; i++) {
                // cout << "Team: " << i+1 << " of " << numTeams << endl;
                // cout << "Member count: " << numMembers[i] << endl;
                for (int j = 0; j < numMembers[i]; j++) {
                    cout << "Table " << tables[j].id << ". Cap: " << tables[j].capacity << endl;
                    // Shouldn't cause err b/c maxNum <= numTables
                    teamToTable[i][j] = tables[j].id;
                    if (--tables[j].capacity < 0) {
                        valid = false;
                    }
                }
                cout << endl;
            }

        }

        if (valid) {
            cout << "1" << endl;
            for (int i = 0; i < numTeams; i++) {
                for (int j = 0; j < numMembers[i]; j++) {
                    cout << teamToTable[i][j] << " ";
                }
                cout << endl;
            }
        } else {
            cout << "0" << endl;
        }

    }
}