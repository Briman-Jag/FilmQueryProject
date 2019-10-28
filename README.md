## Film Query

### Week 7 Homework Project for Skill Distillery

### Overview
The Film Query program is a command-line application used to retrieve film data from a MySQL database. It allows users to follow a user menu and input commands to query the database. The three commands are a query to look up a film by its film ID number, a query to look up a film with a keyword search of text that may be in its title or description, and a quit command to exit the program.

The program is built up of 5 classes total. The classes are named DatabaseAccessor.java, DatabaseAccessorObject.java, Film.java, Actor.java, and FilmQueryApp.java. The DatabaseAccessor class serves as an interface class to give an abstract view of the five different query methods that are used in the program. The five query methods in the program are "public Film findFilmById(int filmId);" to display the Film details given the film ID, "public List<Film> findFilmsByKeyword(String keyword);" to list all the Films related to users keyword input, "public Actor findActorById(int actorId);" to find an actors names by their actor ID, "public List<Actor> findActorsByFilmId(int filmId);" to list the names of the actors in the films cast, and "public List<Film> findFilmsByActorId(int actorId);" to possibly list all films an actor has been in. The DatabaseAccessorObject class is where the SQL query methods are defined and the Java object population is done by receiving the data from the database tables from the MySQL server.

The Film and Actor classes have all the fields that correspond with their respective attributes it will receive from the MySQL databases "film", "film_actor", "language" and "actor" tables within the "sdvid.sql" file used for this project. These classes have getters and setters to attach all the attributes to the Films and Actors. They also have ArrayLists that were created to list a cast of actors within a film and create a list of the films that correspond with the users' keyword. Both classes use their toString methods to display the data related to their class.

The FilmQueryApp is where the code for the user interface is and calls upon the methods in the DatabaseAccessorObject to make the queries. This class has a method for launching the program, a menu to show users their choices, and a startUserInterface, which has a switch statement that gives the three commands their functionality. There are multiple try and catch blocks to help with SQLExceptions and InputMismatchExceptions that may happen in the event of messed up SQL code or user input that could break the program.

The grading requirements for this project are:
#### User Story 1
The user is presented with a menu in which they can choose to:
* Look up a film by its id.
* Look up a film by a search keyword.
* Exit the application.

#### User Story 2
If the user looks up a film by id, they are prompted to enter the film id.  If the film is not found, they see a message saying so.  If the film is found, its title, year, rating, and description are displayed.

#### User Story 3
If the user looks up a film by search keyword, they are prompted to enter it.  If no matching films are found, they see a message saying so.  Otherwise, they see a list of films for which the search term was found anywhere in the title or description, with each film displayed exactly as it is for _User Story 2_.

#### User Story 4
When a film is displayed, its language (`English`,`Japanese`, etc.) is also displayed.

#### User Story 5
When a film is displayed, the list of actors in its cast is displayed along with the title, year, rating, and description.

### How to run
When the program is launched it will print a menu giving the user the options to enter 1 to "Look up film by its ID", 2 to "Look up film a search keyword", or 3 to "Exit application". If the first option 1 is selected the user will then be prompted to enter an integer for a film ID number, which will then display the title, release year, rating, description, language, and the cast of actors within the film. If the second option 2 is chosen then the user is prompted to enter text for a keyword that will search for any matching characters within the database of film titles and film descriptions. The third command will exit the program.

To run this program the user will need to use a command prompt and a SQL server like MySQL with the sdvid.sql database file. A pom.xml file used a Maven compiler plugin to connect the program on eclipse to the proper version of the MySQL server.

### Technologies/Topics Applied
Technologies and topics used in this project were: MySQL, MAMP, SQL, ArrayLists, .xml, Maven, getters & setters, method calling, while loops, switch, toStrings, booleans, menu interface, foreach loops , Do/while loops, and Encapsulation.
