package entities;

public class HashTag implements Comparable<HashTag> {
    private static long nextId = 1;

    private long id;
    private String text;

    public HashTag() {
        this.id = getNextId();
    }

    public HashTag(String text) {
        this.id = getNextId();
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private static synchronized long getNextId() {
        return nextId++;
    }


    public static void setNextId(long nextId) {
        HashTag.nextId = nextId;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int compareTo(HashTag o) {
        return 0;
    }
}

