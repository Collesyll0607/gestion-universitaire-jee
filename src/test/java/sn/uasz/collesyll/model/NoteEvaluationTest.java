package sn.uasz.collesyll.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NoteEvaluationTest {

    @Test
    void testCalculMoyenneEtValidation() {
        NoteEvaluation eval = new NoteEvaluation("2023-UASZ-001", "INF361", 15.0, 17.0, 6);
        // 15 * 0.4 + 17 * 0.6 = 6.0 + 10.2 = 16.2
        assertEquals(16.2, eval.calculerMoyenneFinale(), 0.001);
        assertTrue(eval.estValidee());
        assertEquals("Très Bien", eval.determinerMention());
    }

    @Test
    void testNoteInvalideDeclencheException() {
        assertThrows(IllegalArgumentException.class, () ->
                new NoteEvaluation("2023-UASZ-001", "INF361", -2.0, 14.0, 6)
        );
    }
}
