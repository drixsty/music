package com.predictice.music.domain.usecases;

import com.predictice.music.domain.entities.Album;
import com.predictice.music.domain.services.AlbumService;
import org.springframework.stereotype.Component;

@Component
public class FindAlbumById {
    AlbumService service;

    public FindAlbumById(AlbumService service) {
        this.service = service;
    }

    Album execute(String id) {
        return service.getAlbumById(id);
    }
}
