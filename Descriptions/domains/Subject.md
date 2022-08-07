# **Subjcet.java**

A subject in a university.

Each subject has its own name, unique_id code, semester 
and students that are signed up.

This class has its set and get methods for each variable
plus any method needed for other classes to interact with
a subject instance.


## **Variables**

* name

* unique_id: code for each subject given by the university

* semester: subject's semester (up to 14)

* students: ArrayList of the students that have signed up

* totalSubject: a number showing how many subjects are there saved

used to create a unique ID for each Subject

* uniqueID: a unique ID for each Subject


## **Constructors**

* private Subject()

> creates an empty Subject instance

* private Subject(String name, String unique_id, short semester) throws Exceptions

> creates a subject with all its variables except students 
>
>(they have to manually assign themselves)


## **Methods**

 
* @Override
  public String toString()

> making the toString method return a Subject's info to write it in a log file

* get set methods for all variables

* public void addStudent(Student ... students)

> adds all the students given to a subject

* public static void newSubject(String name, String id, short semester) throws Exceptions

> creates a subject without students if the given unique_id isn't taken up already
>
> and throws to Exceptions if it is


* public static void newStudent(
	String firstName, String lastName, 
	short semester, int am, 
	String email, ArrayList<Subject> subjects
	) throws Exceptions

> creates a students with subjects if the given am isn't taken up already
>
> and throws to Exceptions if it is

* public static String readSubject(BufferedReader br) throws Exceptions, IOException

> continues reading a log file to create one subject from a read method in Logger.java
>
> if the unique_id isn't taken, the instance is created
>
> and returns a message based on it

* public static boolean checkDuplicates(Subject subject, ArrayList<Subject> subjects)

> function to find if there is a duplicate of a subject in an ArrayList
>
> it's used in the GuiStudent.java to check is temporary subjects are all unique


## **LogSubject**

A private class within Subject to send info to Logger to write log files and

it is extended to the abstract class LogAbstract to make sure it does something regarding logs


* public void logAction(String info)

> if info has anything within it, it s written within 'SubjectLog.txt'
>
> and always confirms the new log within the general 'Log.txt'
