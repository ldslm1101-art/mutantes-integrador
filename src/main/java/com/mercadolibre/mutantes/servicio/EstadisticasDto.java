package com.mercadolibre.mutantes.servicio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EstadisticasDto {

    private long countMutantDna;
    private long countHumanDna;
    private double ratio;

    public EstadisticasDto(long countMutantDna, long countHumanDna, double ratio) {
        this.countMutantDna = countMutantDna;
        this.countHumanDna = countHumanDna;
        this.ratio = ratio;
    }



    @JsonProperty("count_mutant_dna")
    public long getCountMutantDna() {
        return countMutantDna;
    }

    @JsonProperty("count_human_dna")
    public long getCountHumanDna() {
        return countHumanDna;
    }

    @JsonProperty("ratio")
    public double getRatio() {
        return ratio;
    }
}