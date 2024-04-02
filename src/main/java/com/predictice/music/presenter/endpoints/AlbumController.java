package com.predictice.music.presenter.endpoints;

import com.predictice.music.domain.models.Album;
import com.predictice.music.domain.services.AlbumService;
import com.predictice.music.presenter.mappers.AlbumEntityResponseMapper;
import com.predictice.music.presenter.models.AlbumResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("albums")
public class AlbumController {
    private final AlbumService albumService;
    private final AlbumEntityResponseMapper mapper;

    public AlbumController(AlbumService albumService, AlbumEntityResponseMapper mapper) {
        this.albumService = albumService;
        this.mapper = mapper;
    }

    @GetMapping()
    public List<AlbumResponse> getAllAlbums() {
        var albums = albumService.getAllAlbum();
        return mapper.listOfEntitiesToModels(albums);
    }

    @GetMapping("/{id}")
    public AlbumResponse getAlbum(@PathVariable String id) {
        var album = albumService.getAlbumById(id);
        return mapper.toModel(album);
    }


    @GetMapping("/search")
    public List<AlbumResponse> searchAlbumsByReleaseYearAndKeyword(
            @RequestParam(name = "releaseYear") String releaseYear,
            @RequestParam(name = "keyword") String keyword,
            @ParameterObject Pageable pageable
    ) {
        var albums = albumService.filterAlbumsByReleaseYearAndKeyword(releaseYear, keyword, pageable);
        return mapper.listOfEntitiesToModels(albums);
    }
}
