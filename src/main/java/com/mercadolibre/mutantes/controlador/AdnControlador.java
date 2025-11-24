package com.mercadolibre.mutantes.controlador;

import com.mercadolibre.mutantes.servicio.AdnServicio;
import com.mercadolibre.mutantes.servicio.EstadisticasDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
public class AdnControlador {
    @Autowired
    private AdnServicio servicio;

    @PostMapping("/mutant")
    public ResponseEntity<Void> detectarMutante(@RequestBody Map<String, String[]> peticion) {
        String[] adn = peticion.get("dna");
        boolean esMutante = servicio.analizarAdn(adn);
        if (esMutante) {
            return ResponseEntity.ok().build(); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
    @GetMapping("/stats") // Endpoint de estadísticas
    public ResponseEntity<EstadisticasDto> obtenerEstadisticas() {
        return ResponseEntity.ok(servicio.obtenerEstadisticas());
    }
}
