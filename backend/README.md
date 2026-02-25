### Backend – API Spring Boot (Vitrine)

## 🧭 Présentation
Ce module correspond au backend de l’application Site Vitrine Fullstack.
Il s’agit d’une API REST sécurisée, développée avec Spring Boot, chargée de :
- exposer les données métiers
- gérer l’authentification et la sécurité
- communiquer avec la base de données PostgreSQL
- fournir une API consommée par le frontend Angular

---

## 🛠️ Stack technique
- Java 21
- Spring Boot
- Spring Web (API REST)
- Spring Data JPA
- Spring Security
- Authentification JWT
- Flyway (migrations de base de données)
- PostgreSQL

---

## 🧱 Architecture

Le backend suit une architecture classique et maintenable :
- backend/
  - src/main/java/
    - com.artisan.vitrine
      - config/        
      - controller/
      - dto/    
      - entity/
      - mapper/        
      - repository/
      - security/    
      - service/       
      - VitrineApiApplication.java
  - src/main/resources/
    - db/migration/      
    - application.properties│
- Dockerfile
- pom.xml
- README.md

---

## ▶️ Lancer le backend
Option 1 – Avec Docker (recommandé)
Le backend est conçu pour être lancé via Docker Compose depuis la racine du projet.
```bash
docker compose up backend
```
Le backend sera accessible sur : http://localhost:8080

Les variables sensibles (BDD, JWT) sont injectées via des variables d’environnement.

Option 2 – Sans Docker (mode développement)
Prérequis
- Java 21
- Maven
- PostgreSQL en local

Lancement
```
./mvnw spring-boot:run
```
API disponible sur : http://localhost:8080

---

## 🔐 Sécurité & authentification

L’API est protégée par une authentification JWT (JSON Web Token).
Fonctionnement
1. Authentification via une route dédiée (/auth)
2. Génération d’un token JWT signé
3. Le token doit être transmis dans les requêtes protégées :
```makefile
Authorization: Bearer <token>
```
4. Les endpoints sensibles sont inaccessibles sans token valide

Sécurité implémentée
- Spring Security
- Filtre JWT personnalisé
- Sécurisation des endpoints
- Gestion des rôles et autorisations
- Désactivation de open-in-view pour éviter les accès non contrôlés

---

## 🗄️ Base de données & Flyway

La base de données est gérée avec Flyway :
- migrations versionnées
- scripts SQL reproductibles
- création automatique du schéma au démarrage
- aucune génération automatique via Hibernate (ddl-auto=none)

Les scripts se trouvent dans :
```css
src/main/resources/db/migration
```
---

## 🔐 Configuration & variables d’environnement

Aucune donnée sensible n’est stockée en dur.
Les paramètres suivants sont injectés via des variables d’environnement :
- URL de la base de données
- Identifiants PostgreSQL
- Secret JWT

Exemple de configuration utilisée par l’application :
```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
app.jwt.secret=${JWT_SECRET}
```

Cette approche permet :
- une configuration sécurisée
- une compatibilité Docker / local
- une portabilité maximale

---

## 📌 Bonnes pratiques appliquées

- Séparation des couches (Controller / Service / Repository)
- DTOs et entités clairement distincts
- Sécurité centralisée
- Gestion des erreurs contrôlée
- Code lisible et maintenable
- Configuration externalisée

---

## ✅ Objectif du backend

Ce backend a pour but de démontrer :
- la maîtrise de Spring Boot et de l’écosystème Spring
- la mise en place d’une sécurité JWT
- la gestion propre d’une base de données avec Flyway
- une architecture professionnelle prête pour un déploiement réel
