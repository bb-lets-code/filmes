package com.letscode.app.model;

public class Rating {
    private Double rating;
    private int votes;

    public Rating(Double rating, int votes) {
        this.rating = rating;
        this.votes = votes;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}