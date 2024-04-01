package com.predictice.music.domain.models;

public class Album {
    private final String id;
    private final String title;
    private final String artist;
    private final String releaseYear;
    private final String coverURL;

    private Album(String id, String title, String artist, String releaseYear, String coverURL) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.coverURL = coverURL;
    }

    public static Album create(String id, String title, String artist, String releaseYear, String coverURL) {

        if (title == null || title.isEmpty()) throw new IllegalArgumentException("Title cannot be null or empty");

        if (artist == null || artist.isEmpty()) throw new IllegalArgumentException("Artist cannot be null or empty");

        if (releaseYear == null || releaseYear.isEmpty())
            throw new IllegalArgumentException("Release year cannot be null or empty");


        return new Album(id, title, artist, releaseYear, coverURL);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getCoverURL() {
        return coverURL;
    }
}
