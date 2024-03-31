package com.predictice.music.infra.album.mappers;

import com.predictice.music.domain.entities.Album;
import com.predictice.music.domain.mapper.ModelEntityMapper;
import com.predictice.music.infra.album.models.AlbumDto;
import org.springframework.stereotype.Component;

@Component
public class AlbumModelEntityMapper implements ModelEntityMapper<Album, AlbumDto> {
    @Override
    public Album toEntity(AlbumDto albumDto) {
        return Album.create(albumDto.id(), albumDto.title(), albumDto.artist(), albumDto.releaseYear(), albumDto.coverURL());
    }

    @Override
    public AlbumDto toDto(Album album) {
        return new AlbumDto(album.getId(), album.getTitle(), album.getArtist(), album.getReleaseYear(), album.getCoverURL());
    }
}
