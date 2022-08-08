# **GuiLog.java**

Extends to GUI to create a window of just showing whatever is within the Log.txt with a scrollbar.



## **Constructor**

public GuiLog(ArrayList<String> logs)

> creates a windows with super() and the method will add its content


## **Methods**

* private void listLog(ArrayList<String> logs)

> 1. creates variables of the window variables for code clarity
>
> 2. sets the window name to 'Logs'
>
> 3. creates a window's list with 1 item per row
>
> 4. passes the list to a vertical scrollbar object
>
> 5. adds all items within logs to the list
>
> 6. sets window frame size

