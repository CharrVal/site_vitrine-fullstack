# Application Fullstack – Spring Boot & Angular

## 🧭 Présentation
Ce projet est une application **fullstack** développée dans un objectif de démonstration de compétences techniques.

Il met en œuvre :
- un **backend Java Spring Boot** exposant une API REST
- un **frontend Angular** consommant cette API
- une architecture claire et maintenable

Ce projet sert de site vitrine pour la société Liberty Céramique mais aussi de **vitrine technique** à destination de recruteurs et de collaborateurs techniques.

---

## 🛠️ Stack technique

### Backend
- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Base de données : PostgreSQL

### Frontend
- Angular
- TypeScript
- HTML / SCSS
- RxJS

### Outils & bonnes pratiques
- Git / GitHub
- Architecture REST
- Séparation des responsabilités
- Convention de nommage claire

---

## 🗂️ Structure du projet

site_vitrine-fullstack/
- backend/ # API REST Spring Boot
  - src/
  - pom.xml
  - README.md
- frontend/ # Application Angular
  - src/
  - angular.json
  - README.md
- README.md # Documentation globale

---

## ▶️ Lancer le projet
Le projet peut être lancé de deux manières :
- **avec Docker (recommandé)** : environnement complet et reproductible
- **sans Docker** : lancement manuel du backend et du frontend

---

## 🐳 Lancer le projet avec Docker (recommandé)

L’ensemble de l’application (backend, frontend et base de données PostgreSQL) est orchestré via **Docker Compose**.

### Prérequis
- Docker
- Docker Compose

### Démarrage
À la racine du projet :

```bash
docker compose up --build



