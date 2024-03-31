package com.predictice.music.data.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "album")
public class AlbumDoc {
    @Id
    private String id;
    private String title;
    private String artist;
    private String releaseYear;
    private String coverURL;

    public AlbumDoc(String id, String title, String artist, String releaseYear, String coverURL) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.coverURL = coverURL;
    }

    public String getId() {
        return id;
    }

    public AlbumDoc setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AlbumDoc setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getArtist() {
        return artist;
    }

    public AlbumDoc setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public AlbumDoc setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public AlbumDoc setCoverURL(String coverURL) {
        this.coverURL = coverURL;
        return this;
    }
}
