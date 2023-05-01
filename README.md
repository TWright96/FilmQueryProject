# FilmQueryProject

#Description
FilmQueryApp takes in film data from a database and and allows the user to search for a film in the database by its ID or by searching a keyword of the users choice. The user is presented with a menu to select from at the start of the program and can continue searching the database until the user selects option 3 which ends the program.

#Technologies Used
* OOP
* mysql
* github lessons
* zoom
* terminal

#Lessons Learned
During this project I learned how to utilize database information within a java program. Something I struggled with is I couldn't understand why I was only returning 1 film when I would enter a search term. The problem was that in DatabaseAccessorObject.java I had an if statement instead of a while loop in my findFilmsBySearch method, so my method was only executing once.