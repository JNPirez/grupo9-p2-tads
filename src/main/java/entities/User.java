package entities;

import uy.edu.um.prog2.adt.linkedlist.LinkedList;

import java.time.LocalDate;

public class User implements Comparable<User> {
    private String name;
    private long id;
    private LinkedList<Tweet> userListTweets;
    private LocalDate LastTweet;
    private Boolean isVerified;
    private LocalDate creationDate;
    private String location;
    private Double followers;
    private Double favourites;
    private Double friends;
    private String description;
    private int countTweets;

    public User(String name) {
        this.name = name;
        userListTweets = new LinkedList<>();
        countTweets = 0;


    }

    public User() {
        userListTweets = new LinkedList<>();
        countTweets = 0;
    }

    public void incrementId() {
        this.id++;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Tweet> getTweets() {
        return userListTweets;
    }

    public void setCountTweets(LinkedList<Tweet> tweets) {
        this.userListTweets = tweets;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getLastTweet() {
        return LastTweet;
    }

    public void setLastTweet(LocalDate lastTweet) {
        LastTweet = lastTweet;
    }

    public Boolean getisVerified() {
        return isVerified;
    }

    public void setisVerified(Boolean verified) {
        isVerified = verified;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getFollowers() {
        return followers;
    }

    public void setFollowers(Double followers) {
        this.followers = followers;
    }

    public Double getFavourites() {
        return favourites;
    }

    public void setFavourites(Double favourites) {
        this.favourites = favourites;
    }

    public Double getFriends() {
        return friends;
    }

    public void setFriends(Double friends) {
        this.friends = friends;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LinkedList<Tweet> getUserListTweets() {
        return userListTweets;
    }

    public int getCountTweets() {
        return countTweets;
    }

    public void setCountTweets(int countTweets) {
        this.countTweets = countTweets;
    }

    public void incrementCountTweets() {
        this.countTweets++;
    }

    @Override
    public int compareTo(User otherUser) {
        return Double.compare(this.getFavourites(), otherUser.getFavourites());
    }
}

