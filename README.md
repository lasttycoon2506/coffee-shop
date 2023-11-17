# artdealer

## Installation
### Part I:
Install the following extensions in VS Code:
1) "Extension Pack for Java"
2) "Debugger for Java"
Next...

1) Find the absolute path to the "mvn" of the file "apache-maven" (I found its path from searching my C drive for "apache-maven")
ex: ![Screenshot 2023-11-17 151718](https://github.com/lasttycoon2506/artdealer/assets/114425878/a0f39f21-7cf5-44e5-af86-02b39b67f309)


Ctrl + Shift + P and type "preferences open user settings"

Add a new independent path making sure to exclude it from any other programs already in the file:

"maven.executable.path": "absolute path from step 1 here"
Save file, exit, reopen VS Code

Ctrl + F5 making sure program runs and exe path was found

-install sqltools -install sqltools-driver -choose mysql -install mysql
