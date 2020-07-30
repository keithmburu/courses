The purpose of this onerous lab is to get your computers setup for  the course.  Students who finish early may want to help other students.  Ideally, you will finish this lab during the lab time, but you may not.

This class will primarily use a Unix environment. You may use Windows for most assignments, but this will be your responsibility.  

Apple's Mac OS X is built on top of Darwin, an offshoot of BSD Unix.  Mac users should skip to the section under "Mac OS X."  Windows users  will have to install a virtual machine or dual boot setup.  The  instructions here are for a virtual machine setup.

**Windows Users**

These instructions will guide you to setup your environment on Mac OS X.

1. Download VirtualBox for Windows hosts here: [https://www.virtualbox.org/wiki/Downloads (Links to an external site.)](https://www.virtualbox.org/wiki/Downloads)

 

2. Download the Ubuntu ISO for [http://www.ubuntu.com (Links to an external site.)](http://www.ubuntu.com) The file is approximately 1.6GB in size.

3. Install Ubuntu in the virtual machine.

First, open the VirtualBox Manager.  Click *New*.  Name it Ubuntu 16.  The *Type* is Linux, and the *Version* is Ubuntu (64-bit). 

Click *Continue*.

1024MB (1GB) of RAM is reasonable, but more is usually better. You can always raise this later.

Click *Continue.*

Create a new virtual hard disk.  8GB is not enough. I recommend at  least 12-16GB, but if you have a large hard disk, you can allow more.  Continue clicking continue.  You hard disk should be dynamically  allocated. (Let me know if you are low on disk space).

Once you have gone through this process, right-click *Ubuntu 16,* and click *Start*.  It will ask you which disk from which to start the VM.  Select the ISO file that you downloaded from Ubuntu.

The VM installation disc image should begin to boot and run the  installation routine.  Select your preferred language, and click *Install Ubuntu.*

Uncheck "Download updates while installing Ubuntu." You can do this later.  Check "Install third-party software."  *Continue.*  Then click, *Install Now*, and click *Continue* again.  On the following screens, select your preferences. 

When it finishes and asks you to restart. If it asks you to remove  the installation medium, double-click the first icon in the lower  right-hand corner of the window and press [ENTER].

When the login screen appears, enter you password (unless you selected auto-login).

Congratulations.  You've installed Ubuntu Linux in a Virtual Machine.  

\4. Now, right-click on the desktop, and open the Terminal in the VM.  Enter the following commands (pressing [ENTER] after each one).

```
sudo apt-get install git

sudo apt-get install emacs24

sudo apt-get install openjdk-8-jdk
```

5. Open FireFox (third icon on the left) to download and install NetBeans and the [Java Development Kit (Links to an external site.)](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).  I recommend downloading the "All" version. Save the file to your disk.

6. When the download finishes, go back to the Terminal.

Type:

cd ~/Downloads

 sh netbeans-8.2-linux.sh

Click *Next* a few times and agree to the terms.  When the installation completes, click *Finish.*

7. Complete steps 2-5 under "Mac OS X Users."

8. Finally, in the VirtualBox menu, click *Devices,* and then click *Install Guest Additions.*  Then, in Ubuntu, click the CD icon in the bottom left.  

Then single-click VBoxLinuxAdditions.run and click *Run Software* in the upper right.  A terminal window will open.  When it finishes,  please [ENTER].  Log out (upper-right corner) and log back in for the  changes take effect.  Now, if you maximize the window, Ubuntu will fill  the screen.

9. (Recommended) Create an free Github student account.

http://education.github.com 

**Mac OS X Users.**

1. Download and install Homebrew.  Homebrew is an easy way to install command line software packages.

a.  Open Terminal and paste the following:

 

```
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)" (Links to an external site.)
```

 

b.  Once it finishes, type: 

```
brew install python3
brew install git
brew install emacs
```

It'll ask for your password.  Even though it'll be invisible, just type it as usual and then press [ENTER].

c.  Download and install the latest [Java Development Kit (Links to an external site.)](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and NetBeans. [https://netbeans.org/downloads/ (Links to an external site.)](https://netbeans.org/downloads/)  I recommend installing the largest package, but you must at least have  Java SE support.  Once the DMG downloads, open it and install the  packages.

2. Go back to the Terminal and type:

`emacs print_test.py`

a. Type the following:

print("Hello, my name is [your name here.]")

b. Press Ctrl-X Ctrl-S to save.

b.  Press Ctrl-C to exit.

(If you get stuck, press Ctrl-G a few times to cancel your previous command).

3. Back at the terminal, type:

`python print_test.py`

If you get an error, note this in your submission and try:

`python3 print_test.py`

4.  Type:

`java -version`

5.  If you do not finish this during the lab, upload (or copy and  paste) your print_test.py file and copy and paste the output of java  -version in the text of your submission.  

6. (Recommended) Create an free Github student account at https://education.github.com