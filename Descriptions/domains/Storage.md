# **Storage.java**

Stores all students, teachers, subjects and departments in their respective ArrayList.

Includes methods : 

* existsClass, checks if said Class instance exists already

* rewriteClass, rewrites the ClassLog.txt files based on the current stored class lists

* searchSubject, searches if there is a subject instance stored with the requested name

* listClear, clears one of the stored class lists

* orderClass, sorts the stored instances in alphabetical order


## **Variables**

* students, ArrayList of stored students

* teachers, ArrayList of stored teachers

* subjects, ArrayList of stored subjects

* departments, ArrayList of stored departments


## **Methods**

 
* public static short existsClass(variable being checked)
 
> checks if said Class instance exists already, returns 1 if it does
>
> * unique variable for each Class instance
> 
>    Student : int am
>
>    Teacher : String fullName, the combination of lastName + firstName
>
>    Subject : String uniqueID
>
>    Department : String name


* public static void rewriteClass()
 
> rewrites the ClassLog.txt files based on the current stored class lists
>
> by emptying the file and rewriting each stored instance again.


* public static Subject searchSubject(String name) throws Exceptions

> searches if there is a subject instance stored with the requested name
>
> and throws an error message if there isn't.


* public static void listClear(String listName)

> clears one of the stored class lists and anything connected to each instance of it.
>
> (for example by clearing students, you clear them from any subject they were connected)


* public static void orderStudents()

> sorts the stored instances in alphabetical order
> 
> it is done by checking each character on the instance names
>
> and saving the earliest in alphabetical order within a temporay list, while they are removed from their original list,
>
> then we copy the temporary sorted list into the original one.
>
> (subjects are ordered not only alphabetically but also based on their semester)
