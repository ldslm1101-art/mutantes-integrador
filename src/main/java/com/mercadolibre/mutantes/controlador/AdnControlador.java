package com.mercadolibre.mutantes.controlador;

import com.mercadolibre.mutantes.dto.AdnPedido;
import com.mercadolibre.mutantes.servicio.AdnServicio;
import com.mercadolibre.mutantes.servicio.EstadisticasDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Tag(name = "Detector de Mutantes", description = "Operaciones para analizar ADN y ver estadísticas")
public class AdnControlador {

    @Autowired
    private AdnServicio servicio;

    // --- Endpoint 1: Detectar Mutante (POST) ---
    @Operation(summary = "Detectar si un humano es mutante",
            description = "Analiza una secuencia de ADN para detectar patrones mutantes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Es Mutante"),
            @ApiResponse(responseCode = "403", description = "Es Humano (No Mutante)"),
            @ApiResponse(responseCode = "400", description = "ADN Inválido (formato incorrecto)")
    })
    @PostMapping("/mutant")
    public ResponseEntity<Void> detectarMutante(@Valid @RequestBody AdnPedido peticion) {
        // La validación @Valid ya verificó que el ADN no sea nulo, sea NxN y tenga letras válidas.
        // Si falla, el GlobalExceptionHandler se encarga.

        String[] adn = peticion.getAdn();
        boolean esMutante = servicio.analizarAdn(adn);

        if (esMutante) {
            return ResponseEntity.ok().build(); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 Forbidden
        }
    }

    // --- Endpoint 2: Estadísticas (GET) ---
    // ¡Asegúrate de tener este método aquí!
    @Operation(summary = "Obtener estadísticas", description = "Devuelve el conteo de mutantes, humanos y el ratio.")
    @ApiResponse(responseCode = "200", description = "Estadísticas obtenidas correctamente")
    @GetMapping("/stats")
    public ResponseEntity<EstadisticasDto> obtenerEstadisticas() {
        return ResponseEntity.ok(servicio.obtenerEstadisticas());
    }
}