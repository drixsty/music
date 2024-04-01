package com.predictice.music.infrastructure.persistence.elasticsearch.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "album")
public class AlbumDoc {
    @Id
    private String id;
    @Field(type = FieldType.Keyword, name = "title")
    private String title;
    @Field(type = FieldType.Keyword, name = "artist")

    private String artist;
    @Field(type = FieldType.Keyword, name = "releaseYear")

    private String releaseYear;
    @Field(type = FieldType.Keyword, name = "coverURL")

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
