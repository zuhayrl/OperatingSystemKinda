# OperatingSystemKinda
I decided to make a simple OS coz i was bored and wanted to mess around with file systems and command line interfaces.

## What it is
This is a basic operating system simulation that runs in your terminal. It's got a file system with folders and files, and you can navigate around using familiar commands like `cd`, `ls`, `mkdir`, etc.

## How to run it
1. Compile: `javac *.java`
2. Run: `java main`
3. Type commands and have fun!

## Commands that work
- `ls` - list what's in the current folder
- `cd <path>` - move to a different folder (supports paths like `folder1/folder2`)
- `cd ~` - go back to root
- `cd ..` - go up one level
- `pwd` - show current directory name
- `mkdir <name>` - create a new folder
- `touch <name>` - create an empty file
- `nano <name> <data>` - create a file with some data
- `cat <file>` - show what's inside a file
- `rm <file>` - delete files only (Unix behavior: fails on directories)
- `rm -r <folder>` - delete a folder and all its contents recursively (to be added)
- `rmdir <folder>` - delete empty directories only (Unix behavior: fails if not empty)
- `exit` - quit the program

### Unix-style Command Behavior
The commands follow Unix conventions:
- `rm` only removes files, not directories (will show error if you try to remove a folder)
- `rmdir` only removes empty directories (will fail if directory contains files/folders)
- For recursive deletion, you'd need `rm -r` (not implemented yet but could be added later)
- This prevents accidental deletion of important folders and their contents

# Structure
## Nodes
nodes will represent both files and folders, where nodes will be a parent class and folders and files will be child classes. 

### Files
- Files will have an attribute for data and a name (Other properties can be added later)
- Files cannot have children
- You can put text data in them and read it back

### Folders
- Folders will have attributes for data and for their contents (a list of some sort)
- Folders can have children, which would be the files and folders inside them
- They know their parent folder (useful for `cd ..`)

Using this we can see that all nodes should have:
- A name
- A type (this is done using child classes, each type is a child)
- Other properties like editable etc to be added after.

## File System Tree
The whole thing is basically a tree structure where:
- Root folder is at the top
- Each folder can contain other folders and files
- You navigate by moving from node to node
- Paths work like `folder1/subfolder/file.txt`

## Files in the project
- `main.java` - the main program with the command loop and all the command logic
- `tree.java` - the file system structure (Node, File, Folder classes)
- `nary.java` - some n-ary tree examples (not used in main program)
- `test.java` - tests for the tree structure

## Things that might be added later
- More commands (cp, mv, better rm)
- File permissions
- Better path handling
- Maybe some colors in the output
- Tab completion would be cool
- Command history
