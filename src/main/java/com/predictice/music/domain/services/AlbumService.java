package com.predictice.music.domain.services;

import com.predictice.music.domain.models.Album;

import java.util.List;
import java.util.Map;

public interface AlbumService {
    public Album getAlbumById(String id);

    public List<Album> filterAlbumsByReleaseYearAndKeyword(String releaseYear, String keyword, int page, int size);

    public void createAlbumIndex(Album album);

    public void deleteAlbumIndexById(String id);

    List<Map<String, Integer>> countAlbumsByReleaseYear();

}
