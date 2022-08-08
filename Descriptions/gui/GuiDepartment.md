# **GuiDepartment.java**

Extends to GUI to create a window of addings a new Department object
or listing all the stored ones so far.


## **Variables**

* winText: a list from which we can check what a user has typed in window TextFields

* infoDepartmentLabels: a list from which we can change the attributes shown of objects when listing them

* final static ADD, LIST: variables accessible outside the class to know what type of window you are creating 
(adding or listing objects)


## **Constructor**

public GuiDepartment(int type)

> creates a windows with super() and calls the appropriate method based on type argument


## **Methods**

* private void newDepartment()

> 1. creates variables of the window variables for code clarity
>
> 2. sets the window name to 'Department Creation'
>
> 3. creates a list for each Department variable needed for a new object
>
> 4. adds text on the window based on the list on the left and TextFields for each on the left (all saved on winText for later usage)
>
> 5. adds a complete button with its own actionPerformed method when it's being tapped
>
> 6. we get the variables writter within winText and try to create a Department object
>
>  if no error occurs, we discpatch the window and show a completion message
>
>  if an error occurs, we show an error message and wait for new user input
>
> 7. sets the final window size 


* private void listDepartment(){

> 1. creates variables of the window variables for code clarity
>
> 2. sets the window name to 'Department List'
>
> 3. creates an array with all the stores Department names to add in a combo box that is placed at the top
>
> 4. adds an ActionListener to change the text below when we choose something in the combo box
>
> 5. creates a list for each Department variable and puts text on the window to display each 
>
>  (right side text is within infoDepartmentLabels and is changed based on the comboBox choice)
>
> 6. adds a button to delete a Department being displayed with its own ActionListener and dispatches the window when deleting
>
> 7. sets the final window size 

