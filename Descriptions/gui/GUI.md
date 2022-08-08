# **GUI.java**

A general class that provides all variables needed for a window frame.

It initialiazes all these variables and then it can get extended to let us make our own windows.


## **Variables**

* frame: window frame

* panel: panel of the frame (blank space we can add objects)

* layout: GridBagLayout is a format we can arrange window objects in cells with (x,y) coordinates

* cons: GridBagConstraints tells the all the cell info for an object


## **Constructor**

public GUI()

> creates all default traits for a window with a GridBagLayout,
>
> cell size, starting coordinate(0,0), starting dimension and position of the window


## **Methods**

* get set methods for all variables

* public static void windowln(String title, String message, String messType)

> method that will create JOptionMessages for any messages we want the user to see,
>
> like success or errors in creating objects
