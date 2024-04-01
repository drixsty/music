package com.predictice.music.domain.services;

import com.predictice.music.domain.models.Album;

import java.util.List;

public interface AlbumService {
    public Album getAlbumById(String id);

    public List<Album> findAlbum();

    public void createAlbumIndex(Album album);

    public void deleteAlbumIndexById(String id);

}
