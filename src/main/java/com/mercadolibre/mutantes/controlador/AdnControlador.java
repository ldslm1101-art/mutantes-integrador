package com.mercadolibre.mutantes.controlador;

import com.mercadolibre.mutantes.dto.AdnPedido;
import com.mercadolibre.mutantes.servicio.AdnServicio;
import com.mercadolibre.mutantes.servicio.EstadisticasDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AdnControlador {

    @Autowired
    private AdnServicio servicio;

    // --- Endpoint 1: Detectar Mutante (POST) ---
    @PostMapping("/mutant")
    public ResponseEntity<Void> detectarMutante(@RequestBody AdnPedido peticion) {
        String[] adn = peticion.getAdn();

        // Validación básica
        if (adn == null || adn.length == 0) {
            return ResponseEntity.badRequest().build();
        }

        boolean esMutante = servicio.analizarAdn(adn);
        if (esMutante) {
            return ResponseEntity.ok().build(); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 Forbidden
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<EstadisticasDto> obtenerEstadisticas() {
        return ResponseEntity.ok(servicio.obtenerEstadisticas());
    }
}
