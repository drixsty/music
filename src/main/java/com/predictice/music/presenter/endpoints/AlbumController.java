package com.predictice.music.presenter.endpoints;

import com.predictice.music.domain.models.Album;
import com.predictice.music.domain.usecases.FindAlbumById;
import com.predictice.music.presenter.mappers.AlbumModelEntityMapper;
import com.predictice.music.presenter.models.response.AlbumResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("albums")
public class AlbumController {
    private final FindAlbumById findAlbumById;
    private final AlbumModelEntityMapper mapper;

    public AlbumController(FindAlbumById findAlbumById, AlbumModelEntityMapper mapper) {
        this.findAlbumById = findAlbumById;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public AlbumResponse getAlbum(@PathVariable String id) {
        Album album = findAlbumById.execute(id);
        return mapper.toModel(album);
    }
}
