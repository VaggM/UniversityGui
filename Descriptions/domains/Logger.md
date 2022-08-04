# **Logger.java**

Writes, reads or clears any log file of saved instances.


## **Methods**

 
* public static void logInit(String classInfo, String logName)

> writes within the file named (logName + "Log.txt") whatever is within the classInfo String.
>
> for example for logName = "Student", it will write the info of one Student instance within StudentLog.txt


* public static void showLog()

> reads the general log file "Log.txt" and saves each line within an ArrayList<String>,
> 
> then creates a GuiLog instance to show the list within a window.


* public static String readClass()
 
> reads the ClassLog.txt file and once it finds a line starting with "Class-"
>
> it passes the bufferedReader to the Class.readClass method to get its variables and create an instance.
>
> readResult is there to prepare a message for the pop up window once reading ends.


* public static String clearFiles(String ... logFiles)

> clears all the log files based on the names within String logFiles as well as their respective list.
>
> clearResult is there to prepare a message for the pop up window once clearing ends.
>
> PS : we make a substring to clear lists because the method listClear wants the first four letters


* public static String resetFiles(String ... logFiles)

> does the same thing as clearFiles method but doesnt clear the respective class lists.
>
> It is used to reset the files to being empty when Logger class is rewriting them.
