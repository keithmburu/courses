#include <iostream>
using namespace std;
int main(int argc, char** argv) {
  int n, fac;	       
  n = 10;
  for(int i = 1; i <=n; ++i)
    {
      fac *= i;
    }
  cout << fac << endl;
  return 0;
}
