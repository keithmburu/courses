#include <string>
class HTMLDocument {
 public:
  HTMLDocument(std::string original_text);
  std::string get_title();
  std::string get_filtered_text();
  std::string get_hyperlink(); /* TODO: Returns the first hyperlink in a web page */

  
 private:
  std::string original_text;
  std::string lowered_text;
  std::string to_lower_case(std::string text);
};
