#include <iostream>
#include "hello.h"
using namespace std;


int main(int argc, char** argv) {
  int num;
  string my_string = "hello";
  cout << my_string << endl;
  /*  while(cin >> num) {
    cout << "You typed " << num << endl;
    }*/

  cout << add(1, 2) << endl;
}

int add(int x, int y) {
  return x + y;
}
