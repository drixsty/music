package com.predictice.music.domain.services;

import com.predictice.music.domain.entities.Album;

import java.util.List;

public interface AlbumService {
    public Album getAlbumById(String id);

    public List<Album> findAlbum();

    public void createAlbumIndex(final Album album);

    public void deleteAlbumIndex(final Album album);

}
