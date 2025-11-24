package com.mercadolibre.mutantes.logica;

import org.springframework.stereotype.Component;

@Component
public class DetectorMutantes {
    private static final int SECUENCIA_MUTANTE = 4;

    public boolean esMutante(String[] adn) {
        if (adn == null || adn.length == 0) return false;

        int n = adn.length;
        int contadorSecuencias = 0;

        char[][] matriz = new char[n][n];
        for (int i = 0; i < n; i++) {
            matriz[i] = adn[i].toCharArray();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (verificarHorizontal(matriz, i, j, n) ||
                        verificarVertical(matriz, i, j, n) ||
                        verificarDiagonal(matriz, i, j, n) ||
                        verificarDiagonalInversa(matriz, i, j, n)) {
                    contadorSecuencias++;
                }
                if (contadorSecuencias > 1) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean verificarHorizontal(char[][] matriz, int fila, int col, int n) {
        if (col + SECUENCIA_MUTANTE > n) return false;
        char base = matriz[fila][col];
        for (int k = 1; k < SECUENCIA_MUTANTE; k++) {
            if (matriz[fila][col + k] != base) return false;
        }
        return true;
    }
    private boolean verificarVertical(char[][] matriz, int fila, int col, int n) {
        if (fila + SECUENCIA_MUTANTE > n) return false;
        char base = matriz[fila][col];
        for (int k = 1; k < SECUENCIA_MUTANTE; k++) {
            if (matriz[fila + k][col] != base) return false;
        }
        return true;
    }
    private boolean verificarDiagonal(char[][] matriz, int fila, int col, int n) {
        if (fila + SECUENCIA_MUTANTE > n || col + SECUENCIA_MUTANTE > n) return false;
        char base = matriz[fila][col];
        for (int k = 1; k < SECUENCIA_MUTANTE; k++) {
            if (matriz[fila + k][col + k] != base) return false;
        }
        return true;
    }
    private boolean verificarDiagonalInversa(char[][] matriz, int fila, int col, int n) {
        if (fila + SECUENCIA_MUTANTE > n || col - SECUENCIA_MUTANTE + 1 < 0) return false;
        char base = matriz[fila][col];
        for (int k = 1; k < SECUENCIA_MUTANTE; k++) {
            if (matriz[fila + k][col - k] != base) return false;
        }
        return true;
    }
}
