package sn.uasz.collesyll.model;

/**
 * Modèle métier représentant un étudiant et le calcul de sa moyenne pondérée.
 */
public class NoteEvaluation {
    private final String matriculeEtudiant;
    private final String codeUe;
    private final double noteDevoir;
    private final double noteExamen;
    private final int credits;

    public NoteEvaluation(String matriculeEtudiant, String codeUe, double noteDevoir, double noteExamen, int credits) {
        if (noteDevoir < 0 || noteDevoir > 20 || noteExamen < 0 || noteExamen > 20) {
            throw new IllegalArgumentException("Les notes doivent être comprises entre 0 et 20.");
        }
        if (credits <= 0) {
            throw new IllegalArgumentException("Le nombre de crédits doit être strictement positif.");
        }
        this.matriculeEtudiant = matriculeEtudiant;
        this.codeUe = codeUe;
        this.noteDevoir = noteDevoir;
        this.noteExamen = noteExamen;
        this.credits = credits;
    }

    /**
     * Calcule la moyenne finale de l'UE (40% contrôle continu / devoir + 60% examen).
     */
    public double calculerMoyenneFinale() {
        return Math.round((this.noteDevoir * 0.40 + this.noteExamen * 0.60) * 100.0) / 100.0;
    }

    public boolean estValidee() {
        return calculerMoyenneFinale() >= 10.0;
    }

    public String determinerMention() {
        double m = calculerMoyenneFinale();
        if (m >= 16.0) return "Très Bien";
        if (m >= 14.0) return "Bien";
        if (m >= 12.0) return "Assez Bien";
        if (m >= 10.0) return "Passable";
        return "Ajourné";
    }

    public String getMatriculeEtudiant() {
        return matriculeEtudiant;
    }

    public String getCodeUe() {
        return codeUe;
    }

    public int getCredits() {
        return credits;
    }
}
