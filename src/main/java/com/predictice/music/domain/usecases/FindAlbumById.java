package com.predictice.music.domain.usecases;

import com.predictice.music.domain.models.Album;
import com.predictice.music.domain.services.AlbumService;
import org.springframework.stereotype.Component;

@Component
public class FindAlbumById {
    private final AlbumService service;

    public FindAlbumById(AlbumService service) {
        this.service = service;
    }

    public  Album execute(String id) {
        return service.getAlbumById(id);
    }
}
