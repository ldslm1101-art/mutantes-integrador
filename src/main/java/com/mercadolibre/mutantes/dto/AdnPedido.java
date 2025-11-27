package com.mercadolibre.mutantes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolibre.mutantes.validacion.ValidAdn;
import io.swagger.v3.oas.annotations.media.Schema;

public class AdnPedido {
    @Schema(description = "Secuencia de ADN (Array de Strings)", example = "[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]")
    @JsonProperty("dna")
    @ValidAdn
    private String[] adn;
    public AdnPedido() {}
    public AdnPedido(String[] adn) {
        this.adn = adn;
    }
    public String[] getAdn() {
        return adn;
    }
    public void setAdn(String[] adn) {
        this.adn = adn;
    }
}