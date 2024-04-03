package com.predictice.music.presenter.endpoints;

import com.predictice.music.domain.models.Album;
import com.predictice.music.domain.services.AlbumService;
import com.predictice.music.presenter.mappers.AlbumEntityResponseMapper;
import com.predictice.music.presenter.models.AlbumResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AlbumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlbumService albumService;

    @MockBean
    private AlbumEntityResponseMapper mapper;

    @Test
    void testGetAlbum() throws Exception {
        Album album = Album.create("1", "Thriller", "Michael Jackson", "1982", "https://example.com/thriller-cover.jpg");
        AlbumResponse albumResponse = new AlbumResponse("1", "Thriller", "Michael Jackson", "1982", "https://example.com/thriller-cover.jpg");

        when(albumService.getAlbumById("1")).thenReturn(album);

        when(mapper.toModel(album)).thenReturn(albumResponse);

        mockMvc.perform(get("/albums/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.title").value("Thriller"))
                .andExpect(jsonPath("$.artist").value("Michael Jackson"))
                .andExpect(jsonPath("$.releaseYear").value("1982"))
                .andExpect(jsonPath("$.coverURL").value("https://example.com/thriller-cover.jpg"));
    }

    @Test
    void testGetAllAlbums() throws Exception {
        List<Album> albums = new ArrayList<>();
        when(albumService.getAllAlbum(any(Pageable.class))).thenReturn(new PageImpl<>(albums));
        when(mapper.toModel(any(Album.class))).thenReturn(new AlbumResponse("1", "Thriller", "Michael Jackson", "1982", "https://example.com/thriller-cover.jpg"));

        mockMvc.perform(get("/albums"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());

        verify(albumService, times(1)).getAllAlbum(any(Pageable.class));
        verify(mapper, times(albums.size())).toModel(any(Album.class));
    }

    @Test
    void testSearchAlbumsByReleaseYearAndKeyword() throws Exception {
        String releaseYear = "2022";
        String keyword = "rock";
        List<Album> albums = new ArrayList<>();
        when(albumService.filterAlbumsByReleaseYearAndKeyword(eq(releaseYear), eq(keyword), any(Pageable.class)))
                .thenReturn(new PageImpl<>(albums));
        when(mapper.toModel(any(Album.class))).thenReturn(new AlbumResponse("1", "Thriller", "Michael Jackson", "1982", "https://example.com/thriller-cover.jpg"));

        mockMvc.perform(get("/albums/search")
                        .param("releaseYear", releaseYear)
                        .param("keyword", keyword))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());

        verify(albumService, times(1)).filterAlbumsByReleaseYearAndKeyword(eq(releaseYear), eq(keyword), any(Pageable.class));
        verify(mapper, times(albums.size())).toModel(any(Album.class));
    }
}