package com.predictice.music.domain.mapper;

import java.util.List;

public interface ModelEntityMapper<Entity, Model> {
    public Entity toEntity(Model model);

    public Model toModel(Entity entity);

    default List<Entity> listOfModelsToEntities(List<Model> models) {
        return models.stream().map(this::toEntity).toList();
    }

    default List<Model> listOfEntitiesToModels(List<Entity> entities) {
        return entities.stream().map(this::toModel).toList();
    }
}
