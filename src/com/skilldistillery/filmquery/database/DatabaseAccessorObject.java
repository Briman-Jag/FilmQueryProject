package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		String user = "student";
		String pass = "student";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT film.id, film.title, film.release_year, film.rating, film.description, language.name FROM film JOIN language ON language.id = language_id WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				int releaseYear = rs.getInt("release_year");
				String description = rs.getString("description");
				String rating = rs.getString("rating");
				String languageName = rs.getString("language.name");
				film = new Film(id, title, releaseYear, rating, description, languageName);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	public List<Film> findFilmsByKeyword(String keyword) {
		List<Film> films = new ArrayList<>();
		String user = "student";
		String pass = "student";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT id, title, release_year, description, rating FROM film where title LIKE ? OR description like ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				int releaseYear = rs.getInt("release_year");
				String description = rs.getString("description");
				String rating = rs.getString("rating");
				String languageName = filmLanguage(id);
				Film film = new Film(id, title, releaseYear, rating, description, languageName);
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	// To Do!
	public Actor findActorById(int actorId) {
		Actor actor = null;
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		String user = "student";
		String pass = "student";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				actor = new Actor();
				actor.setActorId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
//				actor.setFilms(findFilmsByActorId(actorId)); // An Actor has Films
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println(e);
		}

		return actor;
	}

	@Override
	public List<Film> findFilmsByActorId(int actorId) {
		List<Film> films = new ArrayList<>();
		String user = "student";
		String pass = "student";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
			sql += " rental_rate, length, replacement_cost, rating, special_features "
					+ " FROM film JOIN film_actor ON film.id = film_actor.film_id " + " WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				int releaseYear = rs.getInt("release_year");
				String rating = rs.getString("rating");
				Film film = new Film(filmId, title, releaseYear, description, rating);
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		String user = "student";
		String pass = "student";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

//	public void setUp() {
//		String user = "student";
//		String pass = "student";
//		try {
//			Connection conn = DriverManager.getConnection(URL, user, pass);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

//	@Override
//	public Film findAllInfoOnFilmById(int filmId) {
//		Film film = null;
//		String user = "student";
//		String pass = "student";
//		try {
//			Connection conn = DriverManager.getConnection(URL, user, pass);
//			String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
//			sql += " rental_rate, length, replacement_cost, rating, special_features " + " FROM film WHERE id = ?";
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			stmt.setInt(1, filmId);
//			ResultSet filmResult = stmt.executeQuery();
//			while (filmResult.next()) {
//				film = new Film();
//				film.setFilmId(filmResult.getInt("id"));
//				film.setTitle(filmResult.getString("title"));
//				film.setDescription(filmResult.getString("description"));
//				film.setReleaseYear(filmResult.getInt("release_year"));
//				film.setRating(filmResult.getString("rating"));
//				// To get all information on film
//				film.setLanguageId(filmResult.getInt("language_id"));
//				film.setRentalDuration(filmResult.getInt("rental_duration"));
//				film.setRentalRate(filmResult.getDouble("rental_rate"));
//				film.setLength(filmResult.getInt("length"));
//				film.setReplacementCost(filmResult.getDouble("replacement_cost"));
//				film.setSpecialFeatures(filmResult.getString("special_features"));
//			}
//			filmResult.close();
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return film;
//	}

}
