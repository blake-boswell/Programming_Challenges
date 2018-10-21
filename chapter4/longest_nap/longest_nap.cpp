#include <iostream>
#include <vector>

using namespace std;

// UVa 10191

int offset(int in) {
    return in - (10 * 60);
}

int reverseOffset(int in) {
    return in + (10 * 60);
}

int main(int argc, char** argv) {
    // num appointments that day, s
    // Next s lines are appointements (startTime endTime appointment)
    // Response must be in the interval 10:00 <= x <= 18:00

    //Out
    // If there is multiple equal, print the earliest

    int numAppointments;
    int day = 1;
    while (cin >> numAppointments) {
        int startHour;
        int startMinutes;
        int endHour;
        int endMinutes;
        int startTime[numAppointments];
        int endTime[numAppointments];
        string description;
        char colon;
        for (int i = 0; i < numAppointments; i++) {
            cin >> startHour;
            cin >> colon;
            cin >> startMinutes;
            cin >> endHour;
            cin >> colon;
            cin >> endMinutes;
            startTime[i] = startHour * 60 + startMinutes;
            endTime[i] = endHour * 60 + endMinutes;
            getline(cin, description);
        }

        bool busyMinute[8 * 60 + 1];
        for (int i = 0; i < 8 * 60 + 1; i++) {
            busyMinute[i] = false;
        }
        for (int i = 0; i < numAppointments; i++) {
            for (int j = offset(startTime[i]); j < offset(endTime[i]); j++) {
                busyMinute[j] = true;
            }
        }

        vector<int> newStartTime;
        vector<int> newEndTime;

        if (busyMinute[0]) {
            newStartTime.push_back(reverseOffset(0));
        }
        for (int i = 1; i < 8 * 60 + 1; i++) {
            if (busyMinute[i - 1] == false && busyMinute[i] == true) {
                newStartTime.push_back(reverseOffset(i));
            } else if (busyMinute[i - 1] == true && busyMinute[i] == false) {
                newEndTime.push_back(reverseOffset(i));
            }
        }

        int startInterval = 10 * 60;
        int endInterval = 18 * 60;
        int maxTime = -1;
        int napStartTime = -1;
        int size = newStartTime.size();
        for (int i = 0; i <= size; i++) {
            int napTime = -1;
            int start;
            if (i == 0) {
                napTime = newStartTime[i] - startInterval;
                start = startInterval;
            } else if (i < size){
                napTime = newStartTime[i] - newEndTime[i - 1];
                start = newEndTime[i - 1];
            } else {
                napTime = endInterval - newEndTime[size - 1];
                start = newEndTime[size - 1];
            }
            if (maxTime < napTime) {
                maxTime = napTime;
                napStartTime = start;
            }
        }
        int napHours = maxTime / 60;
        int napMins = maxTime % 60;
        string napDuration = "";
        if (napHours > 0) {
            napDuration = to_string(napHours) + " hours and " + to_string(napMins) + " minutes.";
        } else {
            napDuration = to_string(napMins) + " minutes.";
        }

        int napStartMins = napStartTime % 60;
        string napStartMinutes = to_string(napStartMins);
        if (napStartMins < 10) {
            napStartMinutes = "0" + to_string(napStartMins);
        }

        cout << "Day #" << day << ": the longest nap starts at " << napStartTime / 60 << ":" << napStartMinutes << " and will last for " << napDuration << endl;
        day++;
    }

    return 0;
}