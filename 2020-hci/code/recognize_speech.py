"""
***Linux Only***
Note that this will not work in WSL, which does not support audio.
sudo apt update
sudo apt-get install python-pyaudio python3-pyaudio
See https://pypi.org/project/SpeechRecognition/ 
*************
***Mac Only***
sudo xcode-select --install
brew install portaudio

https://towardsdatascience.com/building-a-simple-voice-assistant-for-your-mac-in-python-62247543b626

***All Platforms***
pip3 install --upgrade pip
pip3 install pyaudio
pip3 install pyttsx3
pip3 install speechrecognition
pip3 install pocketsphinx (optional: for offline voice recognition)

"""
import speech_recognition as sr
import pyttsx3
import sys


tts = pyttsx3.init()


def speak(tts, text):
    tts.say(text)
    tts.runAndWait()

def main():
    # get audio from the microphone                                                                       
    listener = sr.Recognizer()                                                                                   
    with sr.Microphone() as source:
        listener.adjust_for_ambient_noise(source)
        while True:
            print("Listening.")
            speak(tts, "listening")
            user_input = None
            sys.stdout.write(">")
            #record audio
            audio = listener.listen(source)
            try:
                #convert audio to text
                #user_input = listener.recognize_sphinx(audio) #requires PocketSphinx installation
                user_input = listener.recognize_google(audio)


                print(user_input)
                speak(tts, user_input)
            except sr.UnknownValueError:
                print("Could not understand audio")
            except sr.RequestError as e:
                print("Could not request results; {0}".format(e))
            sys.stdout.write("\n")


if __name__ == "__main__":
    main()
