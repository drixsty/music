package com.predictice.music.infra.album.services;

import com.predictice.music.data.mappers.AlbumEntityDocumentMapper;
import com.predictice.music.data.repository.AlbumRepository;
import com.predictice.music.domain.entities.Album;
import com.predictice.music.domain.services.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    AlbumRepository albumRepository;

    AlbumEntityDocumentMapper mapper;

    public AlbumServiceImpl(AlbumRepository albumRepository,AlbumEntityDocumentMapper mapper) {
        this.albumRepository = albumRepository;
        this.mapper = mapper;
    }

    @Override
    public Album getAlbumById(String id) {
        return null;
    }

    @Override
    public List<Album> findAlbum() {
        return null;
    }

    @Override
    public void createAlbumIndex(Album album) {

    }

    @Override
    public void deleteAlbumIndex(Album album) {

    }
}
