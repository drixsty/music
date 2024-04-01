package com.predictice.music.presenter.mappers;

import com.predictice.music.domain.models.Album;
import com.predictice.music.domain.mapper.ModelEntityMapper;
import com.predictice.music.presenter.models.AlbumResponse;
import org.springframework.stereotype.Component;

@Component
public class AlbumEntityResponseMapper implements ModelEntityMapper<Album, AlbumResponse> {
    @Override
    public Album toEntity(AlbumResponse albumResponse) {
        return Album.create(albumResponse.id(), albumResponse.title(), albumResponse.artist(), albumResponse.releaseYear(), albumResponse.coverURL());
    }

    @Override
    public AlbumResponse toModel(Album album) {
        return new AlbumResponse(album.getId(), album.getTitle(), album.getArtist(), album.getReleaseYear(), album.getCoverURL());
    }
}
