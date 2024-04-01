package com.predictice.music.presenter.services;

import com.predictice.music.domain.models.Album;
import com.predictice.music.infrastructure.persistence.elasticsearch.mappers.AlbumEntityDocumentMapper;
import com.predictice.music.infrastructure.persistence.elasticsearch.models.AlbumDoc;
import com.predictice.music.infrastructure.persistence.elasticsearch.repository.AlbumRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlbumServiceTest {
    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private AlbumEntityDocumentMapper mapper;

    @InjectMocks
    private AlbumServiceImpl albumService;

    @Test
    void testGetAlbumById() {
        AlbumDoc albumDoc = new AlbumDoc("1", "Thriller", "Michael Jackson", "1982", "https://example.com/thriller-cover.jpg");
        when(albumRepository.findById("1")).thenReturn(java.util.Optional.of(albumDoc));

        Album album = Album.create("1", "Thriller", "Michael Jackson", "1982", "https://example.com/thriller-cover.jpg");
        when(mapper.toEntity(albumDoc)).thenReturn(album);

        Album result = albumService.getAlbumById("1");

        assertEquals(album, result);
    }

    @Test
    void testFilterAlbumsByReleaseYearAndKeyword() {
        List<AlbumDoc> albumDocs = Collections.singletonList(
                new AlbumDoc("1", "Thriller", "Michael Jackson", "1982", "https://example.com/thriller-cover.jpg"));
        Page<AlbumDoc> albumPage = new PageImpl<>(albumDocs);
        when(albumRepository.searchAlbumsByReleaseYearAndKeyword("1982", "Thriller", PageRequest.of(0, 10))).thenReturn(albumPage);

        List<Album> albums = Collections.singletonList(
                 Album.create("1", "Thriller", "Michael Jackson", "1982", "https://example.com/thriller-cover.jpg"));
        when(mapper.listOfModelsToEntities(albumDocs)).thenReturn(albums);

        List<Album> result = albumService.filterAlbumsByReleaseYearAndKeyword("1982", "Thriller", 0, 10);

        assertEquals(albums, result);
    }

    @Test
    void testCreateAlbumIndex() {
        Album album =  Album.create("1", "Thriller", "Michael Jackson", "1982", "https://example.com/thriller-cover.jpg");
        AlbumDoc albumDoc = new AlbumDoc("1", "Thriller", "Michael Jackson", "1982", "https://example.com/thriller-cover.jpg");

       when(albumRepository.save(any())).thenReturn(albumDoc);

       when(mapper.toModel(any())).thenReturn(albumDoc);


        albumService.createAlbumIndex(album);

        verify(albumRepository, times(1)).save(albumDoc);
    }

    @Test
    void testDeleteAlbumIndexById() {
        albumService.deleteAlbumIndexById("1");

        verify(albumRepository, times(1)).deleteById("1");
    }


    @Test
    void testCountAlbumsByReleaseYear() {
        List<AlbumDoc> albumDocs = Collections.singletonList(
                new AlbumDoc("1", "Thriller", "Michael Jackson", "1982", "https://example.com/thriller-cover.jpg"));

        List<Map<String, Integer>> counts = Collections.singletonList(
                Collections.singletonMap("1982", 1));
        when(albumRepository.findAll()).thenReturn(albumDocs);

        List<Map<String, Integer>> result = albumService.countAlbumsByReleaseYear();

        assertEquals(counts, result);
    }


}
