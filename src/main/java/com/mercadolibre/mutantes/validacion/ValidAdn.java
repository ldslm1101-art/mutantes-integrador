package com.mercadolibre.mutantes.validacion;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AdnValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAdn {
    String message() default "La secuencia de ADN no es válida (debe ser NxN y solo contener A, T, C, G)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}