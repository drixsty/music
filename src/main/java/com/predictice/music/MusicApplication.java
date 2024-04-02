package com.predictice.music;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.predictice.music.infrastructure.persistence.elasticsearch.models.AlbumDoc;
import com.predictice.music.infrastructure.persistence.elasticsearch.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.List;

@SpringBootApplication
public class MusicApplication implements CommandLineRunner {
    @Value("${music.elasticsearch.init.data.path}")
    private String initDataPath;

    private final AlbumRepository albumRepository;

    public MusicApplication(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        if (initDataPath != null && !initDataPath.isEmpty()) {
            List<AlbumDoc> albums = objectMapper.readValue(new File(initDataPath), new TypeReference<List<AlbumDoc>>() {});
            albumRepository.saveAll(albums);
        }

    }

    public static void main(String[] args) {
        SpringApplication.run(MusicApplication.class, args);
    }

}
