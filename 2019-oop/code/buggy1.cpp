#include <iostream>
using namespace std;
int main(int argc, char** argv) {
  int* primes = new int[6];
  primes[0] = 1;
  primes[1] = 2;
  primes[3] = 3;
  primes[4] = 5;
  primes[5] = 7;

  for(int i = 0; i < 6; i++) {
    cout << *(primes + i) << endl;
  }
  
}



