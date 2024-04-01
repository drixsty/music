package com.predictice.music.presenter.endpoints;

import com.predictice.music.domain.services.AlbumService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("release/years")
public class ReleaseYearController {
    private final AlbumService albumService;

    public ReleaseYearController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping()
    public List<Map<String, Integer>> getReleaseYear() {
        return albumService.countAlbumsByReleaseYear();
    }
}
