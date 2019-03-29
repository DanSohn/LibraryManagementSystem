T12LMS - I1

Team: 12
Project: Library Management System
Members:
	Matthew Allwright	- 30037812
	Karim Beyk			- 30027342
	Jacob Cuke			- 30030179
	Jacob Krmpotic
	Zander von Neudegg	- 30051361
	Daniel Sohn			- 30017825
	

This document will act as a user-readable manual for the use of the First Iteration of Team 12's Library Management Software.


					**	Logging In	**

When "T12LMS - I1" is run and the "Log in" window appears, you will be promted for your User Type, Email, and Password. 

UserType defines the accessible permissions of a user, while the email and passwords act as identifying information.

You must choose the UserType of which your identifying information is linked, otherwise you will be unable to login.
This is a security measure to prevent unauthorized administrative actions.

Once your UserType, email, and password have been entered, click on the "Submit" button to attempt a log in.
If your credentials are incorrect, a message will appear telling you that you have not logged in successfully, you
will then be allowed to try again.

If your credentials are correct, you will be logged in as the UserType that you belong to, that is, the user type
that you specifed on your login. 


					**	   ADMIN	**

Once you are logged in as "Admin", you will be able to add Books, DVDs, and Online documents to the database for other
users to access.

First you must choose the Type of item that you want to add to the database, as the criteria for adding an item is
dependant on the item type.

If you want to add a Book, choose "BOOK" from the type menu and enter the book ID, Title, Author, and Library Location,
then click on the "add" button to add that book to the database.

If you want to add a DVD, choose "DVD" from the type menu and enter the DVD ID, Title, Producer, and Library Location,
then click on the "add" button to add that DVD to the database.

If you want to add an Online Document, choose "Online" from the type menu and enter the Document ID, Title, Author, and,
Online URL, then click on the "add" button to add that Document to the database.

You are able to add as many items as you like by following the above steps.

To Log Out, just click on the "Log Out" button, this will return you to the Log In screen.


					**	   Clerk	**

Once you are logged in as "Clerk", you will be able to add other Users to the database, allowing them access to the
functionalities of the library.

First, enter the User ID of the person being added to the database, then specify their User Type to determine their user 
permissions. 
Now enter their First Name, Last Name, email and password, then click on the "add" button to add them to the database.

You are able to add as many users as you like by following the above steps.

To Log Out, just click on the "Log Out" button, this will return you to the Log In screen.


					** 	  Student	**

Once you are logged in as "Student", a box will be presented containing all items signed out for that student.

The renting functionality is not yet implemented, but a simple prototype is provided, allowing you to arbitrarily
add and remove items from the top of the presented stack. (Note this does not affect any databases, as it is not a
user story covered in this iteration)

To Log Out, just click on the "Log Out" button, this will return you to the Log In screen.


					** 	  Faculty	**

This version does not include functionality for Faculty
