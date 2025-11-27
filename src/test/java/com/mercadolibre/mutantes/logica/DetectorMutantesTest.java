package com.mercadolibre.mutantes.logica;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class DetectorMutantesTest {

    private final DetectorMutantes detector = new DetectorMutantes();

    // --- GRUPO 1: MUTANTES (7 Tests) ---
    @Test
    @DisplayName("Mutante: Filas Horizontales")
    void testMutanteHorizontal() {
        String[] adn = {"AAAA", "CCCC", "TCAG", "GGTC"};
        assertTrue(detector.esMutante(adn));
    }

    @Test
    @DisplayName("Mutante: Columnas Verticales")
    void testMutanteVertical() {
        String[] adn = {"ATCG", "ATCG", "ATCG", "ATCG"};
        assertTrue(detector.esMutante(adn));
    }

    @Test
    @DisplayName("Mutante: Diagonal Principal")
    void testMutanteDiagonal() {
        String[] adn = {"AAAA", "GAGG", "TTAT", "AGAA"};
        assertFalse(detector.esMutante(adn));
    }

    @Test
    @DisplayName("Mutante: Diagonal Inversa y Vertical")
    void testMutanteDiagonalInversa() {
        String[] adn = {"ATGT", "CTTC", "TTGG", "TGGA"};
        assertFalse(detector.esMutante(adn));
    }

    @Test
    @DisplayName("Mutante: Ejemplo del PDF")
    void testMutantePdf() {
        String[] adn = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        assertTrue(detector.esMutante(adn));
    }

    @Test
    @DisplayName("Mutante: Matriz Grande 10x10")
    void testMutanteGrande() {
        String[] adn = {
                "ATGCGAATGC", "CAGTGCCAGT", "TTATGTTTAT", "AGAAGGATAA", "CCCCTACCCC",
                "TCACTGTCAC", "ATGCGAATGC", "CAGTGCCAGT", "TTATGTTTAT", "AGAAGGATAA"
        };
        assertTrue(detector.esMutante(adn));
    }

    @Test
    @DisplayName("Mutante: Todo Iguales")
    void testMutanteTodoIgual() {
        String[] adn = {"AAAA", "AAAA", "AAAA", "AAAA"};
        assertTrue(detector.esMutante(adn));
    }

    // --- GRUPO 2: HUMANOS (3 Tests) ---
    @Test
    @DisplayName("Humano: Solo 1 secuencia")
    void testHumanoUnaSecuencia() {
        String[] adn = {"AAAA", "CAGT", "TTAT", "AGAC"};
        assertFalse(detector.esMutante(adn));
    }

    @Test
    @DisplayName("Humano: Sin secuencias")
    void testHumanoSinSecuencias() {
        String[] adn = {"ATGC", "CAGT", "TTAT", "AGAC"};
        assertFalse(detector.esMutante(adn));
    }

    @Test
    @DisplayName("Humano: Matriz 4x4 Limpia")
    void testHumanoSimple() {
        String[] adn = {"ATGC", "CAGT", "TTAT", "AGAC"};
        assertFalse(detector.esMutante(adn));
    }

    // --- GRUPO 3: VALIDACIONES Y ERRORES (6 Tests) ---
    @Test
    @DisplayName("Invalido: Null")
    void testNull() {
        assertFalse(detector.esMutante(null));
    }

    @Test
    @DisplayName("Invalido: Vacio")
    void testVacio() {
        assertFalse(detector.esMutante(new String[]{}));
    }

    @Test
    @DisplayName("Invalido: No Cuadrada (NxM)")
    void testNoCuadrada() {
        String[] adn = {"ABC", "DEF"};
        assertFalse(detector.esMutante(adn));
    }

    @Test
    @DisplayName("Invalido: Caracteres Raros")
    void testCaracteresInvalidos() {
        String[] adn = {"ATGX", "CAGT", "TTAT", "AGAA"};
        assertFalse(detector.esMutante(adn));
    }

    @Test
    @DisplayName("Invalido: Matriz Pequeña")
    void testMatrizPequena() {
        String[] adn = {"AAA", "AAA", "AAA"};
        assertFalse(detector.esMutante(adn));
    }

    @Test
    @DisplayName("Invalido: Fila Null")
    void testFilaNull() {
        String[] adn = {"AAAA", null, "TTAT", "AGAA"};
        assertFalse(detector.esMutante(adn));
    }
}