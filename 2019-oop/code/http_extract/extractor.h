#include <string>
class HTMLDocument {
 public:
  HTMLDocument(std::string original_text);
  std::string get_title() const;
  std::string get_filtered_text() const;
  std::string get_hyperlink() const; /* TODO: Returns the first hyperlink in a web page */

  
 private:
  std::string original_text;
  std::string lowered_text;
  std::string to_lower_case(std::string text);
};
