package com.mercadolibre.mutantes.repositorio;

import com.mercadolibre.mutantes.modelo.AdnEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AdnRepositorio extends JpaRepository<AdnEntidad, Long>{
    Optional<AdnEntidad>findByAdnHash(String adnHash);

    long countByEsMutanteTrue();
    long countByEsMutanteFalse();
}
