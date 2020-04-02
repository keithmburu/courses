
using std::cout;
class IntVector {
private:
  int* array;
  int num_elements;
  int array_size;
  int expansion_factor;
  void expandArray();
public:
  void add(int val);
  void remove(int index);
  int get(int index) const;
  void removeLast();
  void set(int index, int val);
  std::string toString() const;
  IntVector(int initial_size);
  ~IntVector();
  

};
