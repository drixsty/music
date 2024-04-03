package com.predictice.music.domain.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AlbumTest {
    @Test
    void testCreateValidAlbum() {
        Album album = Album.create("1", "Thriller", "Michael Jackson", "1982", "https://example.com/thriller-cover.jpg");

        assertEquals("1", album.getId());
        assertEquals("Thriller", album.getTitle());
        assertEquals("Michael Jackson", album.getArtist());
        assertEquals("1982", album.getReleaseYear());
        assertEquals("https://example.com/thriller-cover.jpg", album.getCoverURL());
    }

    @Test
    void testCreateWithEmptyArtist() {
        assertThrows(IllegalArgumentException.class, () -> {
            Album.create("1", "Thriller", "", "1982", "https://example.com/thriller-cover.jpg");
        });
    }


}
