Machine Learning, Spring 2022

Haverford College

Alvin Grissom II

# Lab 0: Local Setup and Remote Working

This lab will not be graded.

This purpose of this optional first lab is for students to set up their home environments for remote work.  Future assignments will assume that students are working in a command line (terminal) environment. 

While all assignments in this course can in principle be completed on one's home computer if it uses a Unix-like system (Linux, macOS, Windows Subsystem for Linux), I will assume that students are using lab computers for assignments, and I will not serve as technical support for students' idiosyncratic personal computer problems.  These instructions are provided as a guide to help students who want to use their personal computers.

## Local Setup

1. **Windows** users should first install the [Windows Subsystem for Linux](https://docs.microsoft.com/en-us/windows/wsl/install)  Once this is complete, they should install the [Windows Terminal](https://www.microsoft.com/en-us/p/windows-terminal/9n0dx20hk701?activetab=pivot:overviewtab) from the Microsoft Store. 


Once both Ubuntu and Windows Terminal have been installed, open Windows Terminal.  There should be an option to open an Ubuntu tab (which can be set as the default).  Open it.

Type the following and press [ENTER] to update the packages to the latest version.

```bash
sudo apt update
```

Do not close this window until it finishes.

* **macOS** users users can use the built-in Terminal application or download [iTerm2](https://iterm2.com/).  From either, they can install Homebrew by following the instructions here: https://brew.sh/

We will use the terminal for nearly all of the work in this course.

2. To connect to the lab machines remotely, you will need to set up the Haverford VPN on your home computer(s) and log in.  Follow the instructions here: https://iitskb.sites.haverford.edu/knowledge-base/installing-the-vpn-client/

I recommend only using the VPN when you are working remotely and disconnecting afterwards.  If you're using an operating system not listed here, let me know.  **Bryn Mawr students will have to apply for VPN access.**

## Remote Connection

Once you are connected via the VPN, you should be able to log in to a remote machine with the `ssh` program.  From the terminal, type the following, replacing `username` with your actual user name and `xxxx` with the name of the computer you're logging in to.

```bash
ssh username@xxxx.cs.haverford.edu
```

For example, I might be:

```bash
agrissom@mobius.cs.haverford.edu
```

If you on Haverford's campus or are connected to the VPN, you should be prompted for your password.  When you type it, the characters won't show on the screen, but they are being read.  Press [ENTER] when you are finished.  If you're successful, you should see a welcome screen.  You can type `exit` to disconnect.

On your local computer, I recommend that Window susers update Ubuntu packages to the latest versions with:

```bash
sudo apt upgrade
```

This may take some time.

### Version Control

If you do not already have one, request a [GitHub Student Developer Pack](https://education.github.com/pack).  If you do not wish to sign up for this, let me know, and we will find an alternative arrangement.

## More Local Setup

If you'd like to create an environment more amenable to working on your personal computer, you can do the following on your local machine, but it is not required.

Unless you intend to do all of your written work on a service such as Overleaf, I highly recommend installing LaTeX and a LaTeX-enabled Markdown editor, such  [Typora](http://typora.io) (paid) or [Mark Text](https://marktext.app/) (free).

### macOS

The macOS operating system includes a Unix subsystem accessible from the Terminal.  The vast majority of the work for this course will take  place in this environment.

1. Use Homebrew to install Python 3 and wget.
   
   ```
    brew install python3
    brew install wget
    brew install emacs
    brew install grep
    brew install tmux
    brew install bat
    brew install saulpw/vd/visidata
    brew install vowpal-wabbit
    brew install graphviz
    brew install sqlite
    brew install csvkit
    brew install gron
    brew install imagemagick
    brew install catimg
   ```

2. Installing Python 3 should also install pip.  Proceed to Part 2.

### Ubuntu Linux (including WSL)

1. Install utilities.  On Ubuntu, this is:
   
   ```bash
   sudo apt install emacs
   sudo apt install git
   sudo apt install python3-pip
   sudo apt install tmux
   sudo apt install visidata
   sudo apt install bat
   sudo apt install vowpal-wabbit
   sudo apt install csvkit
   sudo apt install sqlite
   sudo apt install graphviz
   sudo apt  install gitsome
   sudo apt install imagemagick
   sudo apt install catimg
   ```

You can also install LaTeX locally by following the instructions here: [Get LaTeX - Mac OS, Windows, Linux](https://www.latex-project.org/get/).  Alternatively, you can use an online LaTeX editor, such as Overleaf.



## Part 2: Install TensorFlow and other Programs

First, install numpy, seaborn, and scikit-learn with pip.

```
pip3 install numpy 
pip3 install scipy
pip3 install scikit-learn
pip3 install jupyterlab
pip3 install notebook
pip3 install nbterm
pip3 install seaborn 
pip3 install matplotlib
pip3 install "plotext[image]"
pip3 install termgraph
pip3 install dsutils
```

On some systems, you may have to use `pip` instead of `pip3`.

### macOS

Follow the directions here: [Tensorflow Plugin - Metal - Apple Developer](https://developer.apple.com/metal/tensorflow-plugin/)

### Linux (Debian/Ubuntu-based)

If your computer does **not** have an NVIDIA GPU or you want to keep things simple, use:

```bash
pip3 install tensorflow
```




Otherwise, if you have an NVIDIA GPU and want to use GPU acceleration (potential time sink):

1. Ensure that you are using the [proprietary NVIDIA drivers](https://www.nvidia.com/object/unix.html) for your system.  If you're using the Pop!_OS Linux distribution, this is easy.

2. Install CUDA and CuDNN.

3. Install GPU-accelerated TensorFlow and Keras
   
   Follow the instructions [here](https://www.tensorflow.org/install/gpu).

### Windows 10

If you have an NVIDIA GPU and would like to use GPU acceleration (potential time sink)

1. Install the appropriate CUDA and CuDNN drivers.

2. Use pip to install GPU-accelerated TensorFlow.
   
   ```
   pip install tensorflow-gpu
   pip install keras
   ```
   
   Otherwise, you can just run
   
   ```
   pip install keras
   ```

## Part 3: Make sure TensorFlow loads.

1. Run `python3`.
2. Type `import tensorflow`.

If there are no problems, you shouldn't see any output.  You can then type Ctrl+D to exit.


