# Coffee Shop
Mock Coffee shop App that allows a customer to create a profile, login, edit their profile, and place order(s) with multiple items & delete these order(s)/item(s)
## Installation
### Part I - Java:
Install the following extensions in VS Code:
1) "Extension Pack for Java"
2) "Debugger for Java"
   
Next...

1) Find the absolute path to the "mvn" of the file "apache-maven" (I found its path from searching my C drive for "apache-maven")
   
ex: ![Screenshot 2023-11-17 151718](https://github.com/lasttycoon2506/artdealer/assets/114425878/a0f39f21-7cf5-44e5-af86-02b39b67f309)


2) Ctrl + Shift + P and type "preferences open user settings"
3) Add a new independent path making sure to exclude it from any other programs already in the file:
```
"maven.executable.path": "absolute path from step 1 here"
```

4) Save file, exit, reopen VS Code
5) Create new program and run to make sure Maven .exe path was found

### Part II - MySQL
1) From MySQL Community Downloads install MySQL; Server, Workbench, Shell, Router, Connector/j
2) Use MySQL 8.0 Command Line Client (in Windows desktop search bar) and enter credentials to ensure MySQL installed locally

## How-To
### Start Program
1) Start MySQL (Windows: Task Manager -> Services)
2) Run program

### Create New Customer
1) Customer button
2) New Customer button
3) Fill-out and click Register

### Login 
1) Customer button
2) Provide Login credentials
3) Login button

### Edit Customer
1) Login
2) Your Profile button
3) Edit info and click Edit

### Create Order
1) Login
2) New Order button
3) Choose coffee through dropdown selects/filters
4) Order button

### Delete Order(s)
1) Login
2) Orders button
3) Click on individual Order to delete OR delete by individual Item(s)



   







