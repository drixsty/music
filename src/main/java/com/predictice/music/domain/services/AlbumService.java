package com.predictice.music.domain.services;

import com.predictice.music.domain.models.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface AlbumService {
    public Album getAlbumById(String id);

    public Page<Album> getAllAlbum(Pageable pageable);

    public Page<Album> filterAlbumsByReleaseYearAndKeyword(String releaseYear, String keyword, Pageable pageable);

    public void createAlbumIndex(Album album);

    public void deleteAlbumIndexById(String id);

    List<Map<String, Integer>> countAlbumsByReleaseYear();

}
