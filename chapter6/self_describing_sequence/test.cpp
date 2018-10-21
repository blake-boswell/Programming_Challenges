#include <iostream>
#include <cstdio>
#define N 1000000
using namespace std;
 
int save [N];
int ind;
 
void pre_generate ()
{
    save [1] = 1;
    save [2] = 2;
    save [3] = 4;
    save [4] = 6;
    save [5] = 9;
    save [6] = 12;
    save [7] = 16;
 
    ind = 7;
    int indicator = 4;
    int cnt;
 
    while ( save [ind - 1] <= 2000001000 ) {
        cnt = save [indicator + 1] - save [indicator];
        while ( cnt && save [ind - 1] <= 2000001000 ) {
            save [ind] = save [ind - 1] + indicator;
            ind++;
            cnt--;
        }
        indicator++;
    }
 
    //printf ("%d\n", ind);
}
 
int main() {
    int n;
 
    pre_generate ();
 
    while ( scanf ("%d", &n) && n )
    {
        for (int i = 1; i <= 20; i++) {
            printf("%d, ", save[i]);
        }
        printf("\n\n");
        for ( int i = 1; i < ind; i++ ) {
            if ( n < save [i] ) { printf ("%d\n", i - 1); break; }
        }
    }
 
    return 0;
}