package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

//	private void test() throws SQLException {
//	  Film film = db.findFilmById(1);
//	  System.out.println(film);
//	  Actor actor = db.findActorById(1);
//	  System.out.println(actor);
//	  List<Film> films = db.findFilmsByActorId(1);
//	  System.out.println(films);
//	}

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to the Film Query Application!");
		startUserInterface(input);
		input.close();
	}

	private void startUserInterface(Scanner input) throws SQLException {

		int choice = 0;
		do {
			menu();
			try {
				choice = input.nextInt();
				switch (choice) {

				case 1:
					System.out.println("___Searching for Film by Film's ID___");
					System.out.println("Enter Film ID: ");
					try {
						int filmId = input.nextInt();
						Film film = db.findFilmById(filmId);
						if (film == null) {
							System.out.println("No film matching Film ID of " + filmId + ".");
						} else {
							System.out.println(film);
						}
					} catch (InputMismatchException e) {
						System.out.println("Not valid. A Film ID integer must be entered.");
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
					break;
				default:
					System.out.println("Not a valid Option. Please select number from menu.");
					menu();
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Not a valid choice. Please enter menu option 1, 2, or 3.");
				choice = input.nextInt();
			}
		} while (choice != 3);
		System.exit(0);
	}

	private void menu() {
		System.out.println("________________________________________________________");
		System.out.println("Please enter one of the number to select a Query option:");
		System.out.println("1 - Look up film by its ID");
		System.out.println("2 - Look up film a search keyword");
		System.out.println("3 - Exit application");
	}

}
