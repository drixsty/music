# MUSIC-API

Cette application Spring Boot permet de rechercher des informations sur des albums de musique à l'aide d'Elasticsearch comme moteur de recherche. Les fonctionnalités principales incluent la recherche textuelle et le filtrage par année de sortie d'album.

## Fonctionnalités

- Recherche textuelle d'albums
- Filtrage d'albums par année de sortie

## Architecture

L'architecture de l'application est basée sur une architecture hexagonale pour garantir la modularité et la maintenabilité du code. Les trois couches principales de l'architecture sont :

1. **Couche Domain (Core Layer)** : Contient les entités et les services métier liés à la gestion des albums de musique. Expose les cas d'utilisation de l'application, agissant comme une interface entre la couche Domain et la couche Presentation.
2. **Couche Infrastructure (Adapters Layer)** : Contient les composants qui interagissent avec des ressources externes telles que Elasticsearch. Comprend les contrôleurs pour exposer les API REST, ainsi que les configurations nécessaires.
3. **Couche Presenter (Infrastructure Layer)** : Contient les contrôleurs pour exposer les API REST, ainsi que les configurations nécessaires.

## Démarrage

Pour démarrer l'application, assurez-vous d'avoir Docker et Docker Compose installés sur votre machine. Suivez ensuite ces étapes :

1. Clonez ce dépôt Git.
2. Accédez au répertoire du projet.
3. Exécutez la commande suivante pour construire et démarrer les conteneurs Docker : 
```docker-compose up --build```

L'application sera accessible à l'adresse : `http://localhost:8080`.

Lors du démarrage, un jeu de données stocké dans `resources/albums_sample.json` est automatiquement chargé à l'aide la ligne de configuration ci-dessous :
`music.elasticsearch.init.data.path=src/main/resources/albums_sample.json` definit dans le fichier `application.properties`.


## Interagir avec les endpoints

L'application expose plusieurs endpoints pour interagir avec les données des albums :

- `/albums` : Récupère une liste paginée de tous les albums disponibles.
- `/albums/{id}` : Récupère les détails d'un album spécifique en spécifiant son ID.
- `/albums/search?releaseYear=2000&keyword=rock` : Recherche des albums en filtrant par année de sortie et mot-clé (releaseYear or keyword).
- `/release/years` : Compte le nombre d'albums par année de sortie.

## Accès à Swagger

L'interface Swagger est disponible à l'adresse suivante : http://localhost:8080/swagger-ui.html


Elle permet de tester les API REST exposées par l'application.

## Technologies utilisées

- Spring Boot
- Elasticsearch
- Docker

## Auteur

kevin TSAGUE  
_Fullstack developper_



