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
		//app.test();
		app.launch();
	}

//	private void test() {
//		List<Film> list = db.findFilmsBySearch("tiara");
//		if (list.size() > 0) {
//			System.out.println(list);
//		} else {
//			System.out.println("No such films found.");
//		}
//		Film film = db.findFilmById(2);
//		if (film != null) {
//			System.out.println(film);
//		} else {
//			System.out.println("No such film found.");
//		}
//		Actor actor = db.findActorById(33);
//		if (actor != null) {
//			System.out.println(actor);
//		} else {
//			System.out.println("No such actor found.");
//		}
//	}

	private void launch() {
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
		} while (select != 3);
	}

	public void menu() {
		System.out.println("-------------------Menu-------------------");
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
			language(film.getLanguageId());
			List<Actor> cast = db.findActorsByFilmId(filmId);
			System.out.println("CAST: ");
			for(Actor actor: cast) {
				System.out.println(actor.getFirstName() + " " + actor.getLastName());
			}
		} else {
			System.out.println("No such film found.");
		}
	}

	public void lookUpFilmBySearch() {
		System.out.println("Please enter a search term: ");
		String search;
		search = scan.next();
		List<Film> films = db.findFilmsBySearch(search);
		if (films.size() > 0){	
		for(Film film: films) {
			System.out.println(film);
			language(film.getLanguageId());
			List<Actor> cast = db.findActorsByFilmId(film.getId());
			System.out.println("CAST: ");
			for(Actor actor: cast) {
				System.out.println(actor.getFirstName() + " " + actor.getLastName());
			}
		}
		} else {
			System.out.println("No such films found.");
		}
	}
	
	public void language(int lang) {
		if (lang == 1) {
			System.out.println("Language: English");		
		}
		if (lang == 2) {
			System.out.println("Language: Italian");		
		}
		if(lang == 3) {
			System.out.println("Language: Japanese");	
		}
		if(lang == 4) {
			System.out.println("Language: Mandarin");
		}
		if(lang == 5) {
			System.out.println("Language: French");
		}
		if(lang == 6) {
			System.out.println("Language: German");
		}
		
	}
	
}
