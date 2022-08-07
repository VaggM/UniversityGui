# **Department.java**

Department, part of a university.
Each department has its own name, students, teachers and subjects.

This class has its set and get methods for each variable
plus any method needed for other classes to interact with
a department instance.

PS: the current version of departments does not include
their own students, teachers and subjects.
It can be updated if the future.

The Student, Teacher and Subject classes already have whatever is needed 
to expand on Departments, if ever needed.


## **Variables**

* name: name of the Department

* subjects: ArrayList of the subjects the department has

* teachers: ArrayList of the teachers the department has

* students: ArrayList of the students the department has

* totalDepartments: a number showing how many departments are there saved

used to create a unique ID for each department

* uniqueID: a unique ID for each Department


## **Constructors**

* private Department()

> creates an empty Department instance

*  private Department(String name)

> creates a Department instance with a name

* private Department(
	String name, ArrayList<Subject> subjects, 
	ArrayList<Teacher> teachers, ArrayList<Student> students
	)

> creates a Department instance with a name and all its lists


## **Methods**

 
* @Override
  public String toString()

> making the toString method return a Department's info to write it in a log file

* get set methods for all variables

* public static void newDepartment(String name) throws Exceptions

> calls a constructor if the name of the instance does not exist already
>
> and throws to our expection class in case it does

* public static String readDepartment(BufferedReader br) throws Exceptions, IOException

> continues reading a log file to create one department from a read method in Logger.java
>
> if the name is unique the instance is created
>
> and returns a message based on it


## **LogDepartment**

A private class within Department to send info to Logger to write log files and

it is extended to the abstract class LogAbstract to make sure it does something regarding logs


* public void logAction(String info)

> if info has anything within it, it s written within 'DepartmentLog.txt'
>
> and always confirms the new log within the general 'Log.txt'
