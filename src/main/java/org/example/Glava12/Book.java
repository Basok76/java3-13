package org.example.Glava12;

public class Book {
    private final String title;
    public Boolean isReadingRoomOnly;

    public Book(String title, Boolean isReadingRoomOnly) {
        this.title = title;
        this.isReadingRoomOnly = isReadingRoomOnly;
    }

    public String getTitle() {
        return title;
    }

}
