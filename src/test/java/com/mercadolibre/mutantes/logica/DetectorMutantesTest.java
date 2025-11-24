package com.mercadolibre.mutantes.logica;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DetectorMutantesTest {
    private final DetectorMutantes detector = new DetectorMutantes();

    @Test
    void testEsMutante_Horizontal() {
        String[] adn = {
                "AAAA",
                "CCCC",
                "TCAG",
                "GGTC"
        };
        assertTrue(detector.esMutante(adn), "Debería ser mutante por filas horizontales");
    }

    @Test
    void testEsMutante_Vertical() {
        String[] adn = {
                "ATCG",
                "ATCG",
                "ATCG",
                "ATCG"  // A, T, C, G se repiten verticalmente 4 veces
        };
        assertTrue(detector.esMutante(adn), "Debería ser mutante por columnas verticales");
    }


    @Test
    void testEsMutante_Diagonal() {
        String[] adn = {
                "ATCG",
                "GAGG",
                "TTAT",
                "AGA A" // Diagonal A-A-A-A desde (0,0)
                // Nota: necesitas al menos otra secuencia para ser mutante.
                // Agreguemos complejidad para asegurar >1 secuencia
        };
        // Ejemplo del PDF que es mutante:
        String[] adnMutantePdf = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        assertTrue(detector.esMutante(adnMutantePdf), "El ejemplo del PDF debe ser mutante");
    }

    @Test
    void testEsHumano_NoMutante() {
        String[] adn = {
                "AAAT",
                "AACC",
                "TTAC",
                "GGTC"
        };
        assertFalse(detector.esMutante(adn), "No debería detectar mutante (solo hay una secuencia o ninguna)");
    }

    @Test
    void testArrayVacioONulo() {
        assertFalse(detector.esMutante(null), "Null debe retornar false");
        assertFalse(detector.esMutante(new String[]{}), "Array vacío debe retornar false");
    }
}