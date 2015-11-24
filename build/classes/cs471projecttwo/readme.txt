Fall 2015 CS471 Price
Project Two 
11/23/2015
by Caroline Chey


This is a program to simulate file systems. 
When the "Initialize System" button is clicked, from "driveA.txt", "driveB.txt", and "driveC.txt", three separate drives A, B, and C are created.

Each directory can contain up to five entries.
File system cannot be more than four levels deep.



***GUI
There are three large text areas for, in order, user view of VFS, Before view of actual file systems, and current view of actual file systems.
As functions(methods) are executed, these text areas will be updated accordingly. 

Features include printing, deleting, and adding files. 
When adding, user may specify type (i or d or v) and provide one line data with exact, valid file path.
When deleting, user must provide an existing, valid file path.
When printing, user must provide an existing, valid file path.
After a successful addition or deletion, GUI will be updated to reflect the change and present a "before" view of actual file systems.




***Classes
ComputerOS: contains a vector of file systems to hold file systems, which are built from supplised data files, driveA.txt, driveB.txt, and driveC.txt
variable mRootDevice represents the root, which is driveA in this case
Contains functions (methods) for printing user view of the VFS and a view of the actual file systems. 
 

FileSystem: represents a file system. Contains functions (methods) to build a file system from a data file.

File: a base class for files

Inode: i-node. Contains filename and a one-line unique data

Vnode: v-node. Contains name of filesystem

Dnode: d-node. Contains an array of size 5 to hold entries, each entry contains a file, its name, and its type. 
Contains functions(methods) to insert entries.
