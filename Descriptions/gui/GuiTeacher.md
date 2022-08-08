# **GuiTeacher.java**

Extends to GUI to create a window of adding a new Teacher object
or listing all the stored ones so far.


## **Variables**

* winText: a list from which we can check what a user has typed in window text fields

* infoTeacherLabels: a list from which we can change the attributes shown of objects when listing them

* listModel: a window list for subjects the Teacher is in charge of

* tempSubs: list of the temporary subject names before creating a new Teacher object

* final static ADD, LIST: variables accessible outside the class to know what type of window you are creating 
(adding or listing objects)


## **Constructors**

* public GuiTeacher(int type)

> creates a windows with super() and calls the appropriate method based on type argument

* private GuiTeacher(Teacher teacher)

> creates a new window that edits a selected Teacher 


## **Methods**

* private void newTeacher()

> 1. creates variables of the window variables for code clarity
>
> 2. sets the window name to 'Teacher Creation'
>
> 3. creates a list for each Teacher variable needed for a new object except its subjects
>
> 4. adds text on the window based on the list on the left and TextFields for each on the left (all saved on winText for later usage)
>
> 5. adds a window list to start saving temporary subject names within (includes vertical scrollbar)
>
> 6. adds a '+' button below subjects to add any subject that is saved so far by creating a special window from GuiSubject
>
> 7. adds a 'Clear Subjects' button to clear any subject listed so far for the new object
>
> 8. adds a complete button with its own actionPerformed method when it's being tapped
>
> 9. gets the variables writter within winText and tries to create a Teacher object
>
>  (all subjects are saved in tempSubs in case we are creating a Teacher with subjects)
>
>  if no error occurs, we discpatch the window and show a completion message
>
>  if an error occurs, we show an error message and wait for new user input
>
> 10. sets the final window size 


* private void listTeacher(){

> 1. creates variables of the window variables for code clarity
>
> 2. sets the window name to 'Teacher List'
>
> 3. creates an array with all the stored Teacher names to add in a combo box that is placed at the top
>
> 4. adds an ActionListener to change the text below when we choose something in the combo box
>
> 5. creates a list for each Teacher variable and puts text on the window to display each 
>
>  (right side text is within infoStudentLabels and is changed based on the comboBox choice)
>
> 6. shows students signed up within each subject by selecting a subject on the list
>
> 7. adds a button to create a new window for Teacher editing, dispatching the current window
>
> 8. adds a button with its own ActionListener to delete a Student being displayed and dispatches the current
>
> 9. sets the final window size 


* private void editTeacher(Teacher teacher)

> creates the window from adding a Teacher with:
>
> * text fields prefilled with the saved teacher's variables
>
> * 'firstName' and 'lastName' are locked because it's what makes each teacher unique
>
> * subject list prefilled with the teacher's saved ones 


* private String textCheck()

> checks if all text fields aren't empty
