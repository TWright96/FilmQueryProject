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
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static final String USER = "student";
	private static final String PWD = "student";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Film> findFilmsBySearch(String name) {
		List<Film> films = new ArrayList<>();
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);
			String sql = "SELECT * FROM film WHERE title LIKE ? OR description LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + name + "%");
			stmt.setString(2, "%" + name + "%");
			ResultSet filmResult = stmt.executeQuery();
			while (filmResult.next()) {
				int id = filmResult.getInt("id");
				String title = filmResult.getString("title");
				String desc = filmResult.getString("description");
				int releaseYear = filmResult.getInt("release_year");
				int languageId = filmResult.getInt("language_id");
				int rentalDuration = filmResult.getInt("rental_duration");
				double rentalRate = filmResult.getDouble("rental_rate");
				double length = filmResult.getDouble("length");
				double replacementCost = filmResult.getDouble("replacement_cost");
				String rating = filmResult.getString("rating");
				String specialFeatures = filmResult.getString("special_features");
				film = new Film(id, title, desc, releaseYear, languageId, rentalDuration, rentalRate, length,
						replacementCost, rating, specialFeatures);
				films.add(film);
			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);
			String sql = "SELECT * FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet filmResult = stmt.executeQuery();
			if (filmResult.next()) {
				int id = filmResult.getInt("id");
				String title = filmResult.getString("title");
				String desc = filmResult.getString("description");
				int releaseYear = filmResult.getInt("release_year");
				int languageId = filmResult.getInt("language_id");
				int rentalDuration = filmResult.getInt("rental_duration");
				double rentalRate = filmResult.getDouble("rental_rate");
				double length = filmResult.getDouble("length");
				double replacementCost = filmResult.getDouble("replacement_cost");
				String rating = filmResult.getString("rating");
				String specialFeatures = filmResult.getString("special_features");
				film = new Film(id, title, desc, releaseYear, languageId, rentalDuration, rentalRate, length,
						replacementCost, rating, specialFeatures);
			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);
			String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();
			if (actorResult.next()) {
				int id = actorResult.getInt("id");
				String fn = actorResult.getString("first_name");
				String ln = actorResult.getString("last_name");
				actor = new Actor(id, fn, ln);
				actor.setFilms(findFilmsByActorId(actorId));
			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Film> findFilmsByActorId(int actorId) {
		List<Film> films = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);
			String sql = "SELECT film.* FROM film JOIN film_actor ON film.id = film_actor.film_id WHERE film_actor.actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				short releaseYear = rs.getShort(4);
				int langId = rs.getInt(5);
				int rentDur = rs.getInt(6);
				double rate = rs.getDouble(7);
				int length = rs.getInt(8);
				double repCost = rs.getDouble(9);
				String rating = rs.getString(10);
				String features = rs.getString(11);
				Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features);
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
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);
			String sql = "SELECT actor.* FROM actor JOIN film_actor ON film_actor.actor_id = actor.id WHERE film_actor.film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String fn = rs.getString("first_name");
				String ln = rs.getString("last_name");
				Actor actor = new Actor(id, fn, ln);
				actors.add(actor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

	@Override
	public Film findFilmBySearch(String name) {
		return null;
	}
}