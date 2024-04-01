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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.Mockito.when;

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

        mockMvc.perform(MockMvcRequestBuilders.get("/albums/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Thriller"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist").value("Michael Jackson"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.releaseYear").value("1982"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.coverURL").value("https://example.com/thriller-cover.jpg"));
    }

    @Test
    void testSearchAlbumsByReleaseYearAndKeyword() throws Exception {
        Album album = Album.create("1", "Thriller", "Michael Jackson", "1982", "https://example.com/thriller-cover.jpg");
        AlbumResponse albumResponse = new AlbumResponse("1", "Thriller", "Michael Jackson", "1982", "https://example.com/thriller-cover.jpg");

        when(albumService.filterAlbumsByReleaseYearAndKeyword("1982", "Thriller", PageRequest.of(1, 1))).thenReturn(Collections.singletonList(album));

        when(mapper.listOfEntitiesToModels(Collections.singletonList(album))).thenReturn(Collections.singletonList(albumResponse));

        mockMvc.perform(MockMvcRequestBuilders.get("/albums/search")
                        .param("releaseYear", "1982")
                        .param("keyword", "Thriller")
                        .param("page", "1")
                        .param("size", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Thriller"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].artist").value("Michael Jackson"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].releaseYear").value("1982"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].coverURL").value("https://example.com/thriller-cover.jpg"));
    }
}