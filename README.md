# Application N-tier — Gestion Universitaire (Jakarta EE / Servlets / MySQL / Tomcat)

**Auteure** : Collé Syll — Licence 3 Ingénierie Informatique (UASZ)  
**Technologies** : `Java 21`, `Jakarta EE`, `Servlets`, `MySQL`, `Apache Tomcat`, `Maven`

## Architecture en 3 couches (N-tier)
1. **Couche Présentation / Contrôleur (`sn.uasz.collesyll.servlet`)** : Servlets Jakarta EE avec gestion des sessions HTTP (`HttpSession`) et exposition JSON/Web.
2. **Couche Métier (`sn.uasz.collesyll.model`)** : Calcul des moyennes pondérées (40 % devoir / 60 % examen), validation d'UE et attribution des mentions académiques.
3. **Couche Données (`sql/schema.sql`)** : Base relationnelle MySQL (`etudiants`, `cours`, `notes`).

## Compilation et Tests
```bash
mvn clean test package
```
