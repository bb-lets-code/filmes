package com.letscode.app.model;

import java.math.BigDecimal;
import java.util.Arrays;

public class Movie {

    private String rank;
    
    private String title;
    private Integer year;
    private String[] genre;
    private String[] cast;
    private String[] directors;
    private String description;
    private String[] actors;
    private int runtime;
    private Rating rating;
    private BigDecimal revenue;
    private double metascore;

    public Movie(String rank, String title, Integer year, String[] genre, String[] cast, String[] directors,
            String description, String[] actors, int runtime, Rating rating, BigDecimal revenue, double metascore) {
        this.rank = rank;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.cast = cast;
        this.directors = directors;
        this.description = description;
        this.actors = actors;
        this.runtime = runtime;
        this.rating = rating;
        this.revenue = revenue;
        this.metascore = metascore;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public String[] getCast() {
        return cast;
    }

    public void setCast(String[] cast) {
        this.cast = cast;
    }

    public String[] getDirectors() {
        return directors;
    }

    public void setDirectors(String[] directors) {
        this.directors = directors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public double getMetascore() {
        return metascore;
    }

    public void setMetascore(double metascore) {
        this.metascore = metascore;
    }

    @Override
    public String toString() {
        return "Movie [actors=" + Arrays.toString(actors) + ", title=" + title + ", year=" + year + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((year == null) ? 0 : year.hashCode());
        result = prime * result + (( directors == null) ? 0 : directors.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Movie other = (Movie) obj;
        
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;

        if (year == null) {
            if (other.year != null)
                return false;
        } else if (!year.equals(other.year))
            return false;

        if (directors == null) {
            if (other.directors != null)
                return false;
        } else if (!directors.equals(other.directors))
            return false;
        return true;
    }


}