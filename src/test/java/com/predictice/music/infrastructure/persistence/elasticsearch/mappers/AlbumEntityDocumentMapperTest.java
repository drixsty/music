package com.predictice.music.infrastructure.persistence.elasticsearch.mappers;

import com.predictice.music.domain.models.Album;
import com.predictice.music.infrastructure.persistence.elasticsearch.models.AlbumDoc;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlbumEntityDocumentMapperTest {
    @Test
    void testToEntity() {
        AlbumDoc albumDoc = new AlbumDoc("1", "Thriller", "Michael Jackson", "1982", "https://example.com/thriller-cover.jpg");

        AlbumEntityDocumentMapper mapper = new AlbumEntityDocumentMapper();

        Album album = mapper.toEntity(albumDoc);

        assertEquals("1", album.getId());
        assertEquals("Thriller", album.getTitle());
        assertEquals("Michael Jackson", album.getArtist());
        assertEquals("1982", album.getReleaseYear());
        assertEquals("https://example.com/thriller-cover.jpg", album.getCoverURL());
    }

    @Test
    void testToModel() {
        Album album = Album.create("1", "Thriller", "Michael Jackson", "1982", "https://example.com/thriller-cover.jpg");

        AlbumEntityDocumentMapper mapper = new AlbumEntityDocumentMapper();

        AlbumDoc albumDoc = mapper.toModel(album);

        assertEquals("1", albumDoc.getId());
        assertEquals("Thriller", albumDoc.getTitle());
        assertEquals("Michael Jackson", albumDoc.getArtist());
        assertEquals("1982", albumDoc.getReleaseYear());
        assertEquals("https://example.com/thriller-cover.jpg", albumDoc.getCoverURL());
    }
}