# Main.java

Contains all the components used for the main frame, as well as all the actions taken for all buttons.


## **Constructors**


* public Main()

> * creates the main frame layout, has one panel with all the components attached to it.
>
> * sets ActionListener for all buttons
>
> * sets ActionCommand  for all buttons, named with a counter (button 1 to 19)
>
> * adds a WindowListener to confirm when closing the main frame


## **Methods**

 
* public static void main(String[] args)
 
> * writes on Log.txt that you started a new session
>
> * creates the main frame by calling the constructor
 
* public void actionPerformed(ActionEvent event)

> * handles each button action based on its ActionCommand (button 1 to 19)
>
> * buttons based on class objects
> 
>    1 -  4 : Student 
>
>    5 -  8 : Teacher
>
>    9 - 12 : Subject
>
>    13 - 16 : Department
> 
> * buttons based on their action for each object 
> 
>    1st : create a new window to add a new object
> 
>    2nd : read a file with previously saved objects "ObjectLog.txt"
> 
>    3rd : new window to list all objects (possible to edit or delete)
> 
>    4th : clear all the current objects and its log file "ObjectLog.txt"
> 
>    Read and Clear end with a pop-up window.
> 
> * buttons after 16
> 
>    17 : show the logs for each session "Log.txt"
> 
>    18 : clears the file "Log.txt"
> 
>    19 : clears every objects and every log file
