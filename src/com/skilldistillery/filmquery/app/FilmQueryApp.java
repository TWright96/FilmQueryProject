package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	private DatabaseAccessor db = new DatabaseAccessorObject();
	Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.test();
		app.launch();
	}

	private void test() {

		List<Film> list = db.findFilmsBySearch("tiara");
		if (list != null) {
			System.out.println(list);
		} else {
			System.out.println("No such films found.");
		}

		Film film = db.findFilmById(2);
		if (film != null) {
			System.out.println(film);
		} else {
			System.out.println("No such film found.");
		}

		Actor actor = db.findActorById(33);
		if (actor != null) {
			System.out.println(actor);
		} else {
			System.out.println("No such actor found.");
		}
	}

	private void launch() {
		Scanner input = new Scanner(System.in);
		int select;
		do {
			menu();
			select = scan.nextInt();
			if (select == 1) {
				lookUpFilmById();
			}
			if (select == 2) {
				lookUpFilmBySearch();
			}
			if (select == 3) {
				System.out.println("Goodbye.");
				break;
			}
			startUserInterface(input); // ??
			System.out.println("Return to menu?");
			String option;
			option = input.next();
			if (option.equals("yes")) {
				continue;
			} else {
				input.close();
				System.out.println("Goodbye.");
				break;
			}
		} while (select != 3);
	}

	public void menu() {
		System.out.println("-------------------Menu------------------");
		System.out.println("1.Look up a film by its ID");
		System.out.println("2.Look up a film by a search keyword");
		System.out.println("3.Exit the application");

	}

	public void lookUpFilmById() {
		System.out.println("Please enter a film ID: ");
		int filmId;
		filmId = scan.nextInt();
		Film film = db.findFilmById(filmId);
		if (film != null) {
			System.out.println(film);
		} else {
			System.out.println("No such film found.");
		}
	}

	public void lookUpFilmBySearch() {
		System.out.println("Please enter a search term: ");
		String search;
		search = scan.next();
		List<Film> list = db.findFilmsBySearch(search);
		if (list != null) {
			System.out.println(list);
		} else {
			System.out.println("No such films found.");
		}
	}

	private void startUserInterface(Scanner input) {

	}
}
