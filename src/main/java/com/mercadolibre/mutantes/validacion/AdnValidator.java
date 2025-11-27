package com.mercadolibre.mutantes.validacion;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AdnValidator implements ConstraintValidator<ValidAdn, String[]> {

    @Override
    public boolean isValid(String[] adn, ConstraintValidatorContext context) {
        if (adn == null || adn.length == 0) return false;

        int n = adn.length;
        String validChars = "[ATCG]+";

        for (String fila : adn) {
            // Validar que no sea nula, que cumpla el tamaño N y solo tenga letras válidas
            if (fila == null || fila.length() != n || !fila.matches(validChars)) {
                return false;
            }
        }
        return true;
    }
}
