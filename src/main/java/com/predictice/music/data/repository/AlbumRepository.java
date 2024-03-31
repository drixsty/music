package com.predictice.music.data.repository;

import com.predictice.music.data.models.AlbumDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public class AlbumRepository implements ElasticsearchRepository<AlbumDoc, String> {
    Page<AlbumDoc> findByAuthorsName(String name, Pageable pageable){

    }

}
