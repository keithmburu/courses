#  Working Remotely



As we move this course online, it may become convenient or necessary for some students to work on a remote server.  This allows students to work in a controlled environment where all of the necessary software is set up and working and to which the professor and teaching assistants have access.



##  Secure Shell (SSH)

With a basic grasp of the command line, it is simple and easy to work on a remote computer just as though you were working at home.  The utility that makes this possible is **SSH**.  SSH creates a secure connection to a remote machine, sending every keystroke to the remote machine in real time and displaying the resulting screen.  If the connection has low latency, it may not even be apparent that the machine is remote.  

To connect to the remote machine, we just need the machines address. Some machines have an assigned hostname (e.g., ursinus.edu), which you can use if it exists and you know it, but every machine has a unique numerical IP address.  You have been provided with the IP address for our remote machine and a personal user name to log in to the machine.  To connect, type:

```bash
ssh user@address
```

where the `user` is your user name and `address` is the provided IP address.  For example:

```bash
ssh alvin@5.5.5.5
```

From there, you'll have a command promt as usual. To disconnect, type `exit`, or just close the terminal window.

Windows users can also use [PuTTY](https://www.putty.org/) if they prefer.

##  tmux

The program `tmux` is what's known as a **terminal multiplexer**, which allows one to work in several terminal sessions at once.  It's installed on the server.  This program has many features, but we're going to focus on just one: the ability to keep programs running even if you disconnect from the server, and the ability to pick up exactly where you left off.

To run tmux for the first time, *after you've connected to the server*, run the `tmux` command.  You'll see a screen that now has a bar at the bottom with some information, showing that you are inside of a tmux session.  As long as you are in this session, you don't have to worry about disconnecting or closing the window.  You can always pick up where you left off.

To show this, try running `emacs`.  Then close the terminal window to disconnect from the server.  Afterwards, open the terminal again and reconnect with `ssh`.

At first, it will appear that you are starting from scratch.  But if you type the following command, you'll see that your entire session was saved.

```bash
tmux attach
```

Running `tmux attach` "attaches" your still-running session, allowing you to pick up exactly where you left off.  If you don't add the `attach` argument, you will create a new, separate session that runs alongside the one you've already opened.  This is not recommended as you're just learning how this works.  You can list other tmux sessions with the follwing commnad:

```bash
tmux list-sessions
```

You can exit a session by typng `exit` inside of it or by using the `kill-session` argument.

The `tmux` program has many other features, such as splitting the window in to multiple frames, each with its own console session. For more information, see the links below.



##  Useful Commands

When sharing a computational resource with other uses (in this case, your fellow students), it's often useful to know who else if using resources.  If you're testing the performance of an algorithm, for example, it's important to know whether another user is running a test simultaneously.

The `top` command lists the processes currently using the most resources on the machine and who is running it. You can also observe the top processes for a specific user with 

```bash
top -u username
```

where `username` is the user's user name that you're interested in.  If, for some reason, you need to brows the web, you can use w3m, either as a standalone program or inside of emacs.  Or you can use `browsh`, which is a terminal front-end for FireFox with mouse support. See [browsh commands](https://www.brow.sh/docs/keybindings/).

###  Transfering Files

If you need to transfer your code between your personal computer and the serve, you can use either `sftp` or Secure Copy `scp`.

The `scp` command functions much like the `cp` command, but it allows you to copy to a remote server's location. For example:

```bash
scp user@5.5.5.5:./source_file.txt /home/user/
```

See http://www.hypexr.org/linux_scp_help.php for examples.

The `sftp` command allows you to browse directories, which some people prefer.  Alternatively, you can use a graphical FTP client, such as [FileZilla](https://filezilla-project.org/), and just point it to the server's address.  This allows you to simply drag and drop files.

A third alternative is to use GitHub (or a similar service) and `git`, which has the advantage of backing up your files.  See the references below of a simple `git` tutorial.



---

###  Useful References

[Simple Git Guide](https://rogerdudler.github.io/git-guide/)

[tmux Cheat Sheet](https://tmuxcheatsheet.com/)

[tmux Tutotiral for Splitting Windows](https://lukaszwrobel.pl/blog/tmux-tutorial-split-terminal-windows-easily/)

[tmux book](https://pragprog.com/book/bhtmux2/tmux-2)

[top Command in Linux with Examples](https://www.geeksforgeeks.org/top-command-in-linux-with-examples/)

[Linux scp Help](http://www.hypexr.org/linux_scp_help.php)

