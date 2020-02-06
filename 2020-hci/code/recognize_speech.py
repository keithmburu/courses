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
