### Frontend – Application Angular (Site Vitrine)

## 🧭 Présentation

Ce module correspond au frontend de l’application Site Vitrine Fullstack.
Il s’agit d’une application Angular consommant une API REST sécurisée développée avec Spring Boot.
Le frontend est responsable de :
- l’interface utilisateur
- la navigation
- l’authentification utilisateur
- la communication avec l’API backend
- l’affichage des données métier

---

## 🛠️ Stack technique
- Angular
- TypeScript
- HTML / SCSS
- RxJS
- Angular Router
- HTTP Client

---

## 🧱 Architecture du projet

- frontend/
  - src/
    - app/
      - composants/
        - footer/
        - guards/
        - interceptor/
        - navbar/
        - pages/
          - Contient chaque pages de l'applicaiton         
      - app.routes.ts
    - assets/
  - Dockerfile
  - nginx.conf
  - angular.json
  - package.json
  - README.md

---

## ▶️ Lancer le frontend
Option 1 – Avec Docker (recommandé)
Le frontend est conçu pour être lancé via Docker Compose depuis la racine du projet.
```
docker compose up frontend
```
L’application est accessible sur : http://localhost:4000

Option 2 – Sans Docker (mode développement)
Prérequis
- Node.js
- npm
- Angular CLI

Installation
```
npm install
```

Lancement
```
ng serve --port 4000
```
Application disponible sur : http://localhost:4000

---

## 🔐 Authentification & sécurité

Le frontend implémente une authentification basée sur JWT.

Fonctionnement
1. L’utilisateur s’authentifie via une page dédiée
2. Le backend retourne un token JWT
3. Le token est stocké côté frontend
4. Les requêtes HTTP vers l’API incluent automatiquement le token

Implémentation Angular
- HTTP Interceptor
- Ajout automatique de l’en-tête :
```
Authorization: Bearer <token>
```
- Guards de routes
- Protection des pages nécessitant une authentification
- Service d’authentification
- Gestion du login / logout
- Gestion de l’état utilisateur

---

## 🌐 Communication avec le backend

Les appels API sont centralisés via des services Angular.
- Utilisation de HttpClient
- Séparation claire entre UI et logique métier
- Gestion des erreurs HTTP
- URL de l’API configurable selon l’environnement

---

## 🎨 Interface utilisateur

- Architecture modulaire
- Composants réutilisables
- Styles organisés (SCSS)
- Navigation fluide via Angular Router

---

## 📌 Bonnes pratiques appliquées

- Séparation des responsabilités
- Composants simples et testables
- Services dédiés aux appels API
- Interceptors et guards centralisés
- Code lisible et évolutif

---

## ✅ Objectif du frontend

Ce frontend a pour objectif de démontrer :
- la maîtrise d’Angular et TypeScript
- l’intégration avec une API sécurisée
- la gestion d’une authentification JWT côté client
- une architecture claire et professionnelle
