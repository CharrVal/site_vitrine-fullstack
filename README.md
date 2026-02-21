# Application Fullstack – Spring Boot & Angular

## 🧭 Présentation
Ce projet est une application fullstack développée dans un objectif de démonstration de compétences techniques.

Il s’agit :
- d’un site vitrine pour la société Liberty Céramique
- d’une vitrine technique destinée à des recruteurs et collaborateurs techniques

L’application repose sur une architecture moderne, sécurisée et maintenable, avec une séparation claire entre le backend et le frontend..

---

## 🛠️ Stack technique

### Backend
- Java 21
- Spring Boot
- Spring Web (API REST)
- Spring Data JPA
- Spring Security
- Authentification JWT
- Flyway (gestion des migrations de base de données)
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
- Gestion sécurisée des variables sensibles
- Conteneurisation avec Docker

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
- **sans Docker** : lancement manuel du backend et du frontend (mode développement)

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
Une fois les conteneurs démarrés :
- Backend : http://localhost:8080
- Frontend : http://localhost:4000

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

1. L’utilisateur s’authentifie via une route dédiée (/auth)
2. Le backend génère un token JWT signé
3. Le frontend stocke le token
4. Le toekn est envoyé dans chaque requête protégée via l’en-tête HTTP :
```makefile
Authorization: Bearer <token>
```
5. Les routes protégées nécessitent un token valide

---

Backend - Sécurité
- Spring Security
- Filtre JWT personnalisé
- Protection des endpoints sensibles
- Gestion des rôles et autorisations
- Flyway :
  - migrations versionnées
  - création automatique des tables
  - scripts d'initialisation

---

Frontend - Sécurité
- Interceptor HTTP Angular
- Ajout automatique du token aux requêtes
- Gestion de l’état d’authentification

---

## 🔐 Configuration & variables d’environnement
Les informations sensibles (base de données, secret JWT) ne sont pas stockées en dur.
Elles sont définies via :
- des variables d’environnement
- un fichier .env (utilisé par Docker Compose)
- des valeurs par défaut pour le mode développement local

Cette approche garantit :
- la sécurité des secrets
- une configuration portable
- un environnement cohérent entre développement et démonstration

---

## 📌 Notes importantes

- L’utilisation de Docker est fortement recommandée pour tester le projet
- Docker garantit un environnement identique entre les machines
- Le projet est conçu pour être :
  - lisible
  - maintenable
  - facilement déployable

---

## ✅ Conclusion

Ce projet met en avant :
- Une architecture fullstack moderne
- Des pratiques professionnelles (Docker, Flyway, JWT, env vars)
- Une approche orientée qualité et maintenabilité
