package com.predictice.music.presenter.services;

import com.predictice.music.infrastructure.persistence.elasticsearch.mappers.AlbumEntityDocumentMapper;
import com.predictice.music.infrastructure.persistence.elasticsearch.models.AlbumDoc;
import com.predictice.music.infrastructure.persistence.elasticsearch.repository.AlbumRepository;
import com.predictice.music.domain.models.Album;
import com.predictice.music.domain.services.AlbumService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    private final AlbumEntityDocumentMapper mapper;

    public AlbumServiceImpl(AlbumRepository albumRepository, AlbumEntityDocumentMapper mapper) {
        this.albumRepository = albumRepository;
        this.mapper = mapper;
    }

    @Override
    public Album getAlbumById(String id) {
        return mapper.toEntity(albumRepository.findById(id).orElseThrow());
    }

    @Override
    public List<Album> findAlbum() {
        var albums = new ArrayList<AlbumDoc>();
        albumRepository.findAll().forEach(albums::add);
        return mapper.listOfModelsToEntities(albums);
    }

    @Override
    public void createAlbumIndex(Album album) {
        albumRepository.save(mapper.toModel(album));
    }

    @Override
    public void deleteAlbumIndexById(String id) {
        albumRepository.deleteById(id);
    }
}
