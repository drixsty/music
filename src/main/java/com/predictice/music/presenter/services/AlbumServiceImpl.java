package com.predictice.music.presenter.services;

import com.predictice.music.infrastructure.persistence.elasticsearch.mappers.AlbumEntityDocumentMapper;
import com.predictice.music.infrastructure.persistence.elasticsearch.models.AlbumDoc;
import com.predictice.music.infrastructure.persistence.elasticsearch.repository.AlbumRepository;
import com.predictice.music.domain.models.Album;
import com.predictice.music.domain.services.AlbumService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public List<Album> getAllAlbum() {
        List<AlbumDoc> listOfAlbums = new ArrayList<>();
        albumRepository.findAll().forEach(listOfAlbums::add);
        return mapper.listOfModelsToEntities(listOfAlbums);
    }

    @Override
    public List<Album> filterAlbumsByReleaseYearAndKeyword(String releaseYear, String keyword, int page, int size) {
        var albums = albumRepository.searchAlbumsByReleaseYearAndKeyword(releaseYear, keyword, PageRequest.of(page, size));
        return mapper.listOfModelsToEntities(albums.toList());
    }

    @Override
    public void createAlbumIndex(Album album) {
        albumRepository.save(mapper.toModel(album));
    }

    @Override
    public void deleteAlbumIndexById(String id) {
        albumRepository.deleteById(id);
    }

    @Override
    public List<Map<String, Integer>> countAlbumsByReleaseYear() {
        return albumRepository.countAlbumsByReleaseYear();
    }
}
