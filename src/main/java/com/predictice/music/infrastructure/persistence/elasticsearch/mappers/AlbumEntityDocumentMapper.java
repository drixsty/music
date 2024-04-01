package com.predictice.music.infrastructure.persistence.elasticsearch.mappers;

import com.predictice.music.infrastructure.persistence.elasticsearch.models.AlbumDoc;
import com.predictice.music.domain.models.Album;
import com.predictice.music.domain.mapper.ModelEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class AlbumEntityDocumentMapper implements ModelEntityMapper<Album, AlbumDoc> {
    @Override
    public Album toEntity(AlbumDoc albumDoc) {
        return Album.create(albumDoc.getId(), albumDoc.getTitle(), albumDoc.getArtist(), albumDoc.getReleaseYear(), albumDoc.getCoverURL());
    }

    @Override
    public AlbumDoc toModel(Album album) {
        return new AlbumDoc(album.getId(), album.getTitle(), album.getArtist(), album.getReleaseYear(), album.getCoverURL());
    }
}
