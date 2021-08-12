Computational Linguistics, Fall 2020

Haverford College

# Lab 0: Local Setup and Remote Working

This lab will not be graded.

This purpose of this first lab is for students to set up their environments for remote work.  We will be working remotely, and future assignments will assume that students are working in a command line (terminal) environment. 

While all assignments in this course can in principle be completed on one's home computer if it uses a Unix-like system (Linux, macOS, Windows Subsystem for Linux), I will assume that students are using lab computers for assignments, and I will not serve as technical support for students' idiosyncratic personal computer problems.

## Local Setup

1. We have multiple lab machines with GPUs.  To distribute the load among multiple machines, we will maintain a list of two "owners" and two "backup users."  If you "own" a machine, it will be the first machine that you go to for your assignments. The "backup" users will use this machine if the other one is busy or unavailable.

   While logged into Google with your Haverford credentials, follow [this link](https://docs.google.com/spreadsheets/d/1Kb_gM8po_D-AYJQjTUHujpyAyqKQtXQnA702pjLIWAc/edit?usp=sharing) and type your **user ID** in each of the four options for four different machines: i.e., choose one machine as the primary user, secondary user, backup 1, and backup 2.

   Additionally, if you are doing something substantial on a machine, I encourage you to type your name under "Currently Using" and delete it when you're finished.

   

2. * **Windows** users should first install the [Windows Subsystem for Linux (Ubuntu)](https://www.microsoft.com/en-us/p/ubuntu/9nblggh4msv6?activetab=pivot:overviewtab) -- don't forget the last step.  Once this is complete, they should install the [Windows Terminal](https://www.microsoft.com/en-us/p/windows-terminal/9n0dx20hk701?activetab=pivot:overviewtab) from the Microsoft Store. 

     Windows users should follow the instructions here: https://www.windowscentral.com/install-windows-subsystem-linux-windows-10
     Once both Ubuntu and Windows Terminal have been installed, open Windows Terminal.  There should be an option to open an Ubuntu tab (which can be set as the default).  Open it.

     Type the following and press [ENTER] to update the packages to the latest version.

     ```bash
     sudo apt update
     ```

     Do not close this window until it finishes.

   * **macOS** users users can use the built-in Terminal app or download iTerm.  From either, they can install Homebrew by following the instructions here: http://brew.sh.

   We will use the terminal for nearly all of the work in this course.

   

3. To connect to the lab machines, you will need to set up the Haverford VPN on your home computer(s) and log in.  Follow the instructions here: https://iitskb.sites.haverford.edu/knowledge-base/installing-the-vpn-client/

   I recommend only using the VPN when you are working remotely and disconnecting afterwards.  If you're using an operating system not listed here, let me know.  **Bryn Mawr students will have to apply for VPN access.**

   

## Remote Connection

Once you are connected via the VPN, you should be able to log in to a remote machine with the `ssh` program.  From the terminal, type the following, replacing your user name and server name with the appropriate ones.

```bash
ssh username@xxxx.haverford.edu
```

For example, I might be:

```bash
alvin@mobius.haverford.edu
```

If you are connected to the VPN, you should be prompted for your password.  When you type it, the characters won't show on the screen, but they are being read.  Press [ENTER] when you are finished.  If you're successful, you should see a welcome screen.  You can type `exit` to disconnect.

On your local computer, I recommend that Window susers update Ubuntu packages to the latest versions with:

```bash
sudo apt upgrade
```

This may take some time.

### Version Control

If you do not already have one, request a [GitHub Student Developer Pack](https://education.github.com/pack).  If you do not wish to sign up for this, let me know, and we will find an alternative arrangement.

## More Local Setup

If you'd like to create an environment more amenable to working on your personal computer, you can do the following on your local machine, but it is not required.

Unless you intend to do all of your written work on a service such as Overleaf, I highly recommend installing LaTeX and downloading [Typora](http://typora.io) or [Mark Text](https://marktext.app/).

### macOS 

The macOS operating system includes a Unix subsystem accessible from  the Terminal.  The vast majority of the work for this course will take  place in this environment.

1. Use Homebrew to install Python 3 and wget.

   ```
    brew install python3
    brew install wget
    brew install emacs
    brew install grep
    brew install tmux
   ```

2. Installing Python 3 should also install pip.  Proceed to Part 2.



### Ubuntu Linux (including WSL)

1. Install utilities.  On Ubuntu, this is:

   ```bash
   sudo apt install emacs
   sudo apt install git
   sudo apt install python3-pip
   sudo apt install tmux
   ```

## 

## Part 2: Install Keras

First, install numpy, seaborn, and scikit-learn with pip.

```
pip3 install numpy
pip3 install scikit-learn
pip3 install seaborn
```

On some systems, you may have to use `pip` instead of `pip3`.

### macOS

If you want to use an ATI/AMD GPU for acceleration, you can try [PlaidML](https://plaidml.github.io/plaidml/docs/install.html):

Otherwise, install TensorFlow and Keras.

```
pip3 install tensorflow
pip3 install keras
```

### Linux

If your computer does **not** have an NVIDIA GPU or you want to keep things simple, use:

```
   pip3 install tensorflow
   pip3 install keras
```

Otherwise, if you have an NVIDIA GPU and want to use GPU acceleration (potential time sink):

1. Ensure that you are using the [proprietary NVIDIA drivers](https://www.nvidia.com/object/unix.html) for your system.  If you're using Pop!_OS, this is easy.

2. Install CUDA and CuDNN.

3. Install GPU-accelerated TensorFlow and Keras.

   ```bash
   pip3 install tensorflow-gpu
   pip3 install keras
   ```

### Windows 10

If you have an NVIDIA GPU and would like to use GPU acceleration (potential time sink)

1. Install the appropriate CUDA and CuDNN drivers.

2. Use pip to install GPU-accelerated TensorFlow and Keras.

   ```
   pip install tensorflow-gpu
   pip install keras
   ```

   Otherwise, you can just run

   ```
   pip install keras
   ```

   

## Part 3: Make sure Keras loads.

1. Run `python3`.
2. Type `import keras`.

If there are no problems, you should get a message about using the TensorFlow backend.  You can then type Ctrl+D to exit.

## 

## 