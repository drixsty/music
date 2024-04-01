package com.predictice.music.presenter.endpoints;

import com.predictice.music.domain.services.AlbumService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
 class ReleaseYearControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlbumService albumService;


    @Test
    void testGetReleaseYear() throws Exception {
        List<Map<String, Integer>> counts = Collections.singletonList(Collections.singletonMap("1982", 5));

        when(albumService.countAlbumsByReleaseYear()).thenReturn(counts);

        mockMvc.perform(MockMvcRequestBuilders.get("/release/years"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].1982").value(5));
    }


}
