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
- Spring Security
- Authentification JWT
- Gestion de la base avec Flyway pour les migrations
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
- Sécurité des API

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
docker compose up
```
---

## 🐳 Lancer le projet sans Docker

Backend (Spring Boot)
```bash
cd backend
./mvnw spring-boot:run
```
L’API est accessible sur : http://localhost:8080

Frontend (Angular)
```bash
cd frontend
npm install
ng serve --port 4000
```
L’application est accessible sur : http://localhost:4000/accueil

---

## 🔐 Sécurité & authentification

L’application implémente une authentification basée sur JWT (JSON Web Token).

Principe

- L’utilisateur s’authentifie via une route dédiée (/auth)

- Le backend génère un token JWT signé

- Le frontend stocke le token et l’envoie dans les requêtes HTTP via l’en-tête :
```makefile
Authorization: Bearer <token>
```
- Les routes protégées nécessitent un token valide

Backend

- Spring Security

- Filtre JWT personnalisé

- Protection des endpoints sensibles

- Gestion des rôles et autorisations
  
- Flyway : migrations versionnées de la base de données, création automatique des tables et scripts initiaux

Frontend

- Interceptor HTTP Angular

- Ajout automatique du token aux requêtes

- Gestion de l’état d’authentification

---

## 🔐 Notes importantes

Les variables sensibles (base de données) sont définies via des variables d’environnement

L’utilisation de Docker garantit un environnement identique entre développement et démonstration

Le mode Docker est recommandé pour l’évaluation du projet
