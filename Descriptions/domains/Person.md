# **Person.java**

Person is a common point for students and teachers which can be extended to it

we can include the the first name, last name of a person


## **Variables**

* firstName
* lastName
* birthDate: refers to the time the person is created and not its birthday


## **Constructors**

* public Person()

> creates an empty Person instance

* public Person(String firstName, String lastName) throws Exceptions

> creates a person without a birthDate

* public Person(String firstName, String lastName, Date birthDate) throws Exceptions

> creates a person with all its variables



## **Methods**

 
* get and set methods for all its variables 

> they throw to Exceptions if set argument is invalid

* private boolean checkName (String name) 

> makes sure the name argument is in a title format (Name)
