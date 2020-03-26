#include <iostream>
#include "arrays.h"
using namespace std;
int main(int argc, char** argv) {
  demo();
  return 0;
}

void demo() {
  int x = 5;
  int* x_ptr = &x; //& = "address of" x
  cout << "x: " << x << endl;
  cout << "x_ptr: " << x_ptr << endl;
  cout << "&x: " << &x << endl;
}
