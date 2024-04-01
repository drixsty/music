package com.predictice.music.infrastructure.persistence.elasticsearch.repository;

import com.predictice.music.infrastructure.persistence.elasticsearch.models.AlbumDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends ElasticsearchRepository<AlbumDoc, String> {
    Page<AlbumDoc> findByAuthorsName(String name, Pageable pageable);

}
