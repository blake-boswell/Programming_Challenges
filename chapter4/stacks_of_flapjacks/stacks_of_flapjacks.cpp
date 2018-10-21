#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

// Mock of Java split found from:
// http://ysonggit.github.io/coding/2014/12/16/split-a-string-using-c.html
void split(const string str, char delimeter, vector<string>& splitStr) {
    int position = str.find(delimeter);
    int i = 0;
    // Look until position of delimeter is not found
    while (position != string::npos) {
        splitStr.push_back(str.substr(i, position - i));
        i = ++position;
        position = str.find(delimeter, position);
        if (position == string::npos) {
            splitStr.push_back(str.substr(i, str.length()));
        }
    }
}

// [ a|b |c |d |e ]
void flip(int* stack, int pos) {
    for (int i = pos; i > pos / 2; i--) {
        int temp = stack[i];
        stack[i] = stack[pos - i];
        stack[pos - i] = temp;
    }
}

int main(int argc, char** argv) {
    // Need a reverse(array, start, end)
    // flip(index) -> reverse(array, index - 1, array.end)
    // [top ... bottom]
    string inStack;
    while (getline(cin, inStack)) {
        // cout << inStack.length() << endl;
        if (inStack.length() != 0) {
            vector<string> pancakeStrings;
            split(inStack, ' ', pancakeStrings);
            if (inStack.length() == 2) {        // Hacky way of finding if there is only one element and a newline char
                pancakeStrings.push_back(inStack);
            }
            int numPancakes = pancakeStrings.size();
            int stack[numPancakes];
            int sortedStack[numPancakes];
            for (int i = 0; i < numPancakes; i++) {
                stack[i] = stoi(pancakeStrings[i]);
                sortedStack[i] = stack[i];
            }

            for (int i = 0; i < numPancakes; i++) {
                cout << stack[i];
                if (i != numPancakes - 1) {
                    cout << " ";
                }
            }
            cout << "\n";

            // sort stack
            // 1. Make array of sorted values to compare
            // 2. Find largest in stack
            // 3. Flip it to the top of the stack
            // 4. Flip entire stack, so that the largest is on the bottom
            // Loop:
            //      5. Look for the next (greatest) int in the array that isn't in the same position as the sorted array
            //      6. Flip that int to the top of the stack
            //      7. Flip the stack from the spot that the top value belongs in 
            //           (flip from index of the value at the top of stack in the sorted array) 
            sort(sortedStack, sortedStack + numPancakes);
            int sortedIndex = numPancakes - 1;
            int numSorted = 0;
            bool sorted = false;
            while (!sorted) {
                for (int i = sortedIndex; i >= 0; i--) {
                    int desiredPancake = sortedStack[sortedIndex];
                    if (stack[i] == desiredPancake) {
                        numSorted++;
                        // Check if the pancake is already in place
                        if (i != sortedIndex) {
                            // flip to top flip(i)
                            if (i != 0) {
                                reverse(stack, stack + i + 1);
                                cout << numPancakes - i << " ";
                            }
                            
                            // flip to posiition flip (numSorted)
                            reverse(stack, stack + sortedIndex + 1);
                            cout << numPancakes - sortedIndex << " ";
                            // if (numSorted < numPancakes) {
                            //     cout << " ";
                            // }
                        }
                        sortedIndex--;
                    }
                    // cout << "\n\t" << sortedIndex << "\n\t";
                    // for (int j = 0; j < numPancakes; j++) {
                        // cout << stack[j] << " ";
                    // }
                    // cout << endl;
                }
                sorted = true;
                for (int i = 1; i < numPancakes; i++) {
                    if (stack[i - 1] > stack[i]) {
                        sorted = false;
                    }
                }
            }
            cout << "0" << endl;
        }
        
        

    }

    return 0;
}