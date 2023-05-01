package com.skilldistillery.filmquery.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Film {
	private int id;
	private String title;
	private String desc;
	private int realeaseYear;
	private int languageId;
	private int rentalDuration;
	private double rentalrate;
	private double length;
	private double replacementCost;
	private String rating;
	private String special_features;
	private List<Actor> cast;

	public Film(int id, String title, String desc, int realeaseYear, int languageId, int rentalDuration,
			double rentalrate, double length, double replacementCost, String rating, String special_features) {
		super();
		this.id = id;
		this.title = title;
		this.desc = desc;
		this.realeaseYear = realeaseYear;
		this.languageId = languageId;
		this.rentalDuration = rentalDuration;
		this.rentalrate = rentalrate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.special_features = special_features;
	}

	public Film(int id2, String title2) {
		this.id = id2;
		this.title = title2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getRealeaseYear() {
		return realeaseYear;
	}

	public void setRealeaseYear(int realeaseYear) {
		this.realeaseYear = realeaseYear;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public double getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public double getRentalrate() {
		return rentalrate;
	}

	public void setRentalrate(double rentalrate) {
		this.rentalrate = rentalrate;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecial_features() {
		return special_features;
	}

	public void setSpecial_features(String special_features) {
		this.special_features = special_features;
	}

	public List<Actor> getActor() {
		return cast;
	}

	public void setActor(List<Actor> cast) {
		this.cast = cast;
	}

	public List<Actor> getActors() {
		List<Actor> copy = new ArrayList<>(cast);
		return copy;
	}

	@Override
	public String toString() {
		return "\nFilm [id=" + id + ", title=" + title + ", desc=" + desc + ", realeaseYear=" + realeaseYear
				+ ", languageId=" + languageId + ", rentalDuration=" + rentalDuration + ", rentalrate=" + rentalrate
				+ ", length=" + length + ", replacementCost=" + replacementCost + ", rating=" + rating
				+ ", special_features=" + special_features + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cast, desc, id, languageId, length, rating, realeaseYear, rentalDuration, rentalrate,
				replacementCost, special_features, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(cast, other.cast) && Objects.equals(desc, other.desc) && id == other.id
				&& languageId == other.languageId
				&& Double.doubleToLongBits(length) == Double.doubleToLongBits(other.length)
				&& Objects.equals(rating, other.rating) && realeaseYear == other.realeaseYear
				&& Double.doubleToLongBits(rentalDuration) == Double.doubleToLongBits(other.rentalDuration)
				&& Double.doubleToLongBits(rentalrate) == Double.doubleToLongBits(other.rentalrate)
				&& Double.doubleToLongBits(replacementCost) == Double.doubleToLongBits(other.replacementCost)
				&& Objects.equals(special_features, other.special_features) && Objects.equals(title, other.title);
	}
}