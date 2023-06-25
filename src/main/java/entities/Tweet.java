package entities;

import java.time.LocalDate;
import java.util.Objects;

public class Tweet implements Comparable<Tweet> {
    private static long nextId = 1;

    private long id;
    private String content;
    private String source;
    private boolean isRetweet;
    private LocalDate fecha;
    private HashTag hashTags;

    public Tweet() {
        this.id = getNextId();
        this.hashTags = hashTags;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isRetweet() {
        return isRetweet;
    }

    public void setisRetweet(boolean retweet) {
        isRetweet = retweet;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public HashTag getHashTags() {
        return hashTags;
    }

    public void setHashTags(HashTag hashTags) {
        this.hashTags = hashTags;
    }

    private static synchronized long getNextId() {
        return nextId++;
    }

    public static void setNextId(long nextId) {
        Tweet.nextId = nextId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRetweet(boolean retweet) {
        isRetweet = retweet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet = (Tweet) o;
        return Objects.equals(fecha, tweet.fecha);
    }

    @Override
    public int compareTo(Tweet o) {
        return 0;
    }
}
