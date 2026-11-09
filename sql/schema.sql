-- Schéma relationnel MySQL : Application N-tier de Gestion Universitaire
CREATE DATABASE IF NOT EXISTS uasz_gestion_universitaire CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE uasz_gestion_universitaire;

CREATE TABLE IF NOT EXISTS etudiants (
    id INT AUTO_INCREMENT PRIMARY KEY,
    matricule VARCHAR(20) NOT NULL UNIQUE,
    nom VARCHAR(80) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    filiere VARCHAR(100) NOT NULL DEFAULT 'L3 Ingénierie Informatique',
    date_inscription TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS cours (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code_ue VARCHAR(20) NOT NULL UNIQUE,
    intitule VARCHAR(150) NOT NULL,
    credits INT NOT NULL CHECK (credits > 0),
    semestre VARCHAR(10) NOT NULL DEFAULT 'S6'
);

CREATE TABLE IF NOT EXISTS notes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    etudiant_id INT NOT NULL,
    cours_id INT NOT NULL,
    note_devoir DECIMAL(4,2) NOT NULL CHECK (note_devoir BETWEEN 0 AND 20),
    note_examen DECIMAL(4,2) NOT NULL CHECK (note_examen BETWEEN 0 AND 20),
    CONSTRAINT fk_note_etudiant FOREIGN KEY (etudiant_id) REFERENCES etudiants(id) ON DELETE CASCADE,
    CONSTRAINT fk_note_cours FOREIGN KEY (cours_id) REFERENCES cours(id) ON DELETE CASCADE,
    UNIQUE KEY uq_etudiant_cours (etudiant_id, cours_id)
);

INSERT INTO etudiants (matricule, nom, prenom, email, filiere) VALUES
('2023-UASZ-001', 'SYLL', 'Collé', 'collesyll9@gmail.com', 'L3 Ingénierie Informatique'),
('2023-UASZ-002', 'FAYE', 'Ndeye Thiamba', 'thiamba.faye@zig.univ.sn', 'L3 Ingénierie Informatique');

INSERT INTO cours (code_ue, intitule, credits, semestre) VALUES
('INF361', 'Data Engineering & Entrepôts de Données', 6, 'S6'),
('INF362', 'Architecture N-tier & Microservices', 6, 'S6'),
('INF363', 'Machine Learning & Intelligence Artificielle', 6, 'S6');
