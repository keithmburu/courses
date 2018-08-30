Deep Computer Vision and Language, Fall 2018

Alvin Grissom II, Ursinus College


# Assignment 0: Running a Keras Program

The purpose of this assignment is for you to set up your environment and to ensure that you have a basic grasp of matrices and conditional probability.  It will give students a chance to practice using Python, the primary programming language for this course.  All code must be written in Python 3.  

The starter code with the requiiste functions is provied as part of this assignment.

## Part 1: Environment Setup

This course assumes that you have access to macOS, Linux, or a Linux-like Windows environment.  The following section details the setup process for each.

First, request a free [GitHub Student](https://education.github.com/pack) account, if you do not already have one.

### macOS

The macOS operating system includes a Unix subsystem accessible from the Terminal.  The vast majority of the work for this course will take place in this environment.

1. Install the Homebrew backage manager by following the instructions at http://brew.sh.  We will use this to install other programs.
2. Use Homebrew to install Python 3 and wget.

        brew install python
        brew install wget

3. Installing Python 3 should also install pip.  Prooceed to Part 2.


### Windows 10

1.  Download and install the terminal, [Cmder](http://cmder.net).  Add an icon for it in an easily accessible place.
2.  Download and install [Chocolatey](https://chocolatey.org)
3.  Use Cmder to install Python 3 and pip.

        choco install python3
        choco install pip
        
4.  Install Emacs, Git, OpenSSH, grep, and wget.
    choco install unixutils.
    
        choco install emacs
        choco install git
        choco install openssh
        choco install grepwin
        choco linstall wget
        
5.  Install CUDA and cuDNN.    

6.  Optional: Install the Windows Subsystem for Linux, but this does not support GPU acceleration. 

### Ubuntu Linux

1.  Install emacs.  On Ubuntu, this is:

        sudo apt-get install emacs


## Part 2: Install Keras
First, install numpy, seaborn, and scikit-learn with pip.

    pip3 install numpy
    pip3 install scikit-learn
    pip3 install seaborn

On some systems, you may have to use `pip` instead of `pip3`.
### macOS

Install TensorFlow and Keras.
    
    pip3 install tensorflow
    pip3 install keras
    
### Linux
If your computer does **not** have an NVIDIA GPU, use:

       pip3 install tensorflow
       pip3 install keras
  
   Otherwise, if you have an NVIDIA GPU and want to use GPU acceleration (recommended):
   1.  Ensure that you are using the [proprietary NVIDIA drivers](https://www.nvidia.com/object/unix.html) for your system.
   2. [Install CUDA and cuDNN.](https://yangcha.github.io/CUDA90/)
   3.  Install GPU-accelerated TensorFlow and Keras.

       pip3 install tensorflow-gpu     
       pip3 install keras


### Windows 10
If you have an NVIDIA GPU and would like to use GPU acceleration (recommended):

1.  Install [CUDA 9](https://developer.nvidia.com/cuda-downloads) and [cuDNN 7](https://docs.nvidia.com/deeplearning/sdk/cudnn-install/index.html#installwindows) from NVIDIA.

2.  Use pip to install GPU-accelerated TensorFlow and Keras.

        pip install tensorflow-gpu
        pip install keras

##Part 3: Make sure Keras loads.


1. Run `python3`.
2. Type `import keras`.
   
If there are no problems, you should get a message about using the TensorFlow backend.  You can then type Ctrl+D to exit.

If you were successful, the program should finish 20 epochs in a couple of minutes and report accuracy aroun 98%.  Use output redirection to send the output of the program to a file named mnist_test.txt

## Part 4: Getting used to Python

Write a Python program to add two matrices of any size and print out the resulting matrix.




