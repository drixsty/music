package com.predictice.music.infrastructure.persistence.elasticsearch.repository;

import com.predictice.music.infrastructure.persistence.elasticsearch.models.AlbumDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AlbumRepository extends ElasticsearchRepository<AlbumDoc, String> {
    Page<AlbumDoc> findByReleaseYear(String releaseYear, Pageable pageable);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"releaseYear\": \"?0\"}}, {\"multi_match\": {\"query\": \"?1\", \"fields\": [\"title\", \"artist\", \"coverURL\"]}}]}}")
    Page<AlbumDoc> searchAlbumsByReleaseYearAndKeyword(String releaseYear, String keyword, Pageable pageable);

    @Query("{\"aggs\": {\"album_count_by_release_year\": {\"terms\": {\"field\": \"releaseYear.keyword\"}}}}")
    List<Map<String, Integer>> countAlbumsByReleaseYear();

}
