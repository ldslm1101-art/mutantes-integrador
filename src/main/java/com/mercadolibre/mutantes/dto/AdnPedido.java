package com.mercadolibre.mutantes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdnPedido {

    @JsonProperty("dna")
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