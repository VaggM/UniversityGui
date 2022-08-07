# **Student.java**

A student in a university.

Each student has its own person traits, unique 'am' codenumber, semester, email 
and subjects he has signed up for.

This class has its set and get methods for each variable
plus any method needed for other classes to interact with
a student instance.


## **Variables**

* extends to person: firstName, lastName, birthDate

* university: the name of the university,

it hasnt been used anywhere currently

* am: unique codenumber for each student

* email: student's email

* semester: student's semester (up to 14)

* subjects: ArrayList of the subjects the student has signed up

* totalStudents: a number showing how many students are there saved

used to create a unique ID for each student

* uniqueID: a unique ID for each Student


## **Constructors**

* private Student()

> creates an empty Student instance

* private Student(
	String firstName, String lastName, 
	int am, String email, short semester
	) throws Exceptions

> creates a student with all its variables except the subjects

* private Student(String firstName, String lastName,
	int am, String email, 
	short semester, ArrayList<Subject> subjects
	) throws Exceptions

> creates a student with all its variables


## **Methods**

 
* @Override
  public String toString()

> making the toString method return a Student's info to write it in a log file

* get set methods for all variables

* public static void newStudent(
	String firstName, String lastName, 
	short semester, int am, String email
	) throws Exceptions

> creates a student without subjects if the given am isn't taken up already
>
> and throws to Exceptions if it is

* public static void newStudent(
	String firstName, String lastName, 
	short semester, int am, 
	String email, ArrayList<Subject> subjects
	) throws Exceptions

> creates a student with subjects if the given am isn't taken up already
>
> and throws to Exceptions if it is

* public static String readStudent(BufferedReader br) throws Exceptions, IOException

> continues reading a log file to create one student from a read method in Logger.java
>
> if the am isn't taken, the instance is created
>
> and returns a message based on it

* public static boolean checkDuplicates(String str, ArrayList<String> strings)

> function to find if there is a duplicate of a string in an ArrayList
>
> it's used in the readStudent method to make sure no one has written the same subject twice in a log file


## **LogStudent**

A private class within Student to send info to Logger to write log files and

it is extended to the abstract class LogAbstract to make sure it does something regarding logs


* public void logAction(String info)

> if info has anything within it, it s written within 'StudentLog.txt'
>
> and always confirms the new log within the general 'Log.txt'
