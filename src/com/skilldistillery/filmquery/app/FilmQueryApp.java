package com.skilldistillery.filmquery.app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();			// Used for testing
		app.launch();
	}

	// Used for testing
//	private void test() throws SQLException {
//	  Film film = db.findFilmById(1);
//	  System.out.println(film);
//	  Actor actor = db.findActorById(1);
//	  System.out.println(actor);
//	  List<Film> films = db.findFilmsByActorId(1);
//	  System.out.println(films);
//	}

	private void launch() {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to the Film Query Application!");
		startUserInterface(input);
		input.close();
		System.exit(0);
	}

	private void startUserInterface(Scanner input) {
		boolean keepGoing = true;
		int choice = 0;
		while (keepGoing) {
			try {
				menu();
				choice = input.nextInt();
				switch (choice) {
				case 1:
					int filmId = 0;
					while (keepGoing) {
						System.out.println("___Searching for Film by Film's ID___");
						System.out.println("Enter Film ID: ");
						try {
							filmId = input.nextInt();
							Film film = db.findFilmById(filmId);
							if (film == null) {
								System.out.println("No film matching Film ID of " + filmId + ".");
								break;
							} else {
								System.out.println(film);
								break;
							}
						} catch (InputMismatchException e) {
							System.out.println("Not valid. A Film ID integer must be entered.");
							input.nextLine();
						}
					}
					break;
				case 2:
					System.out.println("___Searching for Film by keyword___");
					System.out.println("Enter keyword: ");
					String keyword = input.next();
					List<Film> films = db.findFilmsByKeyword(keyword);
					if (films.isEmpty()) {
						System.out.println("No films matching keyword of " + keyword + ".");
					} else {
						System.out.println(films);
					}
					break;
				case 3:
					System.out.println("***Quiting Film Query Application***");
					keepGoing = false;
					break;
				default:
					System.out.println("Not a valid Option. Please select number from menu.");

					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Not valid. Please enter integers 1, 2, or 3.");
				input.nextLine();
			}
		}
		input.close();
	}

	private void menu() {
		System.out.println("________________________________________________________");
		System.out.println("Please enter one of the number to select a Query option:");
		System.out.println("1 - Look up film by its ID");
		System.out.println("2 - Look up film a search keyword");
		System.out.println("3 - Exit application");
	}

}
