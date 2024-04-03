package com.predictice.music.presenter.services;

import com.predictice.music.infrastructure.persistence.elasticsearch.mappers.AlbumEntityDocumentMapper;
import com.predictice.music.infrastructure.persistence.elasticsearch.repository.AlbumRepository;
import com.predictice.music.domain.models.Album;
import com.predictice.music.domain.services.AlbumService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
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
    public List<Album> getAllAlbum(Pageable pageable) {
        var albums = albumRepository.findAll(pageable);
        return mapper.listOfModelsToEntities(albums.toList());
    }

    @Override
    public List<Album> filterAlbumsByReleaseYearAndKeyword(String releaseYear, String keyword, Pageable pageable) {
        var albums = albumRepository.searchAlbumsByReleaseYearAndKeyword(releaseYear, keyword, pageable);
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
        List<Map<String, Integer>> response = new ArrayList<>();

        var result = albumRepository.findAll();

        Map<String, Long> countByYear = new HashMap<>();
        result.iterator().forEachRemaining(albumDoc ->
                countByYear.merge(albumDoc.getReleaseYear(), 1L, Long::sum)
        );

        countByYear.forEach((releaseYear, count) -> {
            Map<String, Integer> map = new HashMap<>();
            map.put(releaseYear, count.intValue());
            response.add(map);
        });

        return response;
    }
}
