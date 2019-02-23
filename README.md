# Library Management System Team 12

Library Management System designed for SENG 300 by Team 12

Hey guys. I've gone ahead and created a very simple console based program that displays
the different menus and options that we'll need to implement into the program.
If you notice I've missed any of our requirements let me know!

As Code Reviewer I figured it's my responsibility to create and maintain our repository.
I chose GitLab since it's what my TA covered and we all have accounts automatically
as U of C students. If you're more familiar with GitHub, don't worry because the
commands are all the same. If you need a refresher of important commands I'll leave a list
of them below.

0. Download Git if you don't have it already. For Mac you can use it directly from the terminal. Not sure about Windows or Linux. May need a separate Git Bash program.

1. Getting the repo on your computer: git clone [Project URL] - You can find this URL on the project page

2. Create a separate branch for yourself (don't push directly to master): git checkout -b [your branch name] - e.g. dev_jacobc
   The -b option creates the branch if it doesn't already exist, so once the branch has been created, switch into it with: git checkout [your branch name]

3. Edit or add whatever files you need to

4. Add all the files you wish to commit to the repo: git add [file name] - (git add * will add everything in your cwd)

5. Commit the changes to the staging area: git commit -m "Description of what your commit changed/added goes in these quotes"

6. git status - useful command to check which branch you're in, which files haven't been added/committed yet

7. Push changes to remote: git push origin [your branch name] - e.g. git push origin dev_jacobc


NOTES ABOUT SKELETON PROGRAM:

Super basic console menu program, should help give us an idea of what we need to do.
To be honest, most of this project is just going to be interacting with entries in a database,
shouldn't be too difficult at all if we stay organized and divide the work.

RUN INSTRUCTIONS:

Compile with javac *.java

Run with java Driver

NO ECLIPSE:
Make sure the Database.txt is in the same directory that you're complining and runnin from

ECLIPSE:
Make a new Java project
Copy .java files into the src directory
Copy Database.txt into the project folder (the same one that has bin and src in it)
