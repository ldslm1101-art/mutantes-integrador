package com.mercadolibre.mutantes.servicio;

import com.mercadolibre.mutantes.logica.DetectorMutantes;
import com.mercadolibre.mutantes.modelo.AdnEntidad;
import com.mercadolibre.mutantes.repositorio.AdnRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Optional;

@Service
public class AdnServicio {
    @Autowired
    private DetectorMutantes detector;

    @Autowired
    private AdnRepositorio repositorio;

    public boolean analizarAdn(String[] adn) {

        String adnHash = calcularHash(adn);


        Optional<AdnEntidad> existente = repositorio.findByAdnHash(adnHash);
        if (existente.isPresent()) {
            return existente.get().isEsMutante();
        }
        boolean esMutante = detector.esMutante(adn);

        AdnEntidad nuevoAdn = AdnEntidad.builder()
                .adnHash(adnHash)
                .esMutante(esMutante)
                .build();
        repositorio.save(nuevoAdn);

        return esMutante;
    }
    private String calcularHash(String[] dna) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String adnString = String.join("", dna); // Unimos el array
            byte[] encodedhash = digest.digest(adnString.getBytes());
            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error calculando Hash", e);
        }
    }

    public EstadisticasDto obtenerEstadisticas() {
        long countMutant = repositorio.countByEsMutanteTrue();
        long countHuman = repositorio.countByEsMutanteFalse();
        double ratio = countHuman == 0 ? 0 : (double) countMutant / countHuman;

        return new EstadisticasDto(countMutant, countHuman, ratio);
    }
}
