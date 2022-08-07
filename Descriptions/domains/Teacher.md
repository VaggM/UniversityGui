# **Teacher.java**

A teacher/professor in a university.

Each teacher has its own person traits, email 
and subjects he is in charge for.

This class has its set and get methods for each variable
plus any method needed for other classes to interact with
a teacher instance.


## **Variables**

* extends to person: firstName, lastName, birthDate

* email: teacher's email

* subjects: ArrayList of the subjects the teacher is in charge of

* totalTeachers: a number showing how many teachers are there saved

used to create a unique ID for each Teacher

* uniqueID: a unique ID for each Teacher


## **Constructors**

* private Teacher()

> creates an empty Teacher instance

* private Teacher(String firstName, String lastName, String email) throws Exceptions

> creates a teacher with all its variables except the subjects

* private Teacher(String firstName, String lastName, String email, ArrayList<Subject> subjects) throws Exceptions

> creates a teacher with all its variables


## **Methods**

 
* @Override
  public String toString()

> making the toString method return a Teacher's info to write it in a log file

* get set methods for all variables

* public static void newTeacher(
	String firstName, String lastName, 
	String email
	) throws Exceptions

> creates a teacher without subjects if the given 'lastName + firstName' isn't taken up already
>
> and throws to Exceptions if it is

* public static void newTeacher(
	String firstName, String lastName, 
	String email, ArrayList<Subject> subjects
	) throws Exceptions

> creates a teacher with subjects if the given 'lastName + firstName' isn't taken up already
>
> and throws to Exceptions if it is

* public static String readTeacher(BufferedReader br) throws Exceptions, IOException

> continues reading a log file to create one teacher from a read method in Logger.java
>
> if the 'lastName + firstName' isn't taken, the instance is created
>
> and returns a message based on it

* public static boolean checkDuplicates(String str, ArrayList<String> strings)

> function to find if there is a duplicate of a string in an ArrayList
>
> it's used in the readTeacher method to make sure no one has written the same subject twice in a log file


## **LogTeacher**

A private class within Teacher to send info to Logger to write log files and

it is extended to the abstract class LogAbstract to make sure it does something regarding logs


* public void logAction(String info)

> if info has anything within it, it s written within 'TeacherLog.txt'
>
> and always confirms the new log within the general 'Log.txt'
