
# Initial Setup

The purpose of this document is to assist you in setting up your environment and to ensure that you have a basic grasp of the command line.


## Part 1: Environment Setup

This course assumes that you have access to macOS, Linux, or a Linux-like Windows environment.  The following section details the setup process for each.

First, I recommend that you request a free [GitHub Student](https://education.github.com/pack) account, if you do not already have one.

### macOS

The macOS operating system includes a Unix subsystem accessible from the Terminal.  The vast majority of the work for this course will take place in this environment.

1. Install the Homebrew backage manager by following the instructions at http://brew.sh.  We will use this to install other programs.
2. Use Homebrew to install Python 3 and wget.

        brew install python
        brew install wget

3. Installing Python 3 should also install pip.  Prooceed to Part 2.

### Windows 10

On Windows, you have two recommended options; you don't need to do both: you can either use the Windows Subsystem for Linux or download a Linux-like command line terminal, called Cmder.

#### Option 1: Linux Subsystem for Windows

To go this route, you will need to go to the Microsoft Store on Windows 10 and download a version of Linux.  This guide will assume that you use Ubuntu, but Debian and OpenSUSE are also options.  Doing this creates a functional Linux environment for Windows without need of a virtual machine.

Follow the instructions here: https://docs.microsoft.com/en-us/windows/wsl/install-win10

Then open the Ubuntu command linen and complete the setup instructions under **Unbuntu Linux** in this guide.  This environment does **not** support graphical user interfaces.

#### Option 2: Use a Linux-like Windows Environment

1. Download and install the terminal, [Cmder](http://cmder.net).  Add an icon for it in an easily accessible place.
2. Download and install [Chocolatey](https://chocolatey.org)
3. Use Cmder to install Python 3 and pip.

        choco install python3
        choco install pip

4. Install Emacs, Git, OpenSSH, grep, and wget.
    choco install unixutils.

        choco install emacs
        choco install git
        choco install openssh
        choco install grepwin
        choco linstall wget
     


### Ubuntu Linux

1. Install emacs.  On Ubuntu, this is:

        sudo apt-get install emacs

2. Install Python 3 by running the following commands.

     ````shell
     sudo apt-get update
     sudo apt-get -y upgrade
     sudo apt-get install -y python3-pip
     sudo apt-get install build-essential libssl-dev libffi-dev python-dev
     ````

## Part 2: Using the Command Line

These days, most computer users are unfamiliar with the command line interface (CLI), being more accustomed to graphical users interfaces found in operating systems such as Windows and macOS.  But programmers should be comfortable with the command line.  At a minimu, you should be comfortable changing, creating, copying, and moving folders and files and running programs from the command line.  As with anything else, the more you use it, the more comfortable you will become with it.  The most important shell commands are: `cd`, `mv`, `ls`, `mkdir`, `rmdir`, `rm`, `pwd`, and `cp`.

You may find the following resources helpful for getting started:

https://linuxsurvival.com/linux-more-command/

https://maker.pro/linux/tutorial/basic-linux-commands-for-beginners

https://www.w3resource.com/linux-system-administration/i-o-redirection.php

##Part 3: Writing and running a Python program

Python is interpreted, not compiled.  To ensure that Python is installed correctly, try typing `python3` at the command line.  You can exit with Ctrl+D.

You'll need an editor to write and save your Python programs.  A relatively easy-to-use open source editor is [Atom](http://atom.io). A more advanced editor is Emacs, but it has a higher learning curve.

Once you write a Python program, save it to a `.py` file and run it by typing `python3 helloworld.py`.
