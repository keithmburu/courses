#include <iostream>
#include <string>

int main() {
  std::string line;
  while(std::cin >> line) {
    std::cout << "Read in: " << line << std::endl;
  }
}
