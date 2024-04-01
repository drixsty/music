package com.predictice.music.presenter.mappers;

import com.predictice.music.domain.models.Album;
import com.predictice.music.presenter.models.AlbumResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlbumEntityResponseMapperTest {
    @Test
    void testToEntity() {
        AlbumResponse albumResponse = new AlbumResponse("1", "Thriller", "Michael Jackson", "1982", "https://example.com/thriller-cover.jpg");

        AlbumEntityResponseMapper mapper = new AlbumEntityResponseMapper();

        Album album = mapper.toEntity(albumResponse);

        assertEquals("1", album.getId());
        assertEquals("Thriller", album.getTitle());
        assertEquals("Michael Jackson", album.getArtist());
        assertEquals("1982", album.getReleaseYear());
        assertEquals("https://example.com/thriller-cover.jpg", album.getCoverURL());
    }

    @Test
    void testToModel() {
        Album album = Album.create("1", "Thriller", "Michael Jackson", "1982", "https://example.com/thriller-cover.jpg");

        AlbumEntityResponseMapper mapper = new AlbumEntityResponseMapper();

        AlbumResponse albumResponse = mapper.toModel(album);

        assertEquals("1", albumResponse.id());
        assertEquals("Thriller", albumResponse.title());
        assertEquals("Michael Jackson", albumResponse.artist());
        assertEquals("1982", albumResponse.releaseYear());
        assertEquals("https://example.com/thriller-cover.jpg", albumResponse.coverURL());
    }
}
