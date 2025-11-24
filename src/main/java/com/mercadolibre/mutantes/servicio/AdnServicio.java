package com.mercadolibre.mutantes.servicio;

import com.mercadolibre.mutantes.logica.DetectorMutantes;
import com.mercadolibre.mutantes.modelo.AdnEntidad;
import com.mercadolibre.mutantes.repositorio.AdnRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Optional;

@Service
public class AdnServicio {
    @Autowired
    private DetectorMutantes detector;

    @Autowired
    private AdnRepositorio repositorio;

    public boolean analizarAdn(String[] adn) {
        String adnString = Arrays.toString(adn);

        Optional<AdnEntidad> existente = repositorio.findByAdnSerializado(adnString);
        if (existente.isPresent()) {
            return existente.get().isEsMutante();
        }
        boolean esMutante = detector.esMutante(adn);

        AdnEntidad nuevoAdn = AdnEntidad.builder()
                .adnSerializado(adnString)
                .esMutante(esMutante)
                .build();
        repositorio.save(nuevoAdn);

        return esMutante;
    }
    public EstadisticasDto obtenerEstadisticas() {
        long countMutant = repositorio.countByEsMutanteTrue();
        long countHuman = repositorio.countByEsMutanteFalse();
        double ratio = countHuman == 0 ? 0 : (double) countMutant / countHuman;

        return new EstadisticasDto(countMutant, countHuman, ratio);
    }
}
