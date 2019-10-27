package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
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
		menu();
		startUserInterface(input);
		input.close();
	}

	private void startUserInterface(Scanner input) throws SQLException {

		Boolean keepGoing = true;
		do {
			int choice = input.nextInt();
			switch (choice) {

			case 1:
				System.out.println("___Searching Film by Film's ID___");
				System.out.println("Enter Film ID: ");
				int filmId = input.nextInt();
				Film film = db.findFilmById(filmId);
				System.out.println(film);
				break;
			case 2:
				System.out.println("___Searching Film by keyword___");
				System.out.println("Enter keyword: ");
				String keyword = input.next();

				break;
			case 3:
				System.out.println("***Quiting Film Query Application***");
				keepGoing = false;
				break;
			default:
				System.out.println("Not a valid Option. Please select number from menu.");
				menu();
				break;

			}
		} while (keepGoing);
		System.exit(0);

	}

//	private void userDecision(int choice) {
//		
//		switch (choice) {
//
//		case 1:
//			System.out.println("___Searching Film by Film's ID___");
//			System.out.println("Enter Film ID: ");
//			break;
//		case 2:
//			System.out.println("___Searching Film by keyword___");
//			System.out.println("Enter keyword: ");
//			break;
//		case 3:
//			System.out.println("***Quiting Film Query Application***");
//			System.exit(0);
//			break;
//		default:
//			System.out.println("Not a valid Option. Please select number from menu.");
//			break;
//			
//		}
//	}

	private void menu() {
		System.out.println("Welcome to the Film Query Application!");
		System.out.println("Please enter one of the number to select a Query option:");
		System.out.println("1 - Look up film by its ID");
		System.out.println("2 - Look up film a search keyword");
		System.out.println("3 - Exit application");
	}

}
