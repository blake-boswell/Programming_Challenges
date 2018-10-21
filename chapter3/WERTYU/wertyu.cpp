#include <iostream>
#include <map>

using namespace std;
// UVa 10082

int main(int argc, char** argv) {
    // Map of inputs to new input
    map<char, char> keyMap;
    // top row
    keyMap['W'] = 'Q';
    keyMap['E'] = 'W';
    keyMap['R'] = 'E';
    keyMap['T'] = 'R';
    keyMap['Y'] = 'T';
    keyMap['U'] = 'Y';
    keyMap['I'] = 'U';
    keyMap['O'] = 'I';
    keyMap['P'] = 'O';
    keyMap['['] = 'P';
    keyMap[']'] = '[';
    keyMap['\\'] = ']';
    // mid row
    keyMap['S'] = 'A';
    keyMap['D'] = 'S';
    keyMap['F'] = 'D';
    keyMap['G'] = 'F';
    keyMap['H'] = 'G';
    keyMap['J'] = 'H';
    keyMap['K'] = 'J';
    keyMap['L'] = 'K';
    keyMap[';'] = 'L';
    keyMap['\''] = ';';
    // bottom row
    keyMap['X'] = 'Z';
    keyMap['C'] = 'X';
    keyMap['V'] = 'C';
    keyMap['B'] = 'V';
    keyMap['N'] = 'B';
    keyMap['M'] = 'N';
    keyMap[','] = 'M';
    keyMap['.'] = ',';
    keyMap['/'] = '.';
    // number row
    keyMap['1'] = '`';
    keyMap['2'] = '1';
    keyMap['3'] = '2';
    keyMap['4'] = '3';
    keyMap['5'] = '4';
    keyMap['6'] = '5';
    keyMap['7'] = '6';
    keyMap['8'] = '7';
    keyMap['9'] = '8';
    keyMap['0'] = '9';
    keyMap['-'] = '0';
    keyMap['='] = '-';
    // Read input
    string input;
    while (getline(cin, input)) {
        for (int i = 0; i < input.size(); i++) {
            
            map<char, char>::iterator it;
            it = keyMap.find(input[i]);
            if (it != keyMap.end()) {
                // It exists
                input[i] = it->second;
            }
            cout << input[i];
        }
        cout << '\n';
    }

    
    // Shift all letters over by 1

    return 0;
}