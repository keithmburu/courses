#include <string>
#include <algorithm>
#include <cctype>
#include <string>
#include <iostream>
#include "extractor.h"
int main(int argc, char** argv) {
  HTMLDocument * doc = new HTMLDocument("adkflasdf<title>This is the title</title>dilasfkdlasfd");
  doc->get_title();
  return 0;
}

HTMLDocument::HTMLDocument(std::string original_text) {
  this->original_text = original_text;
  this->lowered_text = this->to_lower_case(original_text);
}

std::string HTMLDocument::to_lower_case(std::string text) {
    std::transform(text.begin(),
		   text.end(),
		   text.begin(), 
                   [](unsigned char c){ return std::tolower(c); }
		   );
    return text;
}


std::string HTMLDocument::get_title() const {
  std::string title = "";
  std::size_t title_start = original_text.find("<title>");
  std::size_t title_end = original_text.find("</title>");
  std::cout << title_end << std::endl;
  if(title_start == std::string::npos) {
    std::cout << "<title> tag not found." << std::endl;
  }

  if(title_end == std::string::npos) {
    std::cout << "</title> tag not found." << std::endl;
  }

  title_start += 7;
  int title_length = this->original_text.length() - title_end;
  title = original_text.substr(title_start, title_end - title_start);
  std::cout << title << std::endl;
  
  return title;
}
