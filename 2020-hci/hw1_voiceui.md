Human-Computer Interaction

Spring 2020



# Homework 1: Voice User Interface



You will use automatic speech recognition (ASR) and text-to-speech (TTS) to create a program completely navigable by voice. Your program should not rely on text or mouse input or output to function. It should adhere to the design principles we have discussed in class. You have been provided with example Python 3 code using the pyttx and SpeechRecognizer APIs.  The SpeechRecognizer API can use a Google-powered ASR engine through the Internet or an offline engine called PocketSphinx.  For the latter, PocketSphinx must be installed separately.  The Google engine does not require any extra setup, though you should be mindful of its limitations when testing your project. (See: https://cloud.google.com/speech-to-text/quotas)

You may use other languages/APIs if you like, but these were chosen because they are simple to use and reasonably cross-platform. If you prefer, you may convert an existing open source program into one that uses a voice user interface, such as a [port](https://www.smallsurething.com/implementing-the-famous-eliza-chatbot-in-python/) of [ELIZA](https://en.wikipedia.org/wiki/ELIZA). Your project must be approved.

Write a short (~1 pg) report, describing your project and both the challenges and the rationale for your HCI decisions. How did you design signifiers and affordances? What kind of conceptual model are you conveying? What kind of feedback do you need? How did you modify your program to take into account feedback? Was timing an issue? Is security difficult with a voice UI?

You will demonstrate your projects in class after the due date.



* Design document/report(25 points)

  * Describes design decisions (e.g., with a flowchart) (5 ts)

  * Describe rationale for design decisions, limitations, advantages (5 pts)

  * Contrasts with alternative UIs (e.g., using text-based or graphical input). (5 pts)

  * Report describe possible improvements (5 pts)

  * Report is formatted well, reasonably grammatical, organized into appropriate sections (5pts)

    

* Programs runs correctly without crashing. (25 points)

* Affordances conveyed. It must be clear how to interact with the program. (10pts)

* User can navigate and exit the program without text I/O. (10pts)

* Program provides feedback. (10pts)

* Program is enjoyable to use (doesn’t become unusable if something goes wrong, provides appropriate timing). Program handle misunderstood inputs and errors elegantly. (10pts)

* Provides personalization and security (e.g., remembers preferred name, login password) (10pts)

## Project Suggestions

* Zork-like game.  Instead of typing commands and reading them, the user speaks command and the computer replies.
* Computer file manager/navigator: for an example, see: https://towardsdatascience.com/building-a-simple-voice-assistant-for-your-mac-in-python-62247543b626
* Calendar (Add, remove, change events)
* Address Book
* Others (must be approved)
* Text editor/dictation app, with the ability to edit, delete, etc.
* Write computer programs



## Other Resources

There are several guides online that may help with coding, including:

https://realpython.com/python-speech-recognition/

https://towardsdatascience.com/building-a-simple-voice-assistant-for-your-mac-in-python-62247543b626

Windows Speech APIs:

 https://docs.microsoft.com/en-us/windows/apps/speech

https://support.office.com/en-us/article/how-to-download-text-to-speech-languages-for-windows-10-d5a6b612-b3ae-423f-afa5-4f6caf1ec5d3

https://docs.microsoft.com/en-us/dotnet/api/system.speech.recognition.speechrecognitionengine?view=netframework-4.8





