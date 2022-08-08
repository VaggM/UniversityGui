# **GuiSubject.java**

Extends to GUI to create a window of adding a new Subject object
or listing all the stored ones so far.

Also provides a window that adds subjects to students and teachers.


## **Variables**

* winText: a list from which we can check what a user has typed in window text fields

* infoSubjectLabels: a list from which we can change the attributes shown of objects when listing them

* listModel: a window list for the students that are assigned to a class

* final static ADD, LIST: variables accessible outside the class to know what type of window you are creating 
(adding or listing objects)


## **Constructors**

* public GuiSubject(int type)

> creates a windows with super() and calls the appropriate method based on type argument

* private GuiSubject(Subject subject)

> creates a new window that edits a selected Subject

* public GuiSubject(DefaultListModel listModel, ArrayList<Subject> subjects)

> creates a new window showing all subject names and lets you add them into a student or teacher ADD window


## **Methods**

* private void newSubject()

> 1. creates variables of the window variables for code clarity
>
> 2. sets the window name to 'Subject Creation'
>
> 3. creates a list for each Subject variable needed for a new object
>
> 4. adds text on the window based on the list on the left and TextFields for each on the left (all saved on winText for later usage)
>
> 5. adds a complete button with its own actionPerformed method when it's being tapped
>
> 6. gets the variables writter within winText and tries to create a Subject object
>
>  if no error occurs, we discpatch the window and show a completion message
>
>  if an error occurs, we show an error message and wait for new user input
>
> 7. sets the final window size 


* private void listSubject(){

> 1. creates variables of the window variables for code clarity
>
> 2. sets the window name to 'Subject List'
>
> 3. creates an array with all the stored Subject names to add in a combo box that is placed at the top
>
> 4. adds an ActionListener to change the text below when we choose something in the combo box
>
> 5. creates a list for each Subject variable and puts text on the window to display each 
>
>  (right side text is within infoSubjectLabels and is changed based on the comboBox choice)
>
> 6. adds a button to create a new window for Subject editing, dispatching the current window
>
> 7. adds a button with its own ActionListener to delete a Subject being displayed and dispatches the current window
>
> 8. sets the final window size 


* private void editSubject(Subject subject)

> creates the window from adding a Subject with:
>
> * text fields prefilled with the saved subject's variables
>
> * 'unique_id' is locked because it's what makes each subject unique


* private void selectSubject(DefaultListModel listModelOld, ArrayList<Subject> subjects

> 1. creates variables of the window variables for code clarity
>
> 2. sets the window name to 'Subject Selection'
>
> 3. creates a window list with all the names and respective semesters of saved subjects
>
> 4. adds the list with a vertical scrollbar
>
> 5. adds an 'Add' button that adds the selected subject to the student or teacher we are currently creating or editing 
> 
> (making sure it isnt added already)
>
> 6. sets the final window size 


* private String textCheck()

> checks if all text fields aren't empty
  
